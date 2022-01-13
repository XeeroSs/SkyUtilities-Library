package com.xeross.skyutilities.helpers.gui.models.types;

public enum ConfiguratorHolderType {

    INTEGER(new String[]{"+1", "-1", "+10", "-10", "+100", "-100"}, new int[]{+1, -1, +10, -10, +100, -100}),
    DOUBLE(new String[]{"+00:10", "-00:10", "+01:00", "-01:00", "+05:00", "-05:00"}, new int[]{+10, -10, +60, -60, +(60 * 5), -(60 * 5)});

    private final String[] strings;
    private final int[] values;

    ConfiguratorHolderType(String[] strings, int[] values) {
        this.strings = strings;
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }

    public String[] getStrings() {
        return strings;
    }
}
