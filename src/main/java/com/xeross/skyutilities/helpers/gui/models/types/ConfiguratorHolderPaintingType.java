package com.xeross.skyutilities.helpers.gui.models.types;

import com.xeross.skyutilities.helpers.gui.api.ConfiguratorHolderPaintingAPI;
import com.xeross.skyutilities.helpers.utils.api.UtilsAPI;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum ConfiguratorHolderPaintingType {
    
    FULLY((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            for (int i = 0; i < slots; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            return painting;
        }
        
        final ItemStack item = items[0].setName().getItem();
        for (int i = 0; i < slots; i++) {
            painting.put(i, item);
        }
        return painting;
    }),
    ALL_SIDES((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        slots = (slots - 1);
        int j = 9;
        final int max = (slots - 9);
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            for (int i = 0; i < 9; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            while (max > j) {
                painting.put(j, utilsAPI.getRandom(items).setName().getItem());
                painting.put((j + 8), utilsAPI.getRandom(items).setName().getItem());
                j = (j + 9);
            }
            for (int i = j; i <= slots; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            return painting;
        }
        if (items.length == 1) {
            final ItemStack item = items[0].setName().getItem();
            for (int i = 0; i < 9; i++) {
                painting.put(i, item);
            }
            while (max > j) {
                painting.put(j, item);
                painting.put((j + 8), item);
                j = (j + 9);
            }
            for (int i = j; i <= slots; i++) {
                painting.put(i, item);
            }
            return painting;
        }
        final ItemStack item = items[0].setName().getItem();
        final ItemStack item2 = items[1].setName().getItem();
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 1 || i == 7 || i == 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        while (max > j) {
            if (j == 9 || j == slots - 17) {
                painting.put(j, item2);
                painting.put((j + 8), item2);
            } else {
                painting.put(j, item);
                painting.put((j + 8), item);
            }
            j = (j + 9);
        }
        for (int i = j; i <= slots; i++) {
            if (i == j || i == j + 1 || i == j + 7 || i == j + 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        painting.put(4, item2);
        painting.put(slots - 4, item2);
        return painting;
    }),
    START_END((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        slots = (slots - 1);
        int j = 9;
        final int max = (slots - 9);
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            painting.put(0, utilsAPI.getRandom(items).setName().getItem());
            painting.put(8, utilsAPI.getRandom(items).setName().getItem());
            while (max > j) {
                painting.put(j, utilsAPI.getRandom(items).setName().getItem());
                painting.put((j + 8), utilsAPI.getRandom(items).setName().getItem());
                j = (j + 9);
            }
            painting.put(j, utilsAPI.getRandom(items).setName().getItem());
            painting.put(j + 8, utilsAPI.getRandom(items).setName().getItem());
            return painting;
        }
        if (items.length == 1) {
            final ItemStack item = items[0].setName().getItem();
            painting.put(0, item);
            painting.put(8, item);
            while (max > j) {
                painting.put(j, item);
                painting.put((j + 8), item);
                j = (j + 9);
            }
            painting.put(j, item);
            painting.put(j + 8, item);
            return painting;
        }
        final ItemStack item = items[0].setName().getItem();
        final ItemStack item2 = items[1].setName().getItem();
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 1 || i == 7 || i == 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        while (max > j) {
            if (j == 9 || j == slots - 9) {
                painting.put(j, item2);
                painting.put((j + 8), item2);
            } else {
                painting.put(j, item);
                painting.put((j + 8), item);
            }
            j = (j + 9);
        }
        for (int i = j; i <= slots; i++) {
            if (i == j || i == j + 1 || i == j + 7 || i == j + 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        return painting;
    }),
    ONLY_BOTTOM((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        slots = (slots - 1);
        int j = slots - 8;
        final int max = (slots - 8);
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            for (int i = j; i <= slots; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            return painting;
        }
        if (items.length == 1) {
            final ItemStack item = items[0].setName().getItem();
            for (int i = j; i <= slots; i++) {
                painting.put(i, item);
            }
            return painting;
        }
        final ItemStack item = items[0].setName().getItem();
        final ItemStack item2 = items[1].setName().getItem();
        for (int i = j; i <= slots; i++) {
            if (i == j || i == j + 1 || i == j + 7 || i == j + 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        painting.put(4, utilsAPI.getRandom(items).setName().getItem());
        painting.put(slots - 4, utilsAPI.getRandom(items).setName().getItem());
        return painting;
    }),
    ONLY_TOP((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        final int min = 0;
        final int max = 8;
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            for (int i = min; i < max; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            return painting;
        }
        if (items.length == 1) {
            final ItemStack item = items[0].setName().getItem();
            for (int i = min; i < max; i++) {
                painting.put(i, item);
            }
            return painting;
        }
        final ItemStack item = items[0].setName().getItem();
        final ItemStack item2 = items[1].setName().getItem();
        for (int i = min; i <= max; i++) {
            if (i == min || i == min + 1 || i == min + 7 || i == min + 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        painting.put(4, utilsAPI.getRandom(items).setName().getItem());
        return painting;
    }),
    TOP_BOTTOM((main, slots, items, sorted) -> {
        final Map<Integer, ItemStack> painting = new HashMap<>();
        final UtilsAPI utilsAPI = main.getAPI().getUtilsAPI();
        slots = (slots - 1);
        int j = slots - 9;
        final int max = (slots - 9);
        if (sorted == ConfiguratorHolderPaintingMaterialType.RANDOM) {
            for (int i = 0; i < 9; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            for (int i = j; i <= slots; i++) {
                painting.put(i, utilsAPI.getRandom(items).setName().getItem());
            }
            return painting;
        }
        if (items.length == 1) {
            final ItemStack item = items[0].setName().getItem();
            for (int i = 0; i < 9; i++) {
                painting.put(i, item);
            }
            for (int i = j; i <= slots; i++) {
                painting.put(i, item);
            }
            return painting;
        }
        final ItemStack item = items[0].setName().getItem();
        final ItemStack item2 = items[1].setName().getItem();
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 1 || i == 7 || i == 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        for (int i = j; i <= slots; i++) {
            if (i == j || i == j + 1 || i == j + 7 || i == j + 8) {
                painting.put(i, item2);
                continue;
            }
            painting.put(i, item);
        }
        painting.put(slots - 4, utilsAPI.getRandom(items).setName().getItem());
        return painting;
    });
    
    
    private final ConfiguratorHolderPaintingAPI api;
    
    ConfiguratorHolderPaintingType(ConfiguratorHolderPaintingAPI api) {
        this.api = api;
    }
    
    public ConfiguratorHolderPaintingAPI getApi() {
        return api;
    }
}
