package com.xeross.skyutilities.helpers.gui.models;

import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderAPI;

public class ConfiguratorHolderData {
    
    private final Integer value;
    private final ConfiguratorHolderAPI api;
    
    public ConfiguratorHolderData(Integer value, ConfiguratorHolderAPI api) {
        this.value = value;
        this.api = api;
    }
    
    public Integer getValue() {
        return value;
    }
    
    public ConfiguratorHolderAPI getApi() {
        return api;
    }
}
