package com.xeross.skyutilities.helpers.gui;

import com.xeross.skyutilities.helpers.gui.api.GUI;
import com.xeross.skyutilities.helpers.gui.api.GUIAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class GUIHandler<P extends Plugin> implements GUIAPI<P> {
    
    private final P plugin;
    
    public GUIHandler(P plugin) {
        this.plugin = plugin;
        inventories = new HashMap<>();
    }
    
    private final Map<Class<? extends GUI<P>>, GUI<P>> inventories;
    
    
    @SuppressWarnings({"unchecked", "unused"})
    @Override
    public void add(GUI<P> gui) {
        inventories.put((Class<? extends GUI<P>>) gui.getClass(), gui);
    }
    
    @Override
    public void open(Player player, Class<? extends GUI<P>> clazz, String addonsForTitle, String[] addons) {
        final GUI<P> gui = inventories.get(clazz);
        if (gui == null) return;
        final Inventory inventory = Bukkit.createInventory(null, gui.getSlots(), gui.getName(addonsForTitle));
        inventory.setContents(gui.getContents(player, addonsForTitle, addons));
        player.openInventory(inventory);
        gui.animated(player, inventory, addonsForTitle);
    }
    
    @Override
    public void click(Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta) {
        GUI<P> gui = inventories.values().stream().filter(g -> g.isGUI(e.getView().getTitle())).findFirst().orElse(null);
        if (gui == null) return;
        gui.onClick(plugin, player, e, currentItem, meta);
    }
}
