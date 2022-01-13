package com.xeross.skyutilities.helpers.gui;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.gui.api.GUI;
import com.xeross.skyutilities.helpers.gui.api.GUIAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class GUIHandler implements GUIAPI {

    private final SkyUtilities main;

    public GUIHandler(SkyUtilities main) {
        this.main = main;
        inventories = new HashMap<>();
    }

    private final Map<Class<? extends GUI>, GUI> inventories;


    private void add(GUI gui) {
        inventories.put(gui.getClass(), gui);
    }

    @Override
    public void open(Player player, Class<? extends GUI> clazz, String addonsForTitle, @Nullable String[] addons) {
        final GUI gui = inventories.get(clazz);
        if (gui == null) return;
        final Inventory inventory = Bukkit.createInventory(null, gui.getSlots(), gui.getName(addonsForTitle));
        inventory.setContents(gui.getContents(player, addonsForTitle, addons));
        player.openInventory(inventory);
        gui.animated(player, inventory, addonsForTitle);
    }

    @Override
    public void click(Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta) {
        GUI gui = inventories.values().stream().filter(g -> g.isGUI(e.getView().getTitle())).findFirst().orElse(null);
        if (gui == null) return;
        gui.onClick(player, e, currentItem, meta);
    }
}
