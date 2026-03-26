package net.allthemods.alltheores.content.blocks.sets.ato_sets;

import net.allthemods.alltheores.content.blocks.sets.BlockSet;
import net.allthemods.alltheores.content.blocks.sets.ESetTypes;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

import static net.allthemods.alltheores.registry.ATORegistry.blockItem;
import static net.allthemods.alltheores.registry.ATORegistry.item;

public class ATOMaterialSet extends BlockSet {

    private static final List<ATOMaterialSet> instances = new ArrayList<>();

    public static List<ATOMaterialSet> getMaterialSets() {
        return instances;
    }

    public final ESetTypes type;

    // Item Tags
    public final TagKey<Item> DUST_TAG;

    // Block Tags
    public final TagKey<Block> BLOCK_TAG;

    // BlockItem Tags
    public final TagKey<Item> BLOCK_ITEM_TAG;


    // Items
    public final DeferredHolder<Item, Item> DUST;

    // Blocks
    public final DeferredHolder<Block, Block> BLOCK;

    // BlockItems
    public final DeferredHolder<Item, BlockItem> BLOCK_ITEM;

    public ATOMaterialSet(String name, ESetTypes type) {
        super(name);
        instances.add(this);

        this.type = type;

        // DEBUG: log material init
        System.out.println(String.format("ATOMaterialSet init: name=%s, type=%s", name, type));

        // Item Tags
        DUST_TAG = ItemTags.create(Reference.dust(name));
        // Block Tags
        BLOCK_TAG = BlockTags.create(Reference.block(name));

        // BlockItem Tags
        BLOCK_ITEM_TAG = ItemTags.create(Reference.block(name));


        // Items
        switch (type) {
            case INGOT, ALLOY:
                DUST = item(String.format("%s_dust", name));
                BLOCK = ATORegistry.BLOCKS.register(String.format("%s_block", name), () -> new Block(Block.Properties.of()
                        .strength(3.0f, 3.0f)
                        .sound(SoundType.METAL).setId(ResourceKey.create(ATORegistry.BLOCKS.getRegistryKey(), Identifier.fromNamespaceAndPath(Reference.MOD_ID, String.format("%s_block", name))))));
                break;
            case GEM:
                DUST = item(String.format("%s_dust", name));
                BLOCK = ATORegistry.BLOCKS.register(String.format("%s_block", name), () -> new Block(Block.Properties.of()
                        .strength(3.0f, 3.0f)
                        .sound(SoundType.AMETHYST).setId(ResourceKey.create(ATORegistry.BLOCKS.getRegistryKey(), Identifier.fromNamespaceAndPath(Reference.MOD_ID, String.format("%s_block", name))))));
                break;
            case DUST:
                DUST = item(name);
                BLOCK = ATORegistry.BLOCKS.register(String.format("%s_block", name), () -> new Block(Block.Properties.of()
                        .strength(3.0f, 3.0f)
                        .sound(SoundType.AMETHYST).setId(ResourceKey.create(ATORegistry.BLOCKS.getRegistryKey(), Identifier.fromNamespaceAndPath(Reference.MOD_ID, String.format("%s_block", name))))));
                break;
            default:
                throw new IllegalArgumentException("Invalid Type: " + name);
        }

        // BlockItems - register with explicit path used above
        BLOCK_ITEM = blockItem(String.format("%s_block", name), BLOCK);
    }
}
