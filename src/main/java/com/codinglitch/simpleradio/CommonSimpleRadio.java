package com.codinglitch.simpleradio;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonSimpleRadio {
    public static final String ID = "simpleradio";
    private static final Logger LOGGER = LogManager.getLogger(ID);

    public static void init(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(CommonSimpleRadio::registerPayloads);
        NeoForge.EVENT_BUS.addListener(CommonSimpleRadio::onRegisterCommands);
    }

    private static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ID).versioned("1.0.0");
        // Packets will be registered here
    }

    private static void onRegisterCommands(RegisterCommandsEvent event) {
        // /radio commands will be registered here
    }
}
