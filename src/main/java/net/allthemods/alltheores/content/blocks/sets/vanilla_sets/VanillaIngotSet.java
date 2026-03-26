package net.allthemods.alltheores.content.blocks.sets.vanilla_sets;

import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATOFluidSet;
import net.allthemods.alltheores.content.blocks.sets.ato_sets.MekanismSet;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.List;


public class VanillaIngotSet extends VanillaSet {

    private static final List<VanillaIngotSet> instances = new ArrayList<>();

    public static List<VanillaIngotSet> getVanillaIngotSets() {
        return instances;
    }

    //public final MekanismSet MEK;
    public final ATOFluidSet FLUID;

    // Item Tags
    public final TagKey<Item> RAW_TAG;
    public final TagKey<Item> NUGGET_TAG;
    public final TagKey<Item> INGOT_TAG;

    // Block Tags
    public final TagKey<Block> ORE_BLOCK_TAG;
    public final TagKey<Block> RAW_BLOCK_TAG;
    public final TagKey<Block> BLOCK_TAG;

    // Block Item Tags
    public final TagKey<Item> ORE_BLOCK_ITEM_TAG;
    public final TagKey<Item> RAW_BLOCK_ITEM_TAG;
    public final TagKey<Item> BLOCK_ITEM_TAG;

    // Items
    public final Item RAW;
    public final Item INGOT;

    // Blocks
    public final Block RAW_BLOCK;
    public final Block BLOCK;

    // Block Items
    public final Item RAW_BLOCK_ITEM;
    public final Item BLOCK_ITEM;

    public VanillaIngotSet(String name, int fluidColor, Item raw, Block rawBlock, Item ingot, Block block) {
        super(name);
        instances.add(this);

        // Item Tags
        RAW_TAG = ItemTags.create(Reference.raw_materials(name));
        NUGGET_TAG = ItemTags.create(Reference.nugget(name));
        INGOT_TAG = ItemTags.create(Reference.ingot(name));

        // Block Tags
        ORE_BLOCK_TAG = BlockTags.create(Reference.ore(name));
        RAW_BLOCK_TAG = BlockTags.create(Reference.block(String.format("raw_%s", name)));
        BLOCK_TAG = BlockTags.create(Reference.block(name));

        // Block Item Tags
        ORE_BLOCK_ITEM_TAG = ItemTags.create(Reference.ore(name));
        RAW_BLOCK_ITEM_TAG = ItemTags.create(Reference.block(String.format("raw_%s", name)));
        BLOCK_ITEM_TAG = ItemTags.create(Reference.block(name));

        // Items
        RAW = raw;
        INGOT = ingot;

        // Blocks
        RAW_BLOCK = rawBlock;
        BLOCK = block;

        // Block Items - avoid getDefaultInstance() during static init
        RAW_BLOCK_ITEM = rawBlock.asItem();
        BLOCK_ITEM = block.asItem();

        //MEK = ModList.get().isLoaded("mekanism") ? new MekanismSet(name, fluidColor, block) : null;
        FLUID = new ATOFluidSet(name, fluidColor);
    }
}
