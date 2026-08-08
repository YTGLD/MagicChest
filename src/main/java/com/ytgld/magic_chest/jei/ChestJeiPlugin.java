package com.ytgld.magic_chest.jei;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.crafting.SoulDisplayRecipe;
import com.ytgld.magic_chest.crafting.SoulRecipe;
import com.ytgld.magic_chest.init.MagicItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
