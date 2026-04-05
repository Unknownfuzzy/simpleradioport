package com.codinglitch.simpleradio;

public class ClientConfigBridge {
    public final ClientDeviceConfig microphone = new ClientDeviceConfig();
    public final ClientDeviceConfig speaker = new ClientDeviceConfig();
    public final WireVisualConfig wire = new WireVisualConfig();

    // Fix for MixinLocalPlayer errors
    public final SpeedConfig transceiver = new SpeedConfig(0.8);
    public final SpeedConfig walkie_talkie = new SpeedConfig(0.9);

    public static class ClientDeviceConfig {
        public int particleInterval = 5;
    }

    public static class WireVisualConfig {
        public double baseSag = 0.1;
        public double distanceSag = 0.02;
    }

    public static class SpeedConfig {
        public double transceiverSlow;
        public double walkieTalkieSlow;

        public SpeedConfig(double val) {
            this.transceiverSlow = val;
            this.walkieTalkieSlow = val;
        }
    }
}