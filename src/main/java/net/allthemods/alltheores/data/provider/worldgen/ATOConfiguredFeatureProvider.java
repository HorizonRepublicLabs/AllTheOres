package net.allthemods.alltheores.data.provider.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import net.allthemods.allthemodium.core.registry.ATMBlocks;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.common.parts.BlockPartType;

import java.util.ArrayList;
import java.util.List;

public final class ATOConfiguredFeatureProvider {
    private ATOConfiguredFeatureProvider() { }
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        Material.forAll(material -> {
            Material.WorldGen worldGen = material.getWorldGen();
            if (worldGen == null) return;
            
            List<OreConfiguration.TargetBlockState> targets = new ArrayList<>();
            ATOConfiguredFeatureProvider.addTarget(material, BlockPartType.STONE_ORE, new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), targets);
            ATOConfiguredFeatureProvider.addTarget(material, BlockPartType.DEEPSLATE_ORE, new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), targets);
            ATOConfiguredFeatureProvider.addTarget(material, BlockPartType.NETHER_ORE, new BlockMatchTest(Blocks.NETHERRACK), targets);
            ATOConfiguredFeatureProvider.addTarget(material, BlockPartType.END_ORE, new BlockMatchTest(Blocks.END_STONE), targets);
            ATOConfiguredFeatureProvider.addTarget(material, BlockPartType.OTHER_ORE, new BlockMatchTest(ATMBlocks.ANCIENT_STONE.get()), targets);
            
            if (targets.isEmpty()) return;
            
            FeatureUtils.register(
                    context,
                    material.getConfiguredOreFeatureKey(),
                    Feature.ORE,
                    new OreConfiguration(targets, worldGen.veinSize())
            );
        });
    }
    
    private static void addTarget(
            Material material,
            BlockPartType type,
            RuleTest target,
            List<OreConfiguration.TargetBlockState> targets
    ) {
        material.apply(type, part -> {
            if (part.isVanilla()) return;
            targets.add(OreConfiguration.target(target, part.getHolder().get().defaultBlockState()));
        });
    }
}
