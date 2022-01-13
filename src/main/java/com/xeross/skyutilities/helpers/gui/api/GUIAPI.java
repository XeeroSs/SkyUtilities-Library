package com.xeross.skyutilities.helpers.gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public interface GUIAPI {

    void open(Player player, Class<? extends GUI> clazz, String addonsForTitle, @Nullable String[] addons);
    void click(Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta);
}
