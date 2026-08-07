package com.ytgld.magic_chest.event;

import com.ytgld.magic_chest.entity.TheSpirit;
import com.ytgld.magic_chest.event.handler.SpiritSoulHandler;
import com.ytgld.magic_chest.init.MagicItems;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class MagicEvent {
    @SubscribeEvent
    public void event(LivingDamageEvent.Post event){
        SpiritSoulHandler.event(event);
    }
}
