package com.ytgld.magic_chest.event.handler;

import com.ytgld.magic_chest.entity.TheSpirit;
import com.ytgld.magic_chest.init.MagicItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SpiritSoulHandler {
    public static void event(LivingDamageEvent.Post event){
        if (event.getSource().getEntity() instanceof Player player) {
            ArrayList<Item> soul = soul();
            Item item = soul.get(player.getRandom().nextInt((soul.size())));
            TheSpirit spirit = new TheSpirit(player.level(),event.getEntity().getX(),
                    event.getEntity().getY(),event.getEntity().getZ(), item.getDefaultInstance());
            spirit.setThrower(player);
            player.level().addFreshEntity(spirit);
        }
    }

    private static ArrayList<Item> soul(){
        ArrayList<Item> list = new ArrayList<>();
        list.add(MagicItems.BloodSoul_.asItem());
        list.add(MagicItems.CelestialSoul_.asItem());
        list.add(MagicItems.SpiritSoul_.asItem());
        return list;
    }
}
