package com.ytgld.magic_chest.init;

import com.ytgld.chest_item.items.InitItems;
import com.ytgld.chest_item.items.memory.MemoryItems;
import com.ytgld.chest_item.items.reinforced.ReinforcedItems;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;

public class MagicTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "chest_item");
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tab = CREATIVE_MODE_TABS.register(TheMagicChest.MODID, () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder().title(Component.translatable("itemGroup.magic_chest"));
        Item item = Items.RESPAWN_ANCHOR;
        return builder.icon(item::getDefaultInstance).displayItems((parameters, output) -> {
            output.accept(MagicItems.BloodSoul_);
            output.accept(MagicItems.CelestialSoul_);
            output.accept(MagicItems.SpiritSoul_);
            output.accept(MagicItems.DeathSoul_);
            output.accept(MagicItems.MagicSoul_);
        }).build();
    });;
}
