package com.xeross.skyutilities.helpers.gui.models;

import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderMessageAPI;
import com.xeross.skyutilities.helpers.gui.api.HolderItemAPI;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import com.xeross.skyutilities.helpers.messages.api.MessageType;

public class HolderItemWithLore<O extends Enum<O> & MessageType> {
    
    private final O type;
    private final ItemCreator item;
    private final String[] keys;
    private final ConfiguratorHolderMessageAPI[] value;
    private final HolderItemAPI api;
    
    public HolderItemWithLore(O type, ItemCreator item, String[] keys, ConfiguratorHolderMessageAPI[] value, HolderItemAPI api) {
        this.type = type;
        this.item = item;
        this.keys = keys;
        this.value = value;
        this.api = api;
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
    
    public HolderItemAPI getAPI() {
        return api;
    }
}
