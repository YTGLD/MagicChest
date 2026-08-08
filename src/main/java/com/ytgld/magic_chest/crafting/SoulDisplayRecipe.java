package com.ytgld.magic_chest.crafting;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public record SoulDisplayRecipe(
        List<ItemStack> inputs,
        ItemStack output,
        ItemStack catalyst
) {
}