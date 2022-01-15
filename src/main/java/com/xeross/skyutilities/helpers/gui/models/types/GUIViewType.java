package com.xeross.skyutilities.helpers.gui.models.types;

@SuppressWarnings("unused")
public enum GUIViewType {
    
    SIX(new int[]{0, 1, 9, 7, 8, 17, 44, 53, 52, 46, 45, 36}, 6),
    FIVE(new int[]{0, 1, 9, 7, 8, 17, 27, 36, 37, 43, 44, 35}, 5),
    THREE(new int[]{0, 1, 9, 7, 8, 17, 18, 19, 25, 26}, 3),
    FOUR(new int[]{0, 1, 9, 7, 8, 17, 18, 27, 28, 26, 35, 34}, 4);
    
    private final int[] glasses;
    private final int size;
    
    GUIViewType(int[] glasses, int size) {
        this.glasses = glasses;
        this.size = size;
    }
    
    public int[] getGlasses() {
        return glasses;
    }
    
    public int getSize() {
        return size;
    }
}
