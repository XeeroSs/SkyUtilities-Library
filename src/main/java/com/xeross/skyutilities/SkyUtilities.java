package com.xeross.skyutilities;

import com.xeross.skyutilities.core.APIHandler;
import com.xeross.skyutilities.core.RegisterHandler;
import org.bukkit.plugin.Plugin;

public class SkyUtilities<P extends Plugin> {
    
    private final APIHandler<P> apiHandler;
    
    public SkyUtilities(final P plugin, boolean antiSpamMenu) {
        this.apiHandler = new APIHandler<>(plugin);
        this.apiHandler.build();
        if (antiSpamMenu) new RegisterHandler(plugin);
    }
    
    public APIHandler<P> getAPI() {
        return apiHandler;
    }
}
