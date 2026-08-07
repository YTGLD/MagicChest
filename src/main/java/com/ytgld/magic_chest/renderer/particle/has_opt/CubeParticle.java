package com.ytgld.magic_chest.renderer.particle.has_opt;

import com.ytgld.chest_item.renderer.light.Light;
import com.ytgld.magic_chest.renderer.MagicRender;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class CubeParticle extends SingleQuadParticle {
    public boolean isLight= false;
    public float size = 2;

    private CubeParticle(ClientLevel level, double x, double y, double z, SpriteSet sprite) {
        super(level, x, y, z, sprite.first());
        this.lifetime = 100;
        this.scale(size);
    }

    @Override
    protected int getLightCoords(float a) {
        return 255;
    }
    public void tick() {
        super.tick();

        this.roll+=0.05f;
        this.oRoll+= (float) (0.05) ;
        if (alpha>0) {
            this.alpha -= 0.05f;
        }
        if (alpha <= 0.0) {
            this.remove();
        }
    }

    @Override
    protected @NotNull Layer getLayer() {
        return new Layer(true, TextureAtlas.LOCATION_PARTICLES, MagicRender.TRANSLUCENT_PARTICLE);
    }
    public record Provider(SpriteSet sprite) implements ParticleProvider<CubeOption> {
        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }
        public SpriteSet sprite() {
            return this.sprite;
        }

        @Override
        public @NotNull Particle createParticle(CubeOption simpleParticleType, ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5, RandomSource textureAtlasSprite) {
            CubeParticle particle = new CubeParticle(clientLevel, v,v1,v2,sprite);
            particle.setSpriteFromAge(this.sprite);

            int color = simpleParticleType.getColor();
            int as = (color >> 24) & 0xFF;
            int rs = (color >> 16) & 0xFF;
            int gs = (color >> 8) & 0xFF;
            int bs = color & 0xFF;

            particle.setColor(rs / 255f,gs / 255f,bs / 255f);
            particle.isLight = simpleParticleType.isLight();
            particle.scale(simpleParticleType.getSize());

            particle.setParticleSpeed(simpleParticleType.getVec3().x,simpleParticleType.getVec3().y,simpleParticleType.getVec3().z);
            return particle;
        }
    }
}
