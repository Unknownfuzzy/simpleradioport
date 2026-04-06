package com.codinglitch.simpleradio.central;

import com.codinglitch.simpleradio.core.routers.RouterContainer;
import com.codinglitch.simpleradio.core.routers.Router;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface RouterHolder<R extends Router> {
    List<R> get();

    default R remove(Predicate<R> criteria) {
        List<R> list = get();
        R removal = list.stream().filter(criteria).findFirst().orElse(null);
        if (removal == null) return null;

        if (list instanceof RouterContainer<?> routerContainer) {
            @SuppressWarnings("unchecked")
            RouterContainer<R> container = (RouterContainer<R>) routerContainer;
            container.removeIf(criteria);
        } else {
            list.removeIf(criteria);
        }

        return removal;
    }
    default R remove(R router) {
        return remove(other -> other == router);
    }
    default R remove(short identifier) {
        return remove(entry -> entry.getIdentifier() == identifier);
    }

    default R remove(Entity owner) {
        return remove(entry -> owner.equals(entry.getOwner()));
    }
    default R remove(WorldlyPosition location) {
        return remove(entry -> location.equals(entry.getPosition()));
    }
    default R remove(UUID id) {
        return remove(entry -> id.equals(entry.getReference()));
    }

    R get(Entity owner);
    R get(WorldlyPosition location);
    R get(UUID id);
    R get(Predicate<R> filter);

    R getOrCreate(Entity owner, @Nullable UUID id);

    R getOrCreate(Entity owner);
    R getOrCreate(WorldlyPosition location, @Nullable UUID id);
    R getOrCreate(WorldlyPosition location);

    R register(R R);
}
