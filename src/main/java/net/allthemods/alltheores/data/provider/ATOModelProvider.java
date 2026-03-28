package net.allthemods.alltheores.data.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.core.registry.ATORegistry;

public class ATOModelProvider extends ModelProvider {
    
    public ATOModelProvider(PackOutput output) {
        super(output, ATO.MOD_ID);
    }
    
    @Override
    protected void registerModels(BlockModelGenerators blocks, ItemModelGenerators items) {
        ATORegistry.BLOCKS.getEntries().forEach(block -> blocks.createTrivialCube(block.get()));
        ATORegistry.ITEMS.getEntries().forEach(item -> {
            if (!(item.get() instanceof BlockItem)) items.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
        });
    }
}
