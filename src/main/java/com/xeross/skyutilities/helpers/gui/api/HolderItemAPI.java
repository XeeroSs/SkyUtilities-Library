package com.xeross.skyutilities.helpers.gui.api;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public interface HolderItemAPI<P extends Plugin> {
    void perform(final P m, final String name, final Player player, String title, InventoryClickEvent e);
}
