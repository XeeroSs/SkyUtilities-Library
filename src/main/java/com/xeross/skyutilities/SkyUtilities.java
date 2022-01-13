package com.xeross.skyutilities;

import com.xeross.skyutilities.core.APIHandler;
import com.xeross.skyutilities.core.RegisterHandler;
import org.bukkit.plugin.Plugin;

public class SkyUtilities {
    
    private APIHandler apiHandler;
    
    public SkyUtilities(final Plugin plugin, boolean antiSpamMenu) {
        this.apiHandler = new APIHandler(this);
        this.apiHandler.build();
        if (antiSpamMenu) new RegisterHandler(plugin);
    }
    
    public void setApiHandler(APIHandler apiHandler) {
        this.apiHandler = apiHandler;
    }
    
    public APIHandler getAPI() {
        return apiHandler;
    }
}
