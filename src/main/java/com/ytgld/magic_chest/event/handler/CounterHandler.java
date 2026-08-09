package com.ytgld.magic_chest.event.handler;

import com.ytgld.magic_chest.init.MagicAttribute;
import com.ytgld.magic_chest.item.tool.DecaySickle;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class CounterHandler {
    public static void event(EntityTickEvent.Post event){
        if (event.getEntity() instanceof Player player) {
            int score = ScoreConsecutiveVictoriesHandler.getNow(player);
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
                player.setData(MagicAttribute.score_consecutive_victories.get(),0);
            }
            if (player.tickCount % 200 == 1) {
                int down = score - 1;
                if (down < 0) {
                    down = 0;
                }
                player.setData(MagicAttribute.score_consecutive_victories.get(),down);
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
            int score = ScoreConsecutiveVictoriesHandler.getNow(player);
            ScoreConsecutiveVictoriesHandler.addScore(player,score);
            bounces = ScoreConsecutiveVictoriesHandler.eventAddDamage(player,bounces,score);

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

    public static class ScoreConsecutiveVictoriesHandler{
        public static float eventAddDamage(Player player,float damage,int score) {
            if (canAddDamage(player, score)) {
                float damageBaseAdd = 0.1f;
                float damageAdd = (float) Math.sqrt(score - HowValueToAddDamage(player));
                return damage + (damageBaseAdd * damageAdd);
            }
            return damage;
        }
        public static void addScore(Player player, int score){
            int max = maxValue(player);
            int newSize = score + 1;
            if (newSize > max) {
                newSize = max;
            }
            player.setData(MagicAttribute.score_consecutive_victories.get(),newSize);
        }
        public static int HowValueToAddDamage(Player player){
            return 5;
        }
        public static int maxValue(Player player){
            return 21;
        }
        public static int getNow(Player player){
            return player.getData(MagicAttribute.score_consecutive_victories);
        }

        public static int HowDifference(Player player){
            return Math.min(0,getNow(player) - HowValueToAddDamage(player));
        }
        public static boolean canAddDamage(Player player,int score){
            return score > HowValueToAddDamage(player);
        }
    }
}
