package com.xeross.skyutilities.helpers.gui.models.types;

public enum ConfiguratorHolderSizeType {
    ONE(1), TWO(2), THREE(3);

    private final int size;

    ConfiguratorHolderSizeType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
