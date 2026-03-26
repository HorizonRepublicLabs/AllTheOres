package net.allthemods.alltheores.content.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;

public class MoltenBlock extends LiquidBlock {

    public MoltenBlock(FlowingFluid fluid, BlockBehaviour.Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos position, @NotNull Entity entity, @NotNull InsideBlockEffectApplier applier, @NotNull boolean precise) {
        super.entityInside(state, world, position, entity, applier, precise);
        entity.setRemainingFireTicks(100);
    }

}
