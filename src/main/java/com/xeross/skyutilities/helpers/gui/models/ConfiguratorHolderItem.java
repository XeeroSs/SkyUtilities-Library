package com.xeross.skyutilities.helpers.gui.models;

import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderMessageAPI;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import org.bukkit.plugin.Plugin;

public class ConfiguratorHolderItem<P extends Plugin, O extends Enum<O> & MessageType> {
    
    private final O type;
    private final ItemCreator item;
    private final String[] keys;
    private final ConfiguratorHolderMessageAPI[] value;
    
    public ConfiguratorHolderItem(O type, ItemCreator item, String[] keys, ConfiguratorHolderMessageAPI[] value) {
        this.type = type;
        this.item = item;
        this.keys = keys;
        this.value = value;
    }
    
    public String[] getKeys() {
        return keys;
    }
    
    public ConfiguratorHolderMessageAPI[] getValue() {
        return value;
    }
    
    public O getType() {
        return type;
    }
    
    public ItemCreator getItem() {
        return item;
    }
}
