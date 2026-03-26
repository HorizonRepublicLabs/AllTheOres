package net.allthemods.alltheores.content.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;

import javax.annotation.Nullable;

public class OreHammer extends Item {

    public OreHammer(Item.Properties properties, int durability) {

        super(properties.durability(durability).setNoCombineRepair());


    }




}
