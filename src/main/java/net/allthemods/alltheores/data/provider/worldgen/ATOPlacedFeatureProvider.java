package net.allthemods.alltheores.data.provider.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.allthemods.alltheores.common.material.Material;

import java.util.List;

public final class ATOPlacedFeatureProvider {
    private ATOPlacedFeatureProvider() { }
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
        
        Material.forAll(material -> {
            Material.WorldGen worldGen = material.getWorldGen();
            if (worldGen == null) return;
            
            PlacementUtils.register(
                    context,
                    material.getPlacedOreFeatureKey(),
                    features.getOrThrow(material.getConfiguredOreFeatureKey()),
                    List.of(
                            CountPlacement.of(worldGen.count()),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.triangle(
                                    VerticalAnchor.absolute(worldGen.minY()),
                                    VerticalAnchor.absolute(worldGen.maxY())
                            ),
                            BiomeFilter.biome()
                    )
            );
        });
    }
}
