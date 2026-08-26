package net.allthemods.alltheores.data.provider.tags;

import net.neoforged.neoforge.common.data.ItemTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.core.registry.ATORegistry;

import java.util.concurrent.CompletableFuture;

public class ATOItemTagsProvider extends ItemTagsProvider {
    
    public ATOItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ATO.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ATORegistry.ORE_HAMMER)
                .add(ATORegistry.COPPER_ORE_HAMMER.getKey())
                .add(ATORegistry.IRON_ORE_HAMMER.getKey())
                .add(ATORegistry.BRONZE_ORE_HAMMER.getKey())
                .add(ATORegistry.INVAR_ORE_HAMMER.getKey())
                .add(ATORegistry.PLATINUM_ORE_HAMMER.getKey());
        
        UniqueTagOutput<Item> output = new UniqueTagOutput<>(this::tag, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
        Material.forAll(material -> material.createItemTags(output));
    }
}
