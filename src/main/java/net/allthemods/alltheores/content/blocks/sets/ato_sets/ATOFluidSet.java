package net.allthemods.alltheores.content.blocks.sets.ato_sets;

import net.allthemods.alltheores.content.blocks.sets.BlockSet;
import net.allthemods.alltheores.content.fluids.MoltenBlock;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ATOFluidSet extends BlockSet {

    private static final List<ATOFluidSet> instances = new ArrayList<>();

    public static List<ATOFluidSet> getFluidSets() {
        return instances;
    }

    public final int fluidColor;

    // Tags
    public final TagKey<Block> MOLTEN_BLOCK_TAG;
    public final TagKey<Fluid> MOLTEN_FLUID_TAG;

    // Fluids
    public final Supplier<FluidType> MOLTEN_TYPE;

    public final DeferredHolder<Fluid, BaseFlowingFluid.Source> MOLTEN;
    public final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> MOLTEN_FLOWING;
    public final DeferredHolder<Item, BucketItem> MOLTEN_BUCKET;
    public final DeferredHolder<Block, LiquidBlock> MOLTEN_BLOCK;

    public ATOFluidSet(String name, int fluidColor) {
        super(name);
        instances.add(this);

        this.fluidColor = fluidColor;

        // Tags
        MOLTEN_BLOCK_TAG = BlockTags.create(Reference.molten(name));
        MOLTEN_FLUID_TAG = FluidTags.create(Reference.molten(name));

        // Fluids
        MOLTEN_TYPE = ATORegistry.FLUID_TYPES.register(String.format("molten_%s_type", name), () -> new FluidType(FluidType.Properties.create()
                .canSwim(false)
                .canDrown(false)
                .canConvertToSource(false)
                .lightLevel(15)
                .density(3000)
                .viscosity(6000)
                .temperature(1300)
                .motionScale(0.0023333333333333335)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.LAVA_EXTINGUISH)
        ));

        MOLTEN = ATORegistry.FLUIDS.register(String.format("molten_%s", name), () -> new BaseFlowingFluid.Source(makeMoltenProperties(name)));
        MOLTEN_FLOWING = ATORegistry.FLUIDS.register(String.format("flowing_molten_%s", name), () -> new BaseFlowingFluid.Flowing(makeMoltenProperties(name)));
        MOLTEN_BUCKET = ATORegistry.FLUID_ITEMS.register(String.format("molten_%s_bucket", name), () -> new BucketItem(MOLTEN.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
        MOLTEN_BLOCK = ATORegistry.FLUID_BLOCKS.register(String.format("molten_%s", name), () -> new MoltenBlock(MOLTEN.get(), Block.Properties.of()
                .strength(100.0F)
                .speedFactor(0.7F)
                .noCollision()
                .liquid()
                .replaceable()
        ));
    }

    private BaseFlowingFluid.Properties makeMoltenProperties(String name) {
        return new BaseFlowingFluid.Properties(
                MOLTEN_TYPE,
                DeferredHolder.create(Registries.FLUID, Identifier.fromNamespaceAndPath(ATORegistry.FLUIDS.getNamespace(), String.format("molten_%s", name))),
                DeferredHolder.create(Registries.FLUID, Identifier.fromNamespaceAndPath(ATORegistry.FLUIDS.getNamespace(), String.format("flowing_molten_%s", name)))
        ).bucket(DeferredHolder.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ATORegistry.FLUIDS.getNamespace(), String.format("molten_%s_bucket", name))))
                .block(DeferredHolder.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ATORegistry.FLUIDS.getNamespace(), String.format("molten_%s", name))))
                .tickRate(30)
                .slopeFindDistance(4)
                .levelDecreasePerBlock(2);
    }
}
