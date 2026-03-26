package net.allthemods.alltheores;


import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATOMekanismRegistry;
import net.allthemods.alltheores.registry.ATORegistry;
import net.allthemods.alltheores.registry.ATORegisters;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class AllTheOres {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public AllTheOres(IEventBus modEventBus, ModContainer modContainer) {
        // Register the deferred registers (ATORegisters) early so static inits that reference them don't run unregistered
        ATORegisters.BLOCKS.register(modEventBus);
        ATORegisters.ITEMS.register(modEventBus);
        ATORegisters.FLUID_ITEMS.register(modEventBus);
        ATORegisters.FLUID_BLOCKS.register(modEventBus);
        ATORegisters.FLUID_TYPES.register(modEventBus);
        ATORegisters.FLUIDS.register(modEventBus);

        // Now it's safe to reference ATORegistry (which uses ATORegisters)
        ATORegistry.init();

        if (ModList.get().isLoaded("mekanism")) {
           // ATOMekanismRegistry.SLURRYS.register(modEventBus);
        }
        Reference.CREATIVE_TABS.register(modEventBus);
        setupLogFilter();
    }

    private static void setupLogFilter() {
        var rootLogger = LogManager.getRootLogger();
        if (rootLogger instanceof org.apache.logging.log4j.core.Logger logger) {
            logger.addFilter(new SetBlockMessageFilter());
        } else {
            LOGGER.error("Registration failed with unexpected class: {}", rootLogger.getClass());
        }
    }

}
