package com.xeross.skyutilities.helpers.blocks.models;

import org.bukkit.DyeColor;

@SuppressWarnings("unused")
public enum ColorType {
    WHITE(0, 15, DyeColor.WHITE),
    ORANGE(1, 14, DyeColor.ORANGE),
    MAGENTA(2, 13, DyeColor.MAGENTA),
    LIGHT_BLUE(3, 12, DyeColor.LIGHT_BLUE),
    YELLOW(4, 11, DyeColor.YELLOW),
    LIME(5, 10, DyeColor.LIME),
    PINK(6, 9, DyeColor.PINK),
    GRAY(7, 8, DyeColor.GRAY),
    SILVER(8, 7, DyeColor.SILVER),
    CYAN(9, 6, DyeColor.CYAN),
    PURPLE(10, 5, DyeColor.PURPLE),
    BLUE(11, 4, DyeColor.BLUE),
    BROWN(12, 3, DyeColor.BROWN),
    GREEN(13, 2, DyeColor.GREEN),
    RED(14, 1, DyeColor.RED),
    BLACK(15, 0, DyeColor.BLACK);
    
    private final byte dataWool;
    private final byte dataGlassAndDye;
    private final DyeColor dyeColor;
    
    public byte getWoolData() {
        return dataWool;
    }
    
    public byte getGlassAndDyeData() {
        return dataGlassAndDye;
    }
    
    public DyeColor getDyeColor() {
        return dyeColor;
    }
    
    ColorType(final int dataWool, final int dataGlassAndDye, final DyeColor dyeColor) {
        this.dataWool = ((byte) dataWool);
        this.dataGlassAndDye = ((byte) dataGlassAndDye);
        this.dyeColor = dyeColor;
    }
    
    
}
