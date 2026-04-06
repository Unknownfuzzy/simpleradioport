package com.codinglitch.simpleradio.compat;

import com.codinglitch.simpleradio.CommonSimpleRadio;
import net.neoforged.fml.loading.LoadingModList;

public class CompatibilityInstance {
    public final String name;
    public final String modId;
    public final boolean enabled;
    public final boolean isLoaded;

    // Fixed: Added 'Object... extra' to handle the extra arguments from CompatCore.java
    public CompatibilityInstance(String name, String modId, boolean enabled, Object... extra) {
        this.name = name;
        this.modId = modId;
        this.enabled = enabled;
        this.isLoaded = net.neoforged.fml.loading.LoadingModList.get().getModFileById(modId) != null;
    }

    public boolean isPresent() {
        return isLoaded && enabled;
    }

    public void spout() {
        if (isPresent()) {
            CommonSimpleRadio.info("Compatibility layer for {} [{}] is ACTIVE.", name, modId);
        }
    }
}