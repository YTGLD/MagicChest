package com.ytgld.magic_chest.other;

import com.ytgld.chest_item.sounds.Sounds;
import com.ytgld.magic_chest.TheMagicChest;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class GenMagicSounds extends SoundDefinitionsProvider {
    public GenMagicSounds(PackOutput output) {
        super(output, TheMagicChest.MODID);
    }

    @Override
    public void registerSounds() {
        this.add(MagicSounds.soul_pickup,
                SoundDefinition.definition().with(
                        sound(TheMagicChest.MODID + ":soul_pickup", SoundDefinition.SoundType.SOUND)
                        .stream(true)
                        .preload(false))
                        .subtitle(TheMagicChest.MODID+".sound.soul_pickup")
                        .replace(true));

    }
}
