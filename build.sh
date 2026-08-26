#!/usr/bin/env bash
# Build/run helper for AllTheOres.
#
#   ./build.sh            build the mod jar (default)
#   ./build.sh data       regenerate src/generated/resources
#   ./build.sh client     launch the dev client
#   ./build.sh server     launch the dev server
#   ./build.sh test       run the game tests headless
#   ./build.sh clean      wipe build output
#
# Anything else is passed straight through to gradlew.
set -euo pipefail

cd "$(dirname "$0")"

# The toolchain in build.gradle needs a JDK 25. Gradle itself has to run on
# one too, so locate a matching JDK before handing over.
REQUIRED_JAVA=25

find_java_home() {
    if [ -n "${JAVA_HOME:-}" ] && [ -x "$JAVA_HOME/bin/javac" ]; then
        echo "$JAVA_HOME"
        return
    fi
    if command -v /usr/libexec/java_home >/dev/null 2>&1; then
        /usr/libexec/java_home -v "$REQUIRED_JAVA" 2>/dev/null && return
    fi
    for candidate in \
        "/opt/homebrew/opt/openjdk@$REQUIRED_JAVA/libexec/openjdk.jdk/Contents/Home" \
        "/usr/local/opt/openjdk@$REQUIRED_JAVA/libexec/openjdk.jdk/Contents/Home" \
        "$HOME/.sdkman/candidates/java/current" \
        "/usr/lib/jvm/java-$REQUIRED_JAVA-openjdk-amd64" \
        "/usr/lib/jvm/java-$REQUIRED_JAVA-openjdk"; do
        if [ -x "$candidate/bin/javac" ]; then
            echo "$candidate"
            return
        fi
    done
}

JAVA_HOME="$(find_java_home)"
if [ -z "$JAVA_HOME" ]; then
    echo "No JDK $REQUIRED_JAVA found. Install one, e.g.:" >&2
    echo "  macOS:  brew install openjdk@$REQUIRED_JAVA" >&2
    echo "  Linux:  apt install openjdk-$REQUIRED_JAVA-jdk" >&2
    echo "Or point JAVA_HOME at an existing install." >&2
    exit 1
fi
export JAVA_HOME

java_major="$("$JAVA_HOME/bin/javac" -version 2>&1 | sed -E 's/javac ([0-9]+).*/\1/')"
if [ "$java_major" != "$REQUIRED_JAVA" ]; then
    echo "Warning: $JAVA_HOME is JDK $java_major, expected $REQUIRED_JAVA." >&2
fi

case "${1:-build}" in
    build)  tasks=(build) ;;
    data)   tasks=(runData) ;;
    client) tasks=(runClient) ;;
    server) tasks=(runServer) ;;
    test)   tasks=(runGameTestServer) ;;
    clean)  tasks=(clean) ;;
    *)      tasks=("$@") ;;
esac

echo "JAVA_HOME=$JAVA_HOME"
echo "> ./gradlew ${tasks[*]}"
exec ./gradlew "${tasks[@]}"
