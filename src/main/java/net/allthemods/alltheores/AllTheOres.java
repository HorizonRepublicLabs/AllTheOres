package net.allthemods.alltheores;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;

import net.allthemods.alltheores.api.ATO;
import net.allthemods.alltheores.core.registry.ATORegistry;

import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

@Mod(ATO.MOD_ID)
public class AllTheOres {
    
    public static final Logger LOGGER = LogUtils.getLogger();

    public AllTheOres(final IEventBus bus, final FMLModContainer container) {
        ATORegistry.register(bus);

    }
}
