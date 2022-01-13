package com.xeross.skyutilities.helpers.gui.models;

import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderAPI;
import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderMessageAPI;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderSizeType;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderType;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import com.xeross.skyutilities.helpers.messages.api.MessageType;

public class ConfiguratorHolder<O extends Enum<O> & MessageType> {
    
    private final ConfiguratorHolderSizeType size;
    private final ConfiguratorHolderType type;
    private final O messageType;
    private final String[] keys;
    private final ConfiguratorHolderMessageAPI[] values;
    private final ItemCreator item;
    private final ConfiguratorHolderAPI api;
    
    public ConfiguratorHolder(ConfiguratorHolderSizeType size, ConfiguratorHolderType type, O messageType, String[] keys, ConfiguratorHolderMessageAPI[] values, ItemCreator item, ConfiguratorHolderAPI api) {
        this.size = size;
        this.type = type;
        this.messageType = messageType;
        this.keys = keys;
        this.values = values;
        this.item = item;
        this.api = api;
    }
    
    public O getMessageType() {
        return messageType;
    }
    
    public String[] getKeys() {
        return keys;
    }
    
    public ConfiguratorHolderMessageAPI[] getValues() {
        return values;
    }
    
    public ConfiguratorHolderSizeType getSize() {
        return size;
    }
    
    public ConfiguratorHolderType getType() {
        return type;
    }
    
    public ItemCreator getItem() {
        return item;
    }
    
    public ConfiguratorHolderAPI getApi() {
        return api;
    }
}
