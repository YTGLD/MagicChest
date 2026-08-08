package com.ytgld.magic_chest.item.soul;

import com.ytgld.magic_chest.entity.TheSpirit;
import com.ytgld.magic_chest.init.MagicData;
import com.ytgld.magic_chest.init.MagicItems;
import com.ytgld.magic_chest.item.CommonItem;
import com.ytgld.magic_chest.other.SetSoulData;
import com.ytgld.magic_chest.other.ToolTipSpiritItem;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

public class SoulBottle extends CommonItem {
    public SoulBottle(Properties properties) {
        super(properties.stacksTo(1));
    }

    public static boolean addSoul(Player player, TheSpirit spirit){
        InventoryMenu inventory = player.inventoryMenu;
        NonNullList<Slot> slots = inventory.slots;
        for (Slot slot : slots) {
            ItemStack stack = slot.getItem();
            if (stack.is(MagicItems.SoulBottle_.asItem())) {
                if (stack.get(MagicData.soulMap) == null) {
                    stack.set(MagicData.soulMap, new SetSoulData(new HashMap<>()));
                }
                Item spiritItem = spirit.getItem().getItem();
                String name = BuiltInRegistries.ITEM.getKey(spiritItem).toString();
                SetSoulData setSoulData = stack.get(MagicData.soulMap);
                if (setSoulData != null) {
                    Integer integer = setSoulData.soulMap().get(name);
                    if (integer == null) {
                        integer = 0;
                    }
                    setSoulData.soulMap().put(name, integer + 1);
                    spirit.setItem(ItemStack.EMPTY);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        SetSoulData setSoulData = itemStack.get(MagicData.soulMap);
        if (setSoulData == null) {
            return Optional.empty();
        }
        return Optional.of(new ToolTipSpiritItem(setSoulData, itemStack));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, builder, tooltipFlag);
        builder.accept(Component.translatable("item.magic_chest.soul_bottle.tooltip.1").setStyle(Style.EMPTY.withColor(textColor())));
    }
}
