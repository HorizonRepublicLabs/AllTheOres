package net.allthemods.alltheores.content.blocks.ore;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OreBlockStone extends OreBlock {
    public static final MapCodec<OreBlockStone> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(IntProviders.codec(0, 10).fieldOf("experience").forGetter((b) -> b.xpRange), propertiesCodec()).apply(i, OreBlockStone::new));
    public static IntProvider xpRange;
    public MapCodec<? extends OreBlockStone> codec() {
        return CODEC;
    }

    public OreBlockStone(IntProvider xpRange, BlockBehaviour.Properties properties) {
        super(xpRange, BlockBehaviour.Properties.of().strength(1.0F, 1.0F).requiresCorrectToolForDrops());
        OreBlockStone.xpRange = xpRange;
    }

}
