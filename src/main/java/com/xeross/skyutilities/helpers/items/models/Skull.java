package com.xeross.skyutilities.helpers.items.models;

import com.xeross.skyutilities.helpers.blocks.models.ColorType;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public enum Skull {
    
    OAK_RIGHT(new ItemCreator(Material.INK_SACK).setDurability((short) ColorType.LIME.getDyeColor().getDyeData()).getItem(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="),
    OAK_LEFT(new ItemCreator(Material.INK_SACK).setDurability((short) ColorType.RED.getDyeColor().getDyeData()).getItem(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="),
    
    BIRCH_RIGHT(new ItemCreator(Material.INK_SACK).setDurability((short) ColorType.LIME.getDyeColor().getDyeData()).getItem(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY5N2MyNDg5MmNmYzAzYzcyOGZmYWVhYmYzNGJkZmI5MmQ0NTExNDdiMjZkMjAzZGNhZmE5M2U0MWZmOSJ9fX0="),
    BIRCH_LEFT(new ItemCreator(Material.INK_SACK).setDurability((short) ColorType.RED.getDyeColor().getDyeData()).getItem(),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZlMTQ1ZTcxMjk1YmNjMDQ4OGU5YmI3ZTZkNjg5NWI3Zjk2OWEzYjViYjdlYjM0YTUyZTkzMmJjODRkZjViIn19fQ==");
    
    private final ItemStack defaultMaterial;
    private final String base64;
    
    Skull(ItemStack defaultMaterial, String base64) {
        this.defaultMaterial = defaultMaterial;
        this.base64 = base64;
    }
    
    public ItemStack getDefaultMaterial() {
        return defaultMaterial;
    }
    
    public String getBase64() {
        return base64;
    }
}
