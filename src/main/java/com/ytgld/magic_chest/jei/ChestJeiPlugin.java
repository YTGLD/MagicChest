package com.ytgld.magic_chest.jei;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.init.MagicItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

@JeiPlugin
public class ChestJeiPlugin implements IModPlugin {
    @Override
    public @NonNull Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(TheMagicChest.MODID, "soul");
    }

    @Override
    public void registerRecipeCatalysts(
            IRecipeCatalystRegistration registration
    ) {
        registration.addCraftingStation(
                ChestRecipeCategory.TYPE,
                MagicItems.SoulBottle_.get()
        );
    }

    @Override
    public void registerCategories(
            IRecipeCategoryRegistration registry
    ) {
        registry.addRecipeCategories(
                new ChestRecipeCategory()
        );
    }
    @Override
    public void registerRecipes(
            IRecipeRegistration registration
    ) {

        registration.addRecipes(
                ChestRecipeCategory.TYPE,
                ModRecipeCache.SOUL_RECIPES
        );
    }
}
