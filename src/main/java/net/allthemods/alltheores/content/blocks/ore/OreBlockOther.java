package net.allthemods.alltheores.content.blocks.ore;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.lwjgl.system.ffm.mapping.Mapping;

public class OreBlockOther extends OreBlock {
    public static final MapCodec<OreBlockOther> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(IntProviders.codec(30, 40).fieldOf("experience").forGetter((b) -> b.xpRange), propertiesCodec()).apply(i, OreBlockOther::new));
    public static IntProvider xpRange;
    public MapCodec<? extends OreBlockOther> codec() {
        return CODEC;
    }


    public OreBlockOther(IntProvider xpRange, BlockBehaviour.Properties properties) {
        super(xpRange, BlockBehaviour.Properties.of().strength(3.0F, 3.0F).requiresCorrectToolForDrops());
        OreBlockOther.xpRange = xpRange;
    }
}
