package net.allthemods.alltheores.content.blocks.ore;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OreBlockNether extends OreBlock {
    public static final MapCodec<OreBlockNether> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(IntProviders.codec(15, 20).fieldOf("experience").forGetter((b) -> b.xpRange), propertiesCodec()).apply(i, OreBlockNether::new));
    public static IntProvider xpRange;
    public MapCodec<? extends OreBlockNether> codec() {
        return CODEC;
    }

    public OreBlockNether(IntProvider xpRange, BlockBehaviour.Properties properties) {
        super(xpRange, BlockBehaviour.Properties.of().strength(1.5F, 1.5F).requiresCorrectToolForDrops());
        OreBlockNether.xpRange = xpRange;
    }
}
