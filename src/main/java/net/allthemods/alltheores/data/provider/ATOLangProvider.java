package net.allthemods.alltheores.data.provider;

import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.common.material.Material;
import net.allthemods.alltheores.core.registry.ATORegistry;

import java.util.Locale;

public class ATOLangProvider extends LanguageProvider {
    
    public ATOLangProvider(PackOutput output) {
        super(output, ATO.MOD_ID, "en_us");
    }
    
    @Override
    protected void addTranslations() {
        this.add(String.format(Locale.ROOT, "creative_tab.%s", ATO.MOD_ID), "All The Ores");
        this.add(ATORegistry.COPPER_ORE_HAMMER.get(), "Copper Ore Hammer");
        this.add(ATORegistry.IRON_ORE_HAMMER.get(), "Iron Ore Hammer");
        this.add(ATORegistry.BRONZE_ORE_HAMMER.get(), "Bronze Ore Hammer");
        this.add(ATORegistry.INVAR_ORE_HAMMER.get(), "Invar Ore Hammer");
        this.add(ATORegistry.PLATINUM_ORE_HAMMER.get(), "Platinum Ore Hammer");
        
        Material.forAll(material -> material.forEach(part -> {
            if (part.isVanilla()) return;
            
            DeferredHolder<?, ?> holder = part.getHolder();
            String path = holder.getId().getPath();
            Object value = holder.get();
            
            if (value instanceof Item item) {
                this.add(item, ATOLangProvider.toTitleCase(path));
                return;
            }
            
            if (value instanceof Block block) {
                this.add(block, ATOLangProvider.toTitleCase(path));
                return;
            }
            
            throw new IllegalStateException("Unknown part type " + part);
        }));
    }
    
    public static String toTitleCase(String input) {
        if (input == null || input.isBlank()) return input;
        
        String[] parts = input.split("_");
        StringBuilder result = new StringBuilder();
        
        for (String part : parts) {
            if (part.isEmpty()) continue;
            String capitalized = part.substring(0, 1).toUpperCase(Locale.ROOT) + part.substring(1).toLowerCase(Locale.ROOT);
            result.append(capitalized).append(" ");
        }
        
        return result.toString().trim();
    }
}
