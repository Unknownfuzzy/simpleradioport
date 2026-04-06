package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, CommonSimpleRadio.ID);

    public static final SimpleParticleType SPEAK_RING = register("speak_ring", new SimpleParticleType(true));
    public static final SimpleParticleType SPEAK_LINE = register("speak_line", new SimpleParticleType(true));
    public static final SimpleParticleType LISTEN = register("listen", new SimpleParticleType(true));

    private static <T extends ParticleType<?>> T register(String name, T type) {
        PARTICLES.register(name, () -> type);
        return type;
    }
}
