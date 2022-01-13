package com.xeross.skyutilities.helpers.schematics.models.types;

public enum GenerationType {
    VERY_LOW(2000, 10),
    LOW(10000, 10),
    NORMAL(15000, 5),
    FAST(20000, 2);

    private final int blockByTicks;
    private final int secondsBetweenEachGeneration;

    GenerationType(int blockByTicks, int secondsBetweenEachGeneration) {
        this.blockByTicks = blockByTicks;
        this.secondsBetweenEachGeneration = secondsBetweenEachGeneration;
    }

    public int getBlockByTicks() {
        return blockByTicks;
    }

    public int getSecondsBetweenEachGeneration() {
        return secondsBetweenEachGeneration;
    }
}
