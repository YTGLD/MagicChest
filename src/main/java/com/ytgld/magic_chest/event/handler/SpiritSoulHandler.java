package com.ytgld.magic_chest.event.handler;

import com.ytgld.magic_chest.entity.TheSpirit;
import com.ytgld.magic_chest.init.MagicItems;
import com.ytgld.magic_chest.item.tool.DecaySickle;
import com.ytgld.magic_chest.other.MagicSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;

public class SpiritSoulHandler {
    public static void event(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity livingEntity) {
            if (!DecaySickle.isSickle(player)) {
                return;
            }
            playerSounds(player);
            for (int i = 0; i < 8; i++) {
                ArrayList<Item> soul = soul();
                Item item = soul.get(player.getRandom().nextInt((soul.size())));
                TheSpirit spirit = new TheSpirit(player.level(),livingEntity.getX(),
                        livingEntity.getEyeY(),livingEntity.getZ(), item.getDefaultInstance());

                spirit.setThrower(player);

                spirit.setDeltaMovement(Mth.nextFloat(player.getRandom(),-0.125f,0.125f),0.05,Mth.nextFloat(livingEntity.getRandom(),-0.125f,0.125f));

                player.level().addFreshEntity(spirit);
            }
        }
    }
    private static void playerSounds(Player player){
        player.level().playSound(null,player.blockPosition(), MagicSounds.soul_fly.value(), SoundSource.PLAYERS,1,1);
    }

    private static ArrayList<Item> soul(){
        ArrayList<Item> list = new ArrayList<>();
        list.add(MagicItems.BloodSoul_.asItem());
        list.add(MagicItems.CelestialSoul_.asItem());
        list.add(MagicItems.SpiritSoul_.asItem());
        list.add(MagicItems.MagicSoul_.asItem());
        list.add(MagicItems.DeathSoul_.asItem());
        return list;
    }
}
