package com.codinglitch.simpleradio.platform;

import com.codinglitch.simpleradio.core.routers.Router;
import com.codinglitch.simpleradio.platform.services.ClientCompatPlatform;
import net.minecraft.client.resources.sounds.SoundInstance;

public class NeoForgeClientCompatPlatform implements ClientCompatPlatform {
    @Override
    public SoundInstance makeSound(Router router, String soundString, float volume, float pitch, float severity, float offset, long seed) {
        return null;
    }
}
