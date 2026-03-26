package net.allthemods.alltheores.content.blocks.sets.ato_sets;

import net.allthemods.alltheores.content.blocks.sets.ESetTypes;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

import static net.allthemods.alltheores.registry.ATORegistry.item;


public class ATOAlloySet extends ATOMaterialSet {

    private static final List<ATOAlloySet> instances = new ArrayList<>();

    public static List<ATOAlloySet> getAlloySets() {
        return instances;
    }

    public final ATOFluidSet FLUID;

    // Item Tags
    public final TagKey<Item> NUGGET_TAG;
    public final TagKey<Item> INGOT_TAG;

    public final TagKey<Item> ROD_TAG;
    public final TagKey<Item> GEAR_TAG;
    public final TagKey<Item> PLATE_TAG;

    // Items
    public final DeferredHolder<Item, Item> INGOT;
    public final DeferredHolder<Item, Item> NUGGET;

    public final DeferredHolder<Item, Item> ROD;
    public final DeferredHolder<Item, Item> GEAR;
    public final DeferredHolder<Item, Item> PLATE;


    public ATOAlloySet(String name, int fluidColor) {
        this(name, fluidColor, ESetTypes.ALLOY);
    }

    public ATOAlloySet(String name, int fluidColor, ESetTypes type) {
        super(name, type);
        instances.add(this);

        // Item Tags
        INGOT_TAG = ItemTags.create(Reference.ingot(name));
        NUGGET_TAG = ItemTags.create(Reference.nugget(name));
        ROD_TAG = ItemTags.create(Reference.rod(name));
        GEAR_TAG = ItemTags.create(Reference.gear(name));
        PLATE_TAG = ItemTags.create(Reference.plate(name));

        // Items
        INGOT = item(String.format("%s_ingot", name));
        NUGGET = item(String.format("%s_nugget", name));

        ROD = item(String.format("%s_rod", name));
        GEAR = item(String.format("%s_gear", name));
        PLATE = item(String.format("%s_plate", name));

        FLUID = new ATOFluidSet(name, fluidColor);
    }
}