package com.codinglitch.simpleradio.core.central;

import java.util.*;

public class SimpleRadioCentral {

    private static final Map<String, Object> REGISTRY = new HashMap<>();

    private static final Set<Object> ROUTERS = new HashSet<>();

    public static void register(String key, Object value) {
        REGISTRY.put(key, value);
    }

    public static Object get(String key) {
        return REGISTRY.get(key);
    }

    public static void registerRouter(Object router) {
        ROUTERS.add(router);
    }

    public static Set<Object> getRouters() {
        return Collections.unmodifiableSet(ROUTERS);
    }

    public static void clear() {
        REGISTRY.clear();
        ROUTERS.clear();
    }
}