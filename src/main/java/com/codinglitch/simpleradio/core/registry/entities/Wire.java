package com.codinglitch.simpleradio.core.registry.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Ensure this implements whatever 'Wiring' or 'Source' interface your mod uses
public class Wire extends Entity {
    private static final EntityDataAccessor<Optional<UUID>> FROM = SynchedEntityData.defineId(Wire.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> TO = SynchedEntityData.defineId(Wire.class, EntityDataSerializers.OPTIONAL_UUID);

    public final List<Effect> effectList = new ArrayList<>();

    public Wire(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    // FIX: Static connect method for WireItem.java
    public static Wire connect(Object fromSocket, Object toSocket, Level level) {
        // Replace with your actual entity registry reference
        Wire wire = new Wire(EntityType.PIG, level); // Replace EntityType.PIG with your WIRE type!
        // wire.setFrom(...) logic here
        level.addFreshEntity(wire);
        return wire;
    }

    public double getLength() { return 1.0; }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(FROM, Optional.empty());
        builder.define(TO, Optional.empty());
    }

    public Optional<UUID> getFrom() { return this.entityData.get(FROM); }
    public Optional<UUID> getTo() { return this.entityData.get(TO); }
    public void setFrom(UUID uuid) { this.entityData.set(FROM, Optional.ofNullable(uuid)); }
    public void setTo(UUID uuid) { this.entityData.set(TO, Optional.ofNullable(uuid)); }

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
        if (!this.level().isClientSide) {
            if (this.getFrom().isEmpty() || this.getTo().isEmpty()) {
                this.discard();
            }
        }
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