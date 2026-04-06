package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.SimpleRadioLibrary;
import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.central.FrequencingType;
import com.codinglitch.simpleradio.central.ConfigHolder;
import com.codinglitch.simpleradio.ServerConfigBridge.DeviceConfig;
import java.util.Optional;

public class SimpleRadioFrequencing {

    // Helper method to wrap DeviceConfig into the ConfigHolder interface
    private static ConfigHolder wrap(DeviceConfig config) {
        return new ConfigHolder() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> Optional<T> getEntry(String name) {
                return switch (name) {
                    case "receptionPower" -> Optional.of((T) Integer.valueOf(config.receptionPower));
                    case "receptionFloor" -> Optional.of((T) Integer.valueOf(config.receptionFloor));
                    case "antennaAptitude" -> Optional.of((T) Integer.valueOf(config.antennaAptitude));
                    case "transmissionPowerFM" -> Optional.of((T) Integer.valueOf(config.transmissionPowerFM));
                    case "diminishThresholdFM" -> Optional.of((T) Integer.valueOf(config.diminishThresholdFM));
                    case "transmissionPowerAM" -> Optional.of((T) Integer.valueOf(config.transmissionPowerAM));
                    case "diminishThresholdAM" -> Optional.of((T) Integer.valueOf(config.diminishThresholdAM));
                    case "diminishmentMethod" -> Optional.of((T) config.diminishmentMethod);
                    case "transmissionDiminishment" -> Optional.of((T) Double.valueOf(config.transmissionDiminishment));
                    case "listeningRange" -> Optional.of((T) Integer.valueOf(config.listeningRange));
                    case "speakingRange" -> Optional.of((T) Integer.valueOf(config.speakingRange));
                    case "redstonePolling" -> Optional.of((T) Integer.valueOf(config.redstonePolling));
                    case "enabled" -> Optional.of((T) Boolean.valueOf(config.enabled));
                    default -> Optional.empty();
                };
            }
        };
    }

    public static FrequencingType WALKIE_TALKIE = FrequencingRegistry.register(
            CommonSimpleRadio.id("walkie_talkie"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.walkie_talkie))
    );

    public static FrequencingType TRANSCEIVER = FrequencingRegistry.register(
            CommonSimpleRadio.id("transceiver"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.transceiver))
    );

    public static FrequencingType TRANSMITTER = FrequencingRegistry.register(
            CommonSimpleRadio.id("transmitter"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.transmitter))
    );

    public static FrequencingType RECEIVER = FrequencingRegistry.register(
            CommonSimpleRadio.id("receiver"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.receiver))
    );

    public static FrequencingType RADIO = FrequencingRegistry.register(
            CommonSimpleRadio.id("radio"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.radio))
    );

    public static FrequencingType WIRE = FrequencingRegistry.register(
            CommonSimpleRadio.id("wire"),
            FrequencingRegistry.fromConfig(wrap(SimpleRadioLibrary.SERVER_CONFIG.wire))
    );

    public static void load() {}
}
