package net.allthemods.alltheores.content.blocks.sets.vanilla_sets;

import net.allthemods.alltheores.infos.Reference;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class VanillaGemSet extends VanillaSet {

    private static final List<VanillaGemSet> instances = new ArrayList<>();

    public static List<VanillaGemSet> getVanillaGemSets() {
        return instances;
    }

    // Item Tags
    public final TagKey<Item> GEM_TAG;

    // Block Tags
    public final TagKey<Block> ORE_BLOCK_TAG;
    public final TagKey<Block> BLOCK_TAG;

    // Block Item Tags
    public final TagKey<Item> ORE_BLOCK_ITEM_TAG;
    public final TagKey<Item> BLOCK_ITEM_TAG;

    // Items
    public final Item GEM;

    // Blocks
    public final Block BLOCK;

    // Block Items
    public final Item BLOCK_ITEM;

    public VanillaGemSet(String name, Item gem, Block block) {
        super(name);
        instances.add(this);

        // Item Tags
        GEM_TAG = ItemTags.create(Reference.gem(name));

        // Block Tags
        ORE_BLOCK_TAG = BlockTags.create(Reference.ore(name));
        BLOCK_TAG = BlockTags.create(Reference.block(name));

        // Block Item Tags
        ORE_BLOCK_ITEM_TAG = ItemTags.create(Reference.ore(name));
        BLOCK_ITEM_TAG = ItemTags.create(Reference.block(name));

        // Items
        GEM = gem;

        // Blocks
        BLOCK = block;

        // Block Items
        BLOCK_ITEM = block.asItem();
    }
}
