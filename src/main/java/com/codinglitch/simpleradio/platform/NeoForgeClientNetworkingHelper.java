package com.codinglitch.simpleradio.platform;

import com.codinglitch.simpleradio.core.networking.CustomPacket;
import com.codinglitch.simpleradio.platform.services.ClientNetworkingHelper;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeClientNetworkingHelper implements ClientNetworkingHelper {
    @Override
    public void sendToServer(CustomPacket packet) {
        PacketDistributor.sendToServer(packet);
    }
}
