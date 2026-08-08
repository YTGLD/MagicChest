package com.ytgld.magic_chest.jei;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.crafting.SoulRecipe;
import com.ytgld.magic_chest.init.MagicItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.common.Internal;
import mezz.jei.common.gui.textures.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ChestRecipeCategory implements IRecipeCategory<SoulRecipe> {
    public static final IRecipeType<SoulRecipe> TYPE =
            IRecipeType.create(
                    TheMagicChest.MODID,
                    "crafting",
                    SoulRecipe.class
            );
    private final IDrawable theRecipeArrow;
    private final IDrawable theSlot;
    public ChestRecipeCategory(){
        Textures textures = Internal.getTextures();
        this.theRecipeArrow = textures.getRecipeArrow();
        this.theSlot = textures.getSlot();
    }
    @Override
    public IRecipeType<SoulRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("magic_chest.jei.recipe");
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 100;
    }


    @Override
    public void draw(SoulRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        theRecipeArrow.draw(guiGraphics,30,45);
        int offsetItem = 0;
        for (Ingredient item : recipe.ingredients){
            for (Holder<Item> stack : item.getValues()) {
                if (stack.value() == MagicItems.SoulBottle_.asItem()) {
                    continue;
                }
                offsetItem += 16;
                theSlot.draw(guiGraphics,offsetItem - 16,36);
            }
        }

        int offset = 0;
        for (String string : recipe.getSoulCost().keySet()) {
            Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(string));
            if (item == MagicItems.SoulBottle_.asItem()) {
                continue;
            }
            offset += 16;
            theSlot.draw(guiGraphics,offset - 16,20);

        }
        theSlot.draw(guiGraphics,80, 40);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return new ItemIDrawable();
    }

    @Override
    public void setRecipe(
            IRecipeLayoutBuilder builder,
            SoulRecipe page,
            IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
                .add(MagicItems.SoulBottle_.asItem());

        int offset = 0;
        for (String string : page.getSoulCost().keySet()) {
            Item item = BuiltInRegistries.ITEM.getValue(Identifier.parse(string));
            if (item == MagicItems.SoulBottle_.asItem()) {
                continue;
            }
            offset += 16;
            builder.addSlot(RecipeIngredientRole.INPUT, offset - 16, 20)
                    .add(new ItemStack(item,page.getSoulCost().get(string)));
        }
        int offsetItem = 0;
        for (Ingredient item : page.ingredients){
            for (Holder<Item> stack : item.getValues()) {
                if (stack.value() == MagicItems.SoulBottle_.asItem()) {
                    continue;
                }
                offsetItem += 16;
                builder.addSlot(RecipeIngredientRole.INPUT, offsetItem - 16, 36)
                        .add(stack.value());
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 40)
                .add(page.result.value());
    }

    public static class ItemIDrawable implements IDrawable {

        @Override
        public int getWidth() {
            return 16;
        }

        @Override
        public int getHeight() {
            return 16;
        }

        @Override
        public void draw(GuiGraphicsExtractor guiGraphicsExtractor, int i, int i1) {
            guiGraphicsExtractor.item(MagicItems.SoulBottle_.get().getDefaultInstance(),i,i1);
        }
    }
}