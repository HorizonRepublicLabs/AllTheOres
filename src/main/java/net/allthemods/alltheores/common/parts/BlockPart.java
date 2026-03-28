package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.common.tags.TagOutput;

import org.jspecify.annotations.Nullable;

public class BlockPart extends MaterialPart<Block, BlockPart, BlockPartType> {
    
    protected final @Nullable TagKey<Block> hardness;
    
    protected BlockPart(String group, @Nullable TagKey<Block> hardness, BlockPartType type, DeferredHolder<Block, ? extends Block> holder, boolean isVanilla) {
        super(group, type, holder, isVanilla);
        this.hardness = hardness;
    }
    
    public static BlockPart fromVanilla(String group, TagKey<Block> hardness, BlockPartType type, Block vanilla) {
        return new BlockPart(group, hardness, type, DeferredHolder.create(Registries.BLOCK, BuiltInRegistries.BLOCK.getKey(vanilla)), true);
    }
    
    public @Nullable TagKey<Block> getHardness() {
        return this.hardness;
    }
    
    public void buildItemTags(TagOutput<Item> output) {
        this.type.createItemTags(this, output);
    }
}
