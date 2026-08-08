package com.ytgld.magic_chest.item;

import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.init.MagicItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class GatherItemModel extends ModelProvider {
    public GatherItemModel(PackOutput output) {
        super(output, TheMagicChest.MODID);
    }
    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(MagicItems.BloodSoul_.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MagicItems.SpiritSoul_.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MagicItems.CelestialSoul_.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MagicItems.DeathSoul_.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MagicItems.MagicSoul_.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MagicItems.SoulBottle_.asItem(), ModelTemplates.FLAT_ITEM);

    }
}
