package com.codinglitch.simpleradio.core.registry.entities;

import com.codinglitch.simpleradio.SimpleRadioApi;
import com.codinglitch.simpleradio.central.Socket;
import com.codinglitch.simpleradio.central.Wiring;
import com.codinglitch.simpleradio.core.registry.SimpleRadioEntities;
import com.codinglitch.simpleradio.core.routers.Router;
import com.codinglitch.simpleradio.radio.Source;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Wire extends Entity implements Wiring {
    private static final EntityDataAccessor<Optional<UUID>> FROM = SynchedEntityData.defineId(Wire.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> TO = SynchedEntityData.defineId(Wire.class, EntityDataSerializers.OPTIONAL_UUID);

    public final List<Effect> effectList = new ArrayList<>();
    private int demiseTicks = -1;
    private int missingEndpointTicks = 0;

    public Wire(EntityType<? extends Wire> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    public static Wire connect(Object fromSocket, Object toSocket, Level level) {
        if (!(fromSocket instanceof Socket from) || !(toSocket instanceof Socket to)) {
            return null;
        }

        Router fromRouter = from.getRouter();
        Router toRouter = to.getRouter();
        if (fromRouter == null || toRouter == null) {
            return null;
        }
        if (fromRouter.getReference().equals(toRouter.getReference())) {
            return null;
        }
        if (!from.canConnectTo(to) || !to.canConnectTo(from)) {
            return null;
        }
        if (from.hasWire(fromRouter.getReference(), toRouter.getReference())) {
            return null;
        }

        Wire wire = new Wire(SimpleRadioEntities.WIRE.get(), level);
        wire.setFrom(fromRouter);
        wire.setTo(toRouter);
        wire.updateAnchorPosition();

        from.connect(wire);
        to.connect(wire);
        level.addFreshEntity(wire);

        return wire;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FROM, Optional.empty());
        builder.define(TO, Optional.empty());
    }

    @Override
    public Router transport(Router source) {
        if (source == null) {
            return null;
        }
        UUID sourceRef = source.getReference();
        if (getFrom().filter(sourceRef::equals).isPresent()) {
            return getToRouter();
        }
        if (getTo().filter(sourceRef::equals).isPresent()) {
            return getFromRouter();
        }
        return null;
    }

    @Override
    public void relay(Source source, Socket originSocket) {
        Router origin = originSocket.getRouter();
        Router destination = transport(origin);
        if (origin == null || destination == null) {
            return;
        }

        source.travel(origin, destination, this);
        destination.accept(source);
    }

    @Override
    public float getLength() {
        Router fromRouter = getFromRouter();
        Router toRouter = getToRouter();
        if (fromRouter == null || toRouter == null) {
            return 0;
        }
        return (float) fromRouter.getConnectionPosition().distanceTo(toRouter.getConnectionPosition());
    }

    @Override
    public UUID getReference() {
        return getUUID();
    }

    @Nullable
    @Override
    public Router getFromRouter() {
        if (level() == null) {
            return null;
        }
        return getFrom().map(reference -> SimpleRadioApi.getRouterSided(reference, level().isClientSide)).orElse(null);
    }

    @Override
    public Optional<UUID> getFrom() {
        return this.entityData.get(FROM);
    }

    @Nullable
    @Override
    public String getFromType() {
        Router router = getFromRouter();
        return router == null ? null : router.getClass().getSimpleName();
    }

    public void setFrom(UUID uuid) {
        this.entityData.set(FROM, Optional.ofNullable(uuid));
    }

    @Override
    public void setFrom(Router from) {
        setFrom(from == null ? null : from.getReference());
        attachToRouter(from);
        updateAnchorPosition();
    }

    @Nullable
    @Override
    public Router getToRouter() {
        if (level() == null) {
            return null;
        }
        return getTo().map(reference -> SimpleRadioApi.getRouterSided(reference, level().isClientSide)).orElse(null);
    }

    @Override
    public Optional<UUID> getTo() {
        return this.entityData.get(TO);
    }

    @Nullable
    @Override
    public String getToType() {
        Router router = getToRouter();
        return router == null ? null : router.getClass().getSimpleName();
    }

    public void setTo(UUID uuid) {
        this.entityData.set(TO, Optional.ofNullable(uuid));
    }

    @Override
    public void setTo(Router to) {
        setTo(to == null ? null : to.getReference());
        attachToRouter(to);
        updateAnchorPosition();
    }

    @Override
    public boolean isValid() {
        return !isRemoved()
                && getFrom().isPresent()
                && getTo().isPresent();
    }

    @Override
    public void burnOut() {
        cleanUp();
    }

    @Override
    public void shortCircuit(Vector3f at) {
        cleanUp();
    }

    @Override
    public void shortCircuit() {
        cleanUp();
    }

    @Override
    public void queueDemise(int time, float position) {
        this.demiseTicks = Math.max(this.demiseTicks, time);
    }

    @Override
    public void cleanUp() {
        detachFromRouter(getFromRouter());
        detachFromRouter(getToRouter());
        discard();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("from")) this.setFrom(tag.getUUID("from"));
        if (tag.contains("to")) this.setTo(tag.getUUID("to"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        this.getFrom().ifPresent(uuid -> tag.putUUID("from", uuid));
        this.getTo().ifPresent(uuid -> tag.putUUID("to", uuid));
    }

    @Override
    public void tick() {
        super.tick();

        if (!isValid()) {
            cleanUp();
            return;
        }

        attachToRouter(getFromRouter());
        attachToRouter(getToRouter());
        updateAnchorPosition();

        Router fromRouter = getFromRouter();
        Router toRouter = getToRouter();
        if (!level().isClientSide) {
            if ((fromRouter != null && !fromRouter.isValid()) || (toRouter != null && !toRouter.isValid())) {
                cleanUp();
                return;
            }

            if (fromRouter == null || toRouter == null) {
                if (++missingEndpointTicks > 100) {
                    cleanUp();
                    return;
                }
            } else {
                missingEndpointTicks = 0;
            }
        }

        if (this.demiseTicks >= 0 && --this.demiseTicks <= 0) {
            cleanUp();
        }
    }

    private void attachToRouter(@Nullable Router router) {
        if (!(router instanceof Socket socket)) {
            return;
        }
        boolean attached = socket.getWires().stream().anyMatch(wire -> wire.getReference().equals(getReference()));
        if (!attached) {
            socket.connect(this);
        }
    }

    private void detachFromRouter(@Nullable Router router) {
        if (router instanceof Socket socket) {
            socket.disconnect(this);
        }
    }

    private void updateAnchorPosition() {
        Router fromRouter = getFromRouter();
        Router toRouter = getToRouter();

        Vec3 anchor;
        if (fromRouter != null && toRouter != null) {
            anchor = fromRouter.getConnectionPosition().lerp(toRouter.getConnectionPosition(), 0.5d);
        } else if (fromRouter != null) {
            anchor = fromRouter.getConnectionPosition();
        } else if (toRouter != null) {
            anchor = toRouter.getConnectionPosition();
        } else {
            return;
        }

        setPos(anchor.x, anchor.y, anchor.z);
    }

    @Override public boolean shouldRender(double x, double y, double z) { return true; }

    public static class Effect {
        public long progress;
        public int color;
        // FIX: Added for WireRenderer.java:79 and SimpleRadioClientNetworking.java:55
        public int direction;

        public Effect() {}
        public Effect(long progress, int color, int direction) {
            this.progress = progress;
            this.color = color;
            this.direction = direction;
        }
    }
}
