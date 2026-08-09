package com.ytgld.magic_chest.item.tool;

import com.ytgld.magic_chest.init.MagicItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class DecaySickle extends Item {
    public DecaySickle(Properties properties) {
        super(properties.sword(ToolMaterial.IRON, 3.0F, -2.6F));
    }
    public static boolean isSickle(Player player){
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(MagicItems.DecaySickle_.asItem());
    }


}

