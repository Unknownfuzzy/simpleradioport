package com.codinglitch.simpleradio.radio.effects;

public abstract class AudioEffect {
    public float severity;
    public float volume;

    public short[] apply(short[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = clampSample(data[i] * volume);
        }

        return data;
    }

    protected short clampSample(float sample) {
        return (short) Math.max(Short.MIN_VALUE, Math.min(Math.round(sample), Short.MAX_VALUE));
    }
}
