package net.allthemods.alltheores.common.parts;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.tags.TagOutput;
import net.allthemods.alltheores.core.registry.ATORegistry;

import java.util.Locale;
import java.util.function.UnaryOperator;

public abstract class BlockPartType extends MaterialPartType<Block, BlockPart, BlockPartType> {
    
    public static final BlockPartType STONE_ORE = new BlockPartType(Tags.Blocks.ORES) {
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("ores/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_ore", group), properties, UnaryOperator.identity());
        }
        
        @Override
        protected void constructTags(BlockPart part, TagOutput<Block> output) {
            output.add(Tags.Blocks.ORES_IN_GROUND_STONE, part.getHolder().get());
        }
    };
    
    public static final BlockPartType DEEPSLATE_ORE = new BlockPartType(Tags.Blocks.ORES) {
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("ores/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "deepslate_%s_ore", group), properties, UnaryOperator.identity());
        }
        
        @Override
        protected void constructTags(BlockPart part, TagOutput<Block> output) {
            output.add(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, part.getHolder().get());
        }
    };
    
    public static final BlockPartType NETHER_ORE = new BlockPartType(Tags.Blocks.ORES) {
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("ores/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "nether_%s_ore", group), properties, UnaryOperator.identity());
        }
        
        @Override
        protected void constructTags(BlockPart part, TagOutput<Block> output) {
            output.add(Tags.Blocks.ORES_IN_GROUND_NETHERRACK, part.getHolder().get());
        }
    };
    
    public static final BlockPartType END_ORE = new BlockPartType(Tags.Blocks.ORES) {
        
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("ores/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "end_%s_ore", group), properties, UnaryOperator.identity());
        }
        
        @Override
        protected void constructTags(BlockPart part, TagOutput<Block> output) {
            output.add(ATORegistry.ORES_IN_GROUND_END_STONE, part.getHolder().get());
        }
    };
    
    public static final BlockPartType OTHER_ORE = new BlockPartType(Tags.Blocks.ORES) {
        
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("ores/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> blockProperties) {
            return this.construct(String.format(Locale.ROOT, "other_%s_ore", group), blockProperties, UnaryOperator.identity());
        }
        
        @Override
        protected void constructTags(BlockPart part, TagOutput<Block> output) {
            output.add(ATORegistry.ORES_IN_GROUND_ANCIENT_STONE, part.getHolder().get());
        }
    };
    
    public static final BlockPartType RAW_BLOCK = new BlockPartType(Tags.Blocks.STORAGE_BLOCKS) {
        
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("storage_blocks/raw_" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "raw_%s_block", group), properties, UnaryOperator.identity());
        }
    };
    
    public static final BlockPartType BLOCK = new BlockPartType(Tags.Blocks.STORAGE_BLOCKS) {
        
        @Override
        public TagKey<Block> getTag(String group) {
            return TagKey.create(Registries.BLOCK, ATO.c("storage_blocks/" + group));
        }
        
        @Override
        public DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties) {
            return this.construct(String.format(Locale.ROOT, "%s_block", group), properties, UnaryOperator.identity());
        }
    };
    
    protected BlockPartType(TagKey<Block> tag) {
        super(tag);
    }
    
    @Override
    public void createTags(BlockPart part, TagOutput<Block> output) {
        super.createTags(part, output);
        output.addTag(BlockTags.MINEABLE_WITH_PICKAXE, part.getTag());
        if (part.getHardness() != null) output.addTag(part.getHardness(), part.getTag());
    }
    
    public void createItemTags(BlockPart part, TagOutput<Item> output) {
        final TagKey<Item> tag = TagKey.create(Registries.ITEM, part.getTag().location());
        
        output.add(tag, part.holder.get().asItem());
        output.addTag(TagKey.create(Registries.ITEM, this.getTag().location()), tag);
        output.addTag(TagKey.create(Registries.ITEM, BlockTags.MINEABLE_WITH_PICKAXE.location()), tag);
        if (part.getHardness() != null) output.addTag(TagKey.create(Registries.ITEM, part.getHardness().location()), tag);
    }
    
    public BlockPart create(String group, TagKey<Block> hardness) {
        return this.create(group, hardness, UnaryOperator.identity());
    }
    
    public BlockPart create(String group, TagKey<Block> hardness, UnaryOperator<BlockBehaviour.Properties> properties) {
        return new BlockPart(group, hardness, this, this.createHolder(group, properties), false);
    }
    
    @Override
    public DeferredHolder<Block, ? extends Block> createHolder(String group) {
        return this.createHolder(group, UnaryOperator.identity());
    }
    
    public abstract DeferredHolder<Block, ? extends Block> createHolder(String group, UnaryOperator<BlockBehaviour.Properties> properties);
    
    public DeferredHolder<Block, ? extends Block> construct(String identifier, UnaryOperator<BlockBehaviour.Properties> blockProperties, UnaryOperator<Item.Properties> itemProperties) {
        DeferredHolder<Block, Block> holder = ATORegistry.BLOCKS.register(identifier, k -> new Block(blockProperties.apply(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)).setId(ResourceKey.create(Registries.BLOCK, k))));
        ATORegistry.ITEMS.register(identifier, k -> new BlockItem(holder.get(), itemProperties.apply(new Item.Properties()).setId(ResourceKey.create(Registries.ITEM, k)).useBlockDescriptionPrefix()));
        return holder;
    }
}
