package net.allthemods.alltheores.content.blocks.sets.vanilla_sets;

import net.allthemods.alltheores.content.blocks.sets.ato_sets.ATOFluidSet;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class VanillaDebrisSet extends VanillaSet {

    private static final List<VanillaDebrisSet> instances = new ArrayList<>();

    public static List<VanillaDebrisSet> getVanillaDebrisSets() {
        return instances;
    }

    public final ATOFluidSet FLUID;

    // Item Tags
    public final TagKey<Item> INGOT_TAG;

    // Block Tags
    public final TagKey<Block> DEBRIS_TAG;
    public final TagKey<Block> BLOCK_TAG;

    // Block Item Tags
    public final TagKey<Item> DEBRIS_ITEM_TAG;
    public final TagKey<Item> BLOCK_ITEM_TAG;

    // Items
    public final Item SCRAP;
    public final Item INGOT;

    // Blocks
    public final Block DEBRIS;
    public final Block BLOCK;

    // Block Items
    public final Item DEBRIS_ITEM;
    public final Item BLOCK_ITEM;

    public VanillaDebrisSet(String name, int fluidColor, Block debris, Item scrap, Item ingot, Block block) {
        super(name);
        instances.add(this);

        // Item Tags
        INGOT_TAG = ItemTags.create(Reference.ingot(name));

        // Block Tags
        DEBRIS_TAG = BlockTags.create(Reference.ore(String.format("%s_scrap", name)));
        BLOCK_TAG = BlockTags.create(Reference.block(name));

        // Block Item Tags
        DEBRIS_ITEM_TAG = ItemTags.create(Reference.ore(String.format("%s_scrap", name)));
        BLOCK_ITEM_TAG = ItemTags.create(Reference.block(name));

        // Items
        SCRAP = scrap;
        INGOT = ingot;

        // Blocks
        DEBRIS = debris;
        BLOCK = block;

        // Block Items
        DEBRIS_ITEM = debris.asItem();
        BLOCK_ITEM = block.asItem();

        FLUID = new ATOFluidSet(name, fluidColor);
    }
}
