package com.codinglitch.simpleradio.mixin;

import com.codinglitch.simpleradio.SimpleRadioLibrary;
import com.codinglitch.simpleradio.core.registry.items.TransceiverItem;
import com.codinglitch.simpleradio.core.registry.items.WalkieTalkieItem;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import com.mojang.authlib.GameProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LocalPlayer.class)
public abstract class MixinLocalPlayer extends AbstractClientPlayer {

    public MixinLocalPlayer(ClientLevel level, GameProfile profile) {
        super(level, profile);
    }

    @Shadow public abstract boolean isUsingItem();

    @Unique
    private boolean simpleradio$willSlow_transceiverSlowing(LocalPlayer player, Operation<Boolean> original) {
        if (this.isUsingItem()) {
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());

            // Pointing to the inner class inside SimpleRadioLibrary
            SimpleRadioLibrary.ClientConfigBridge config = SimpleRadioLibrary.CLIENT_CONFIG;

            if (config != null) {
                if (stack.getItem() instanceof TransceiverItem) {
                    return config.transceiver.transceiverSlow < 1.0;
                } else if (stack.getItem() instanceof WalkieTalkieItem) {
                    return config.walkie_talkie.walkieTalkieSlow < 1.0;
                }
            }
        }
        return original.call(player);
    }

    @WrapOperation(
            method = "aiStep",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z")
    )
    private boolean simpleradio$aiStep_transceiverSlowing(LocalPlayer instance, Operation<Boolean> original) {
        return simpleradio$willSlow_transceiverSlowing(instance, original);
    }

    @WrapOperation(
            method = "canStartSprinting",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z")
    )
    private boolean simpleradio$canStartSprinting_transceiverSlowing(LocalPlayer instance, Operation<Boolean> original) {
        return simpleradio$willSlow_transceiverSlowing(instance, original);
    }
}