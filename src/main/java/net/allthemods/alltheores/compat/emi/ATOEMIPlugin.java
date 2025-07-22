package net.allthemods.alltheores.compat.emi;

import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;

@EmiEntrypoint
public class ATOEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        if (!Reference.enableFluids)
            ATORegistry.FLUIDS.getEntries().stream().map(entry -> EmiStack.of(new FluidStack(entry.get(), FluidType.BUCKET_VOLUME).getFluid())).toList().forEach(registry::removeEmiStacks);
    }
}
