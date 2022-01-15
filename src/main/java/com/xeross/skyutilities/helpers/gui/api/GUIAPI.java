package com.xeross.skyutilities.helpers.gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


public interface GUIAPI<P extends Plugin> {
    
    void open(Player player, Class<? extends GUI<P>> clazz, String addonsForTitle, String[] addons);
    @SuppressWarnings("unused")
    void click(Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta);
    void add(final GUI<P> gui);
}
