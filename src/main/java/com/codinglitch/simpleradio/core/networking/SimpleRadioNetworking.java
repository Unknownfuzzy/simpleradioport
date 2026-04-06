package com.codinglitch.simpleradio.core.networking;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.client.core.SimpleRadioClientNetworking;
import com.codinglitch.simpleradio.core.networking.packets.ClientboundActivityPacket;
import com.codinglitch.simpleradio.core.networking.packets.ClientboundRegisterRouterPacket;
import com.codinglitch.simpleradio.core.networking.packets.ClientboundSpeakSoundPacket;
import com.codinglitch.simpleradio.core.networking.packets.ClientboundWireEffectPacket;
import com.codinglitch.simpleradio.core.networking.packets.ServerboundRadioUpdatePacket;
import com.codinglitch.simpleradio.core.networking.packets.ServerboundRequestRouterPacket;
import com.codinglitch.simpleradio.core.registry.menus.RadiosmitherMenu;
import com.codinglitch.simpleradio.platform.Services;
import com.codinglitch.simpleradio.radio.RadioManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.UUID;

public final class SimpleRadioNetworking {
    private static final String PROTOCOL_VERSION = "1";

    private SimpleRadioNetworking() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);

        registrar.playToServer(ServerboundRadioUpdatePacket.TYPE, ServerboundRadioUpdatePacket.STREAM_CODEC, SimpleRadioNetworking::handleRadioUpdate);
        registrar.playToServer(ServerboundRequestRouterPacket.TYPE, ServerboundRequestRouterPacket.STREAM_CODEC, SimpleRadioNetworking::handleRequestRouter);

        registrar.playToClient(ClientboundActivityPacket.TYPE, ClientboundActivityPacket.STREAM_CODEC, (packet, context) -> SimpleRadioClientNetworking.handleActivityPacket(packet));
        registrar.playToClient(ClientboundRegisterRouterPacket.TYPE, ClientboundRegisterRouterPacket.STREAM_CODEC, (packet, context) -> SimpleRadioClientNetworking.handleRegisterRouter(packet));
        registrar.playToClient(ClientboundSpeakSoundPacket.TYPE, ClientboundSpeakSoundPacket.STREAM_CODEC, (packet, context) -> SimpleRadioClientNetworking.handleSpeakSound(packet));
        registrar.playToClient(ClientboundWireEffectPacket.TYPE, ClientboundWireEffectPacket.STREAM_CODEC, (packet, context) -> SimpleRadioClientNetworking.handleWireEffect(packet));
    }

    private static void handleRadioUpdate(ServerboundRadioUpdatePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (!RadioManager.getInstance().frequencies().check(packet.frequency())) {
                return;
            }

            AbstractContainerMenu menu = player.containerMenu;
            if (menu instanceof RadiosmitherMenu radiosmitherMenu) {
                if (!menu.stillValid(player)) {
                    CommonSimpleRadio.debug("Player {} interacted with invalid menu {}", player, menu);
                    return;
                }
                radiosmitherMenu.updateTinkering(packet.frequency(), packet.modulation());
            }
        });
    }

    private static void handleRequestRouter(ServerboundRequestRouterPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            UUID reference = packet.reference();
            String routerType = packet.routerType();
            short mapping = packet.mapping();

            short identifier = RadioManager.getInstance().getIdentifier(router ->
                    reference.equals(router.getReference()) && router.getClass().getSimpleName().equals(routerType)
            );

            if (identifier == Short.MAX_VALUE) {
                CommonSimpleRadio.warn("We could not find the {} with reference {} for mapping {}!", routerType, reference, mapping);
                return;
            }

            Services.NETWORKING.sendToPlayer(player, new ClientboundRegisterRouterPacket(mapping, identifier));
        });
    }
}
