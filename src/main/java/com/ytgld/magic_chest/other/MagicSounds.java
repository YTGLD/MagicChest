package com.ytgld.magic_chest.other;

import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MagicSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, TheMagicChest.MODID);
    public static final Holder<SoundEvent> soul_pickup = REGISTRY.register("soul_pickup",SoundEvent::createVariableRangeEvent);
}
