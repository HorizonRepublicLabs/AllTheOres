package net.allthemods.alltheores.content.blocks.ore;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OreBlockSlate extends OreBlock {

    public static final MapCodec<OreBlockSlate> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(IntProviders.codec(10, 15).fieldOf("experience").forGetter((b) -> b.xpRange), propertiesCodec()).apply(i, OreBlockSlate::new));
    public static IntProvider xpRange;
    public MapCodec<? extends OreBlockSlate> codec() {
        return CODEC;
    }

    public OreBlockSlate(IntProvider xpRange, BlockBehaviour.Properties properties) {
        super(xpRange, properties);
        OreBlockSlate.xpRange = xpRange;
    }
}
