package net.allthemods.alltheores.data.provider.worldgen;

import net.neoforged.neoforge.data.event.DatapackRegistryGatherer;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import net.minecraft.core.registries.Registries;

public final class ATOWorldGenProvider {
    
    public static void register(DatapackRegistryGatherer gatherer) {
        gatherer.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATOBiomeModifierProvider::bootstrap)
                .add(Registries.FEATURE, ATOConfiguredFeatureProvider::bootstrap)
                .add(Registries.PLACED_FEATURE, ATOPlacedFeatureProvider::bootstrap);
    }
    
    private ATOWorldGenProvider() { }
}
