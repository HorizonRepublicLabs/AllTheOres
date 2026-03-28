package net.allthemods.alltheores.data.provider.worldgen;

import net.neoforged.neoforge.registries.NeoForgeRegistries;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public final class ATOWorldGenProvider {
    
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ATOBiomeModifierProvider::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ATOConfiguredFeatureProvider::bootstrap)
            .add(Registries.PLACED_FEATURE, ATOPlacedFeatureProvider::bootstrap);
    
    private ATOWorldGenProvider() { }
}
