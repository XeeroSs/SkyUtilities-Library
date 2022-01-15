package com.xeross.skyutilities.helpers.nms;

import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.nms.api.NMS;
import org.bukkit.Bukkit;

public class NMSHandler implements MainAPI {
    
    private final NMS nms;
    
    public NMSHandler() throws Exception {
        
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf(".") + 1);
        
        if (version.equals("v1_8_R3")) {
            nms = new NMS_v1_8_R3();
            return;
        }
        
        throw new Exception("Spigot is not correct version");
    }
    
    public NMS getNms() {
        return nms;
    }
    
    @Override
    public void onReload() {
    
    }
    
    @Override
    public void onDisable() {
    
    }
}
