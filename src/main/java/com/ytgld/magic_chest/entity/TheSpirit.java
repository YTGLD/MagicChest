package com.ytgld.magic_chest.entity;

import com.ytgld.magic_chest.init.MagicEntitys;
import com.ytgld.magic_chest.item.BaseItem;
import com.ytgld.magic_chest.item.soul.SoulBottle;
import com.ytgld.magic_chest.other.MagicSounds;
import com.ytgld.magic_chest.renderer.particle.has_opt.CubeOption;
import com.ytgld.magic_chest.renderer.particle.has_opt.MagicColorOption;
import com.ytgld.magic_chest.renderer.particle.other.MagicParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class TheSpirit extends ItemEntity {
    public boolean canSee = true;
    public final List<Vec3> trailPositions = new ArrayList<>();

    public TheSpirit(EntityType<? extends TheSpirit> type, Level level) {
        super(type, level);
    }
    public TheSpirit(Level level, double x, double y, double z, ItemStack itemStack) {
        this(MagicEntitys.TheSpirit_.get(), level);
        this.setPos(x, y, z);
        this.setItem(itemStack);
        this.setDeltaMovement(this.random.nextDouble() * 0.2 - 0.1, 0.2, this.random.nextDouble() * 0.2 - 0.1);
        this.lifespan = itemStack.getEntityLifespan(level);
    }

    public int live = 50;
    @Override
    public void tick() {
        super.tick();
        if (canSee) {
            if (getItem().getItem() instanceof BaseItem item) {
                if (this.level() instanceof ServerLevel serverLevel) {
                    float speed = 0.33f;
                    if (tickCount < 50) {
                        speed = 0;
                    }
                    serverLevel.sendParticles(CubeOption.creatParticle(MagicParticles.colorCube.get(),
                            this.getDeltaMovement().scale(speed),true,item.color(),0.125f),getX(),getY(),getZ(),1,0,0,0,0);

                    serverLevel.sendParticles(MagicColorOption.creatParticle(MagicParticles.colorOption.get(),
                            this.getDeltaMovement().scale(speed),true,item.color(),0.35f),getX(),getY(),getZ(),1,0,0,0,0);
                }
            }
        }
        this.noPhysics = true;
        this.setNoGravity(true);
        if (canSee) {
            Entity target = this.getOwner();
            if (target instanceof LivingEntity livingEntity) {
                if (tickCount > 30) {
                    float size = Math.min((tickCount - 30f) / 100f, 0.63f);

                    Vec3 targetPos = livingEntity.position();
                    Vec3 direction = targetPos.subtract(position()).normalize();

                    Vec3 targetVelocity = direction.scale(size)
                            .add(
                                    Math.cos(tickCount / 7.5f) / 20f,
                                    Math.sin(tickCount / 7.5f) / 20f,
                                    0
                            );

                    Vec3 smoothVelocity = getDeltaMovement()
                            .scale(0.9)
                            .lerp(targetVelocity, 0.08);

                    setDeltaMovement(smoothVelocity);
                }
            }
        }else {
            setDeltaMovement(0,0,0);
            live--;
            if (live <= 0) {
                this.discard();
            }
        }
        if (this.tickCount > 200) {
            setCanSee(false);
        }
        if(canSee){
            trailPositions.add(position());
        }
        if(trailPositions.size()>15){
            trailPositions.removeFirst();
        }
    }
    @Override
    public void playerTouch(Player player) {
        if (!SoulBottle.addSoul(player, this)) {
            super.playerTouch(player);
        }
        this.level().playSound(null,this.blockPosition(), MagicSounds.soul_pickup.value(), SoundSource.PLAYERS,1,1);
        setCanSee(false);
        if (getItem().getItem() instanceof BaseItem item) {
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(CubeOption.creatParticle(MagicParticles.colorCube.get(),
                        Vec3.ZERO,true,item.color(),0.66f),getX(),getY(),getZ(),1,0,0,0,0);

                serverLevel.sendParticles(MagicColorOption.creatParticle(MagicParticles.colorOption.get(),
                        Vec3.ZERO,true,item.color(),1.25f),getX(),getY(),getZ(),1,0,0,0,0);
            }
        }
    }
    public void setCanSee(boolean canSee) {
        this.canSee = canSee;

    }

    @Override
    public ProjectileDeflection deflection(Projectile projectile) {
        return ProjectileDeflection.NONE;
    }

    public List<Vec3> getTrailPositions() {
        return trailPositions;
    }
}
