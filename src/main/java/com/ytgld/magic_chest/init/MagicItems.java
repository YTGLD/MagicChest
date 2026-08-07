package com.ytgld.magic_chest.init;

import com.ytgld.chest_item.renderer.light.Light;
import com.ytgld.magic_chest.TheMagicChest;
import com.ytgld.magic_chest.item.BaseSoul;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MagicItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheMagicChest.MODID);

    public static final DeferredItem<Item> BloodSoul_ = ITEMS.registerItem("blood_soul",
            (properties)-> new BaseSoul(properties, Light.ARGB.color(255,255,50,100)));

    public static final DeferredItem<Item> SpiritSoul_ = ITEMS.registerItem("spirit_soul",
            (properties)-> new BaseSoul(properties, Light.ARGB.color(255,100,150,250)));

    public static final DeferredItem<Item> CelestialSoul_ = ITEMS.registerItem("celestial_soul",
            (properties)-> new BaseSoul(properties, Light.ARGB.color(255,250,250,100)));

}
