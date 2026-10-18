package net.allthemods.alltheores.data;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.DatapackRegistryGatherer;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.data.provider.ATOLangProvider;
import net.allthemods.alltheores.data.provider.ATOModelProvider;
import net.allthemods.alltheores.data.provider.ATORecipeProvider;
import net.allthemods.alltheores.data.provider.enderio.ATOEIOAlloySmelterRecipeProvider;
import net.allthemods.alltheores.data.provider.enderio.ATOEIOSagMillRecipeProvider;
import net.allthemods.alltheores.data.provider.energizedpower.ATOEnergizedPowerPulverizerRecipeProvider;
import net.allthemods.alltheores.data.provider.loot.ATOBlockLoot;
import net.allthemods.alltheores.data.provider.tags.ATOBlockTagsProvider;
import net.allthemods.alltheores.data.provider.tags.ATOItemTagsProvider;
import net.allthemods.alltheores.data.provider.worldgen.ATOWorldGenProvider;

@EventBusSubscriber(modid = ATO.MOD_ID)
public class ATODataGenerator {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        if (!ATO.MOD_ID.equalsIgnoreCase(event.getModContainer().getModId())) return;
        
        event.createProvider(ATOBlockTagsProvider::new);
        event.createProvider(ATOItemTagsProvider::new);
        event.createProvider(ATOLangProvider::new);
        event.createProvider(ATOModelProvider::new);
        event.createProvider(ATOEIOSagMillRecipeProvider::new);
        event.createProvider(ATOEIOAlloySmelterRecipeProvider::new);
        event.createProvider(ATOEnergizedPowerPulverizerRecipeProvider::new);
    }
    
    @SubscribeEvent
    public static void gatherRegistries(GatherDataRegistryEntriesEvent event) {
        DatapackRegistryGatherer gatherer = event.gatherFor(ATO.MOD_ID)
                .recipe(ATORecipeProvider::new)
                .lootTable(new LootTableProvider.SubProviderEntry(ATOBlockLoot::new, LootContextParamSets.BLOCK));
        ATOWorldGenProvider.register(gatherer);
    }
}
