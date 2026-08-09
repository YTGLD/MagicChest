package com.ytgld.magic_chest.event;

import com.ytgld.magic_chest.event.handler.CraftingHandler;
import com.ytgld.magic_chest.event.handler.SpiritSoulHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class MagicEvent {

    public static int time = 0;

    @SubscribeEvent
    public void event(ClientTickEvent.Pre event) {
        ++time;
    }

    @SubscribeEvent
    public void event(PlayerEvent.ItemCraftedEvent event){
        CraftingHandler.onCraft(event);
    }
    @SubscribeEvent
    public void event(LivingDamageEvent.Post event){
    }
    @SubscribeEvent
    public void event(LivingDeathEvent event){
        SpiritSoulHandler.event(event);
    }
}
