package com.xeross.skyutilities.helpers.gui.api;

import com.xeross.skyutilities.SkyUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface HolderItemAPI {
    void perform(SkyUtilities m, final String name, final Player player, String title, InventoryClickEvent e);
}
