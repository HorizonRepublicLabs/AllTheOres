package net.allthemods.alltheores.registry;

import net.allthemods.alltheores.content.blocks.sets.ATOSetHelper;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class ATOFluidInteractionRegistry {
    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> ATOSetHelper.applyToIngot(set -> {
            FluidInteractionRegistry.addInteraction(set.FLUID.MOLTEN_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(NeoForgeMod.WATER_TYPE.value(), fluidState -> fluidState.isSource() ? set.BLOCK.get().defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState()));
            FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(set.FLUID.MOLTEN_TYPE.get(), fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : set.RAW_BLOCK.get().defaultBlockState()));
        }));
        event.enqueueWork(() -> ATOSetHelper.applyToVanillaIngot(set -> {
            FluidInteractionRegistry.addInteraction(set.FLUID.MOLTEN_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(NeoForgeMod.WATER_TYPE.value(), fluidState -> fluidState.isSource() ? set.BLOCK.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState()));
            FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(set.FLUID.MOLTEN_TYPE.get(), fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : set.RAW_BLOCK.defaultBlockState()));
        }));
    }
}
