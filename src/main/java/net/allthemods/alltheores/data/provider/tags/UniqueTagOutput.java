package net.allthemods.alltheores.data.provider.tags;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import net.allthemods.alltheores.common.tags.TagOutput;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class UniqueTagOutput<T> implements TagOutput<T> {
    
    private final Function<TagKey<T>, TagAppender<T>> factory;
    private final Function<T, ResourceKey<T>> keyResolver;
    private final Map<TagKey<T>, Set<String>> entries = new HashMap<>();
    
    public UniqueTagOutput(Function<TagKey<T>, TagAppender<T>> factory, Function<T, ResourceKey<T>> keyResolver) {
        this.factory = factory;
        this.keyResolver = keyResolver;
    }
    
    @Override
    public void add(TagKey<T> tag, T value) {
        ResourceKey<T> key = this.keyResolver.apply(value);
        if (this.track(tag, String.valueOf(key.identifier()))) {
            this.factory.apply(tag).add(key);
        }
    }
    
    @Override
    public void addTag(TagKey<T> tag, TagKey<T> childTag) {
        if (this.track(tag, "#" + childTag.location())) {
            this.factory.apply(tag).addTag(childTag);
        }
    }
    
    private boolean track(TagKey<T> tag, String entry) {
        return this.entries.computeIfAbsent(tag, ignored -> new LinkedHashSet<>()).add(entry);
    }
}
