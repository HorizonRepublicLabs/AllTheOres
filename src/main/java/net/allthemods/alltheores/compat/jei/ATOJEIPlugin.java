package net.allthemods.alltheores.compat.jei;

//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.neoforge.NeoForgeTypes;
//import mezz.jei.api.registration.IRecipeRegistration;
import net.allthemods.alltheores.infos.Reference;
import net.allthemods.alltheores.registry.ATORegistry;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

//@JeiPlugin
public class ATOJEIPlugin /*implements IModPlugin */{
/*
    @Override
    public @NotNull Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(Reference.MOD_ID, Reference.MOD_ID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!Reference.enableFluids) registration.getIngredientManager().removeIngredientsAtRuntime(NeoForgeTypes.FLUID_STACK, ATORegistry.FLUIDS.getEntries().stream().map(entry -> new FluidStack(entry.get(), FluidType.BUCKET_VOLUME)).collect(Collectors.toList()));
    }*/
}
