package net.allthemods.alltheores.data.provider.worldgen;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPartType;

public final class ATOBiomeModifierProvider {
    private ATOBiomeModifierProvider() { }
    
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        
        Material.forAll(material -> {
            Material.WorldGen worldGen = material.getWorldGen();
            if (worldGen == null) return;
            
            HolderSet<PlacedFeature> feature = HolderSet.direct(placedFeatures.getOrThrow(material.getPlacedOreFeatureKey()));
            
            if (ATOBiomeModifierProvider.shouldGenerate(material, BlockPartType.STONE_ORE) || ATOBiomeModifierProvider.shouldGenerate(material, BlockPartType.DEEPSLATE_ORE)) {
                context.register(material.getOverworldBiomeModifierKey(), new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(Tags.Biomes.IS_OVERWORLD),
                        feature,
                        GenerationStep.Decoration.UNDERGROUND_ORES
                ));
            }
            
            if (ATOBiomeModifierProvider.shouldGenerate(material, BlockPartType.NETHER_ORE)) {
                context.register(material.getNetherBiomeModifierKey(), new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(Tags.Biomes.IS_NETHER),
                        feature,
                        GenerationStep.Decoration.UNDERGROUND_ORES
                ));
            }
            
            if (ATOBiomeModifierProvider.shouldGenerate(material, BlockPartType.END_ORE)) {
                context.register(material.getEndBiomeModifierKey(), new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(Tags.Biomes.IS_END),
                        feature,
                        GenerationStep.Decoration.UNDERGROUND_ORES
                ));
            }
        });
    }
    
    private static boolean shouldGenerate(Material material, BlockPartType type) {
        if (!material.has(type)) return false;
        
        final boolean[] shouldGenerate = { false };
        material.apply(type, part -> shouldGenerate[0] = !part.isVanilla());
        return shouldGenerate[0];
    }
}
