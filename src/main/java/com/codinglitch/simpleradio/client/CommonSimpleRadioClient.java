package com.codinglitch.simpleradio.client;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.client.core.registry.SimpleRadioModels;
import com.codinglitch.simpleradio.client.core.registry.models.InsulatorModel;
import com.codinglitch.simpleradio.client.core.registry.models.MicrophoneModel;
import com.codinglitch.simpleradio.client.core.registry.models.RadioModel;
import com.codinglitch.simpleradio.client.core.registry.renderers.*;
import com.codinglitch.simpleradio.client.core.registry.screens.RadiosmitherScreen;
import com.codinglitch.simpleradio.core.registry.*;
import com.codinglitch.simpleradio.core.registry.particles.ListenParticle;
import com.codinglitch.simpleradio.core.registry.particles.SpeakLineParticle;
import com.codinglitch.simpleradio.core.registry.particles.SpeakRingParticle;
import com.codinglitch.simpleradio.core.routers.Receiver;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import static com.codinglitch.simpleradio.core.SimpleRadioComponents.REFERENCE;

public class CommonSimpleRadioClient {
    public static final Map<UUID, Boolean> isTransmitting = new HashMap<>();
    private static boolean initialized;

    public static void loadProperties(TriConsumer<Item, ResourceLocation, ClampedItemPropertyFunction> registry) {
        registry.accept(SimpleRadioItems.TRANSCEIVER.get(), CommonSimpleRadio.id("using"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);

        registry.accept(SimpleRadioItems.TRANSCEIVER.get(), CommonSimpleRadio.id("speaking"),
                (stack, level, entity, i) -> {
                    if (!stack.has(REFERENCE)) return 0;
                    Receiver receiver = ClientRadioManager.getInstance().getReceiver(stack.get(REFERENCE));
                    if (receiver == null) return 0;
                    return receiver.getActivityTime() > 0 ? 1 : 0;
                }
        );

        registry.accept(SimpleRadioItems.WALKIE_TALKIE.get(), CommonSimpleRadio.id("using"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);

        registry.accept(SimpleRadioItems.SPUDDIE_TALKIE.get(), CommonSimpleRadio.id("using"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
    }

    public static void loadRenderTypes(BiConsumer<Block, RenderType> registry) {
        registry.accept(SimpleRadioBlocks.RADIOSMITHER.get(), RenderType.cutout());
        registry.accept(SimpleRadioBlocks.ANTENNA.get(), RenderType.cutout());
        registry.accept(SimpleRadioBlocks.RECEIVER.get(), RenderType.cutout());
    }

    public static void loadLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registry) {
        registry.accept(RadioModel.LAYER_LOCATION, RadioModel::createBodyLayer);
        registry.accept(MicrophoneModel.LAYER_LOCATION, MicrophoneModel::createBodyLayer);
        registry.accept(InsulatorModel.LAYER_LOCATION, InsulatorModel::createBodyLayer);
    }

    public interface BlockEntityRendererRegistry {
        <BE extends BlockEntity> void register(BlockEntityType<BE> type, BlockEntityRendererProvider<? super BE> factory);
    }
    public static void loadBlockEntityRenderers(BlockEntityRendererRegistry registry) {
        registry.register(SimpleRadioBlockEntities.RADIO.get(), RadioRenderer::new);
        registry.register(SimpleRadioBlockEntities.FREQUENCER.get(), FrequencerRenderer::new);
        registry.register(SimpleRadioBlockEntities.MICROPHONE.get(), MicrophoneRenderer::new);
        registry.register(SimpleRadioBlockEntities.INSULATOR.get(), InsulatorRenderer::new);
        registry.register(SimpleRadioBlockEntities.TRANSMITTER.get(), TransmitterRenderer::new);
        registry.register(SimpleRadioBlockEntities.RECEIVER.get(), ReceiverRenderer::new);
    }

    public static void loadEntityRenderers() {
        EntityRenderers.register(SimpleRadioEntities.WIRE.get(), WireRenderer::new);
    }

    // -- Screens Registration -- \\
    public interface ScreenRegistry {
        <M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>> void register(MenuType<M> type, MenuScreens.ScreenConstructor<M, S> factory);
    }

    public static void loadScreens(ScreenRegistry registry) {
        // FIX: Use the registry parameter instead of calling MenuScreens.register directly
        registry.register(SimpleRadioMenus.RADIOSMITHER_MENU, RadiosmitherScreen::new);
    }

    @FunctionalInterface
    public interface ParticleProviderRegistry {
        <O extends ParticleOptions> void register(ParticleType<O> type, ParticleEngine.SpriteParticleRegistration<O> registration);
    }
    public static void loadParticles(ParticleProviderRegistry registry) {
        registry.register(SimpleRadioParticles.SPEAK_RING, SpeakRingParticle.Provider::new);
        registry.register(SimpleRadioParticles.SPEAK_LINE, SpeakLineParticle.Provider::new);
        registry.register(SimpleRadioParticles.LISTEN, ListenParticle.Provider::new);
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        SimpleRadioModels.load();
        ClientRadioManager.load();
    }
}
