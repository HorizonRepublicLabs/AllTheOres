package net.allthemods.alltheores;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import net.allthemods.alltheores.api.ATO;

@Mod(value = ATO.MOD_ID, dist = Dist.CLIENT)
//@EventBusSubscriber(modid = AllTheOres.MOD_ID, value = Dist.CLIENT)
public class AllTheOresClient {
    
    public AllTheOresClient(ModContainer container) {
        //container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
