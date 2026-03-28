package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ItemPart extends MaterialPart<Item, ItemPart, ItemPartType> {
    
    public ItemPart(String group, ItemPartType type, DeferredHolder<Item, ? extends Item> holder, boolean isVanilla) {
        super(group, type, holder, isVanilla);
    }
    
    public static ItemPart fromVanilla(String group, ItemPartType type, Item vanilla) {
        return new ItemPart(group, type, DeferredHolder.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(vanilla)), true);
    }
}
