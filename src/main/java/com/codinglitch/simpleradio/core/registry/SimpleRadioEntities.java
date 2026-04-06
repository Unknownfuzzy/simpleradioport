package com.codinglitch.simpleradio.core.registry;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import com.codinglitch.simpleradio.core.registry.entities.Wire;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRadioEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, CommonSimpleRadio.ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Wire>> WIRE = ENTITY_TYPES.register(
            "wire",
            () -> EntityType.Builder.of(Wire::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build(CommonSimpleRadio.id("wire").toString())
    );

    public static void load() {}
}
