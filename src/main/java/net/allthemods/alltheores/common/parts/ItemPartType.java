package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.core.registry.ATORegistry;

import java.util.Locale;
import java.util.function.UnaryOperator;

public abstract class ItemPartType extends MaterialPartType<Item, ItemPart, ItemPartType> {
    
    public static final ItemPartType NUGGET = new ItemPartType(Tags.Items.NUGGETS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("nuggets/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_nugget", group), properties);
        }
    };
    
    public static final ItemPartType INGOT = new ItemPartType(Tags.Items.INGOTS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("ingots/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_ingot", group), properties);
        }
    };
    
    public static final ItemPartType RAW = new ItemPartType(Tags.Items.RAW_MATERIALS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("raw_materials/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "raw_%s", group), properties);
        }
    };
    
    public static final ItemPartType DUST = new ItemPartType(Tags.Items.DUSTS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("dusts/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_dust", group), properties);
        }
    };
    
    public static final ItemPartType GEM = new ItemPartType(Tags.Items.GEMS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("gems/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(group, properties);
        }
    };
    
    public static final ItemPartType ROD = new ItemPartType(Tags.Items.RODS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("rods/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_rod", group), properties);
        }
    };
    
    public static final ItemPartType PLATE = new ItemPartType(ATORegistry.PLATES) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("plates/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_plate", group), properties);
        }
    };
    
    public static final ItemPartType GEAR = new ItemPartType(ATORegistry.GEARS) {
        
        @Override
        public TagKey<Item> getTag(String group) {
            return TagKey.create(Registries.ITEM, ATO.c("gears/" + group));
        }
        
        @Override
        public DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_gear", group), properties);
        }
    };
    
    protected ItemPartType(TagKey<Item> tag) {
        super(tag);
    }
    
    public ItemPart create(String group) {
        return new ItemPart(group, this, this.createHolder(group), false);
    }
    
    @Override
    public DeferredHolder<Item, ? extends Item> createHolder(String group) {
        return this.createHolder(group, UnaryOperator.identity());
    }
    
    public abstract DeferredHolder<Item, ? extends Item> createHolder(String group, UnaryOperator<Item.Properties> properties);
    
    public DeferredHolder<Item, ? extends Item> construct(String identifier, UnaryOperator<Item.Properties> properties) {
        return ATORegistry.ITEMS.register(identifier, k -> new Item(properties.apply(new Item.Properties()).setId(ResourceKey.create(Registries.ITEM, k))));
    }
}
