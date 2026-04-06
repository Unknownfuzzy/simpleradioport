package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, CommonSimpleRadio.ID);

    public static final SoundEvent RADIO_OPEN = register("radio_open");
    public static final SoundEvent RADIO_CLOSE = register("radio_close");

    public static final SoundEvent SHORT_CIRCUIT = register("short_circuit");

    public static final SoundEvent TILT_MICROPHONE = register("tilt_microphone");
    public static final SoundEvent PRESS_MICROPHONE = register("press_microphone");

    public static final SoundEvent SPIN_INSULATOR = register("spin_insulator");

    private static SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(CommonSimpleRadio.id(name));
        SOUND_EVENTS.register(name, () -> soundEvent);
        return soundEvent;
    }
}
