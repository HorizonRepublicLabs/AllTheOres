package net.allthemods.alltheores.infos;


import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Reference {

    public static final String MOD_ID = "alltheores";
    public static boolean enableFluids = ModList.get().isLoaded("tconstruct");

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(String.format("itemGroup.%s", Reference.MOD_ID)))
            // Use a safe vanilla icon to avoid resolving mod entries during static init
            .icon(() -> Items.STONE.getDefaultInstance())
            // Do not enumerate mod items here; that can cause suppliers to be invoked during register time
            .build()
    );

    public static Identifier ato(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static Identifier forge(String path) {
        return Identifier.fromNamespaceAndPath("c", path);
    }

    public static Identifier ore(String path) {
        return forge("ores/" + path);
    }

    public static Identifier ores_in_ground(String path) {
        return forge("ores_in_ground/" + path);
    }

    public static Identifier nugget(String path) {
        return forge("nuggets/" + path);
    }

    public static Identifier ingot(String path) {
        return forge("ingots/" + path);
    }

    public static Identifier gem(String path) {
        return forge("gems/" + path);
    }

    public static Identifier raw_materials(String path) {
        return forge("raw_materials/" + path);
    }

    public static Identifier block(String path) {
        return forge("storage_blocks/" + path);
    }

    public static Identifier dust(String path) {
        return forge("dusts/" + path);
    }

    public static Identifier plate(String path) {
        return forge("plates/" + path);
    }

    public static Identifier gear(String path) {
        return forge("gears/" + path);
    }

    public static Identifier rod(String path) {
        return forge("rods/" + path);
    }

    public static Identifier crystal(String path) {
        return forge("crystals/" + path);
    }

    public static Identifier shard(String path) {
        return forge("shards/" + path);
    }

    public static Identifier clump(String path) {
        return forge("clumps/" + path);
    }

    public static Identifier dirty_dust(String path) {
        return forge("dirty_dusts/" + path);
    }

    public static Identifier molten(String path) {
        return forge("molten_" + path);
    }
}
