package com.xeross.skyutilities.helpers.items;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.items.api.ItemAPI;
import com.xeross.skyutilities.helpers.items.utils.SkullCreator;

public class ItemHandler implements ItemAPI {

    private final SkyUtilities main;
    private final SkullCreator skullCreator;

    public ItemHandler(SkyUtilities main) {
        this.main = main;
        this.skullCreator = new SkullCreator();
    }

    @Override
    public SkullCreator getSkullAPI() {
        return skullCreator;
    }
}
