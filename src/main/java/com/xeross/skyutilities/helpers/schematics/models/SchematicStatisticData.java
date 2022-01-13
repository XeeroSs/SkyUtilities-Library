package com.xeross.skyutilities.helpers.schematics.models;

public class SchematicStatisticData {
    private String name_id;
    private int amount;

    public SchematicStatisticData(String name_id, int amount) {
        this.name_id = name_id;
        this.amount = amount;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
