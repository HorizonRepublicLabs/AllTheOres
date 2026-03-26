package net.allthemods.alltheores.content.blocks.sets.ato_sets;

import net.allthemods.alltheores.content.blocks.sets.ESetTypes;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

import static net.allthemods.alltheores.registry.ATORegistry.blockItem;
import static net.allthemods.alltheores.registry.ATORegistry.item;

public class ATOIngotSet extends ATOAlloySet {

    private static final List<ATOIngotSet> instances = new ArrayList<>();

    public static List<ATOIngotSet> getIngotSets() {
        return instances;
    }

    public final ATOOreSet ORES;
    //public final MekanismSet MEK;

    // Item Tags
    public final TagKey<Item> RAW_TAG;

    // Block Tags
    public final TagKey<Block> RAW_BLOCK_TAG;

    // Block Item Tags
    public final TagKey<Item> RAW_BLOCK_ITEM_TAG;

    // Items
    public final DeferredHolder<Item, Item> RAW;

    // Blocks
    public final DeferredHolder<Block, Block> RAW_BLOCK;

    // Block Items
    public final DeferredHolder<Item, BlockItem> RAW_BLOCK_ITEM;

    public ATOIngotSet(String name, int fluidColor, String hardness, int veinSize, int minY, int maxY, int count) {
        super(name, fluidColor, ESetTypes.INGOT);
        instances.add(this);

        // Item Tags
        RAW_TAG = ItemTags.create(Reference.raw_materials(name));

        // Block Tags
        RAW_BLOCK_TAG = BlockTags.create(Reference.block(String.format("raw_%s", name)));

        // Block Item Tags
        RAW_BLOCK_ITEM_TAG = ItemTags.create(Reference.block(String.format("raw_%s", name)));

        // Items
        RAW = item(String.format("raw_%s", name));

        // Blocks
        RAW_BLOCK = ATORegistry.BLOCKS.register(String.format("raw_%s_block", name), () -> new Block(Blocks.STONE.properties().strength(3.0f, 3.0f)));

        // Block Items
        RAW_BLOCK_ITEM = blockItem(String.format("raw_%s_block", name), RAW_BLOCK);

        ORES = new ATOOreSet(name, ESetTypes.INGOT, hardness, RAW, RAW_BLOCK, veinSize, minY, maxY, count);
        //MEK = ModList.get().isLoaded("mekanism") ? new MekanismSet(name, fluidColor, BLOCK) : null;
    }
}
