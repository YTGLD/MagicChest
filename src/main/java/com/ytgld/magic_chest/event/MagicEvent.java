package com.ytgld.magic_chest.event;

import com.ytgld.magic_chest.event.handler.CounterHandler;
import com.ytgld.magic_chest.event.handler.CraftingHandler;
import com.ytgld.magic_chest.event.handler.SpiritSoulHandler;
import com.ytgld.magic_chest.renderer.CounterRender;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class MagicEvent {

    public static int time = 0;

    @SubscribeEvent
    public void event(ClientTickEvent.Pre event) {
        ++time;
        CounterRender.tick(event);
    }
    @SubscribeEvent
    public void event(EntityTickEvent.Post event) {
        CounterHandler.event(event);
    }
    @SubscribeEvent
    public void event(PlayerEvent.ItemCraftedEvent event){
        CraftingHandler.onCraft(event);
    }
    @SubscribeEvent
    public void event(LivingDamageEvent.Pre event){
        CounterHandler.event(event);
    }
    @SubscribeEvent
    public void event(LivingDeathEvent event){
        SpiritSoulHandler.event(event);
    }
}
