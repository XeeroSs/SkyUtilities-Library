package com.xeross.skyutilities.helpers.gui.api;


import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public abstract class GUI<P extends Plugin> {
    
    private final String BACK = "§c§l↩ Retour";
    private final ItemStack itemStack;
    private final SkyUtilities<P> main;
    
    public GUI(SkyUtilities<P> main) {
        this.itemStack = new ItemCreator(Material.ARROW).setName(BACK).getItem();
        this.main = main;
    }
    
    public abstract String getName(String title);
    
    /**
     * 00 01 02 03 04 05 06 07 08
     * <p>
     * 09 10 11 12 13 14 15 16 17
     * <p>
     * 18 19 20 21 22 23 24 25 26
     * <p>
     * 27 28 29 30 31 32 33 34 35
     * <p>
     * 36 37 38 39 40 41 42 43 44
     * <p>
     * 45 46 47 48 49 50 51 52 53
     */
    public abstract ItemStack[] getContents(Player player, String addonsForTile, String[] addons);
    
    public abstract void animated(Player player, Inventory inventory, String addonsForTile);
    
    protected abstract int getSize();
    
    public boolean isGUI(String title) {
        return title.equals(getName(""));
    }
    
    public int getSlots() {
        return (getSize() * 9);
    }
    
    @SuppressWarnings("unused")
    protected void placeItemBack(final ItemStack[] array_items) {
        final int position = (getSlots() - 1);
        array_items[position] = itemStack;
    }
    
    @SuppressWarnings({"unused"})
    protected boolean onBackClick(String name, Player player, Class<? extends GUI<P>> gui) {
        if (!name.equals(BACK)) return false;
        main.getAPI().getGUIAPI().open(player, gui, "1", null);
        return true;
    }
    
    public abstract void onClick(P plugin, Player player, InventoryClickEvent e, ItemStack currentItem, ItemMeta meta);
}
