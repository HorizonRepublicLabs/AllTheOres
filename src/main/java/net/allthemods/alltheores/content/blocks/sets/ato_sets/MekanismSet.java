package net.allthemods.alltheores.content.blocks.sets.ato_sets;

//import mekanism.api.chemical.Chemical;
//import mekanism.api.chemical.ChemicalBuilder;
import net.allthemods.alltheores.content.blocks.sets.BlockSet;
import net.allthemods.alltheores.content.items.mekanism.Clump;
import net.allthemods.alltheores.content.items.mekanism.Crystal;
import net.allthemods.alltheores.content.items.mekanism.DirtyDust;
import net.allthemods.alltheores.content.items.mekanism.Shard;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATOMekanismRegistry;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class MekanismSet extends BlockSet {

    private static final List<MekanismSet> instances = new ArrayList<>();

    public MekanismSet(String name) {
        super(name);
    }

    public static List<MekanismSet> getMekanismSets() {
        return instances;
    }
/*
    //Item Tags
    public final TagKey<Item> CRYSTAL_TAG;
    public final TagKey<Item> SHARD_TAG;
    public final TagKey<Item> CLUMP_TAG;
    public final TagKey<Item> DIRTY_DUST_TAG;

    // Chemicals
    public final DeferredHolder<Chemical, Chemical> DIRTY_SLURRY;
    public final DeferredHolder<Chemical, Chemical> CLEAN_SLURRY;

    // Items
    public final DeferredHolder<Item, Item> CRYSTAL;
    public final DeferredHolder<Item, Item> SHARD;
    public final DeferredHolder<Item, Item> CLUMP;
    public final DeferredHolder<Item, Item> DIRTY_DUST;

    public MekanismSet(String name, int fluidColor, DeferredHolder<Block, Block> block) {
        super(name);
        instances.add(this);

        //Item Tags
        CRYSTAL_TAG = ItemTags.create(Reference.crystal(name));
        SHARD_TAG = ItemTags.create(Reference.shard(name));
        CLUMP_TAG = ItemTags.create(Reference.clump(name));
        DIRTY_DUST_TAG = ItemTags.create(Reference.dirty_dust(name));

        // Chemicals
        DIRTY_SLURRY = ATOMekanismRegistry.SLURRYS.register(String.format("dirty_%s", name), () -> new Chemical(ChemicalBuilder.builder().tint(darkenColor(fluidColor, 0.75)).ore(block.getId())));
        CLEAN_SLURRY = ATOMekanismRegistry.SLURRYS.register(String.format("clean_%s", name), () -> new Chemical(ChemicalBuilder.builder().tint(fluidColor).ore(block.getId())));

        // Items
        CRYSTAL = ATORegistry.ITEMS.register(String.format("%s_crystal", name), () -> new Crystal(new Item.Properties()));
        SHARD = ATORegistry.ITEMS.register(String.format("%s_shard", name), () -> new Shard(new Item.Properties()));
        CLUMP = ATORegistry.ITEMS.register(String.format("%s_clump", name), () -> new Clump(new Item.Properties()));
        DIRTY_DUST = ATORegistry.ITEMS.register(String.format("dirty_%s_dust", name), () -> new DirtyDust(new Item.Properties()));
    }

    public MekanismSet(String name, int fluidColor, Block block) {
        super(name);
        instances.add(this);

        //Item Tags
        CRYSTAL_TAG = ItemTags.create(Reference.crystal(name));
        SHARD_TAG = ItemTags.create(Reference.shard(name));
        CLUMP_TAG = ItemTags.create(Reference.clump(name));
        DIRTY_DUST_TAG = ItemTags.create(Reference.dirty_dust(name));

        // Chemicals
        DIRTY_SLURRY = ATOMekanismRegistry.SLURRYS.register(String.format("dirty_%s", name), () -> new Chemical(ChemicalBuilder.builder().tint(darkenColor(fluidColor, 0.75)).ore(BuiltInRegistries.BLOCK.getKey(block))));
        CLEAN_SLURRY = ATOMekanismRegistry.SLURRYS.register(String.format("clean_%s", name), () -> new Chemical(ChemicalBuilder.builder().tint(fluidColor).ore(BuiltInRegistries.BLOCK.getKey(block))));

        // Items
        CRYSTAL = ATORegistry.ITEMS.register(String.format("%s_crystal", name), () -> new Crystal(new Item.Properties()));
        SHARD = ATORegistry.ITEMS.register(String.format("%s_shard", name), () -> new Shard(new Item.Properties()));
        CLUMP = ATORegistry.ITEMS.register(String.format("%s_clump", name), () -> new Clump(new Item.Properties()));
        DIRTY_DUST = ATORegistry.ITEMS.register(String.format("dirty_%s_dust", name), () -> new DirtyDust(new Item.Properties()));
    }

    private static int darkenColor(int color, double factor) {
        return (0xFF << 24) | // Alpha
                (Math.max(0, (int) (((color >> 16) & 0xFF) * factor)) << 16) | // Red
                (Math.max(0, (int) (((color >> 8) & 0xFF) * factor)) << 8) |  // Green
                Math.max(0, (int) ((color & 0xFF) * factor)); // Blue
    }
*/
}
