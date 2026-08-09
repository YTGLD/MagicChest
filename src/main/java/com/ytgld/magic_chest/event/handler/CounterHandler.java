package com.ytgld.magic_chest.event.handler;

import com.ytgld.magic_chest.init.MagicAttribute;
import com.ytgld.magic_chest.item.tool.DecaySickle;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class CounterHandler {
    public static void event(EntityTickEvent.Post event){
        if (event.getEntity() instanceof Player player) {
            int max = (int) player.getAttributeValue(MagicAttribute.counter);
            int now =   player.getData(MagicAttribute.counter_data);
            int cooldown =   player.getData(MagicAttribute.counter_data_cooldown);
            if (max > 0 && cooldown <= 0) {
                if (player.tickCount % 100 == 1) {
                    int add = now + 2;
                    if (add > max) {
                        add = max;
                    }
                    player.setData(MagicAttribute.counter_data.get(),add);
                }
            }
            if (cooldown > 0) {
                player.setData(MagicAttribute.counter_data_cooldown.get(),cooldown - 1);
            }
        }
    }
    public static void event(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof Player player) {
            int max = (int) player.getAttributeValue(MagicAttribute.counter);
            int now = player.getData(MagicAttribute.counter_data);
            if (max <= 0){
                return;
            }
            float bounces = 1.3f;
            if (DecaySickle.isSickle(player)) {
                bounces += 0.2f;
            }
            event.setNewDamage(event.getNewDamage() * bounces);
            int newNow = now - 1;
            if (newNow < 0) {
                player.setData(MagicAttribute.counter_data_cooldown,applyCooldown(player));
                newNow = 0;
            }
            player.setData(MagicAttribute.counter_data,newNow);
        }
    }
    private static int applyCooldown(Player player){
        return 200;
    }
}
