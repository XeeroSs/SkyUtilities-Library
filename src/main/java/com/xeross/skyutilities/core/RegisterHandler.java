package com.xeross.skyutilities.core;

import com.xeross.skyutilities.core.api.ListenerAPI;
import com.xeross.skyutilities.core.api.RegisterAPI;
import com.xeross.skyutilities.events.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class RegisterHandler implements RegisterAPI {
    
    private final Plugin plugin;
    private final PluginManager manager;
    private final ArrayList<ListenerAPI> listeners;
    
    public RegisterHandler(final Plugin plugin) {
        this.plugin = plugin;
        manager = Bukkit.getPluginManager();
        listeners = new ArrayList<>();
        registerListeners();
    }
    
    private void registerListeners() {
        addListener(new MenuListener());
    }
    
    private <I extends Listener> void addListener(final I api) {
        manager.registerEvents(api, plugin);
        if (!(api instanceof ListenerAPI)) return;
        listeners.add(((ListenerAPI) api));
    }
    
    @Override
    public void onReload() {
        listeners.forEach(ListenerAPI::onReload);
    }
    
    @Override
    public void onDisable() {
    }
}
