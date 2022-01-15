package com.xeross.skyutilities.helpers.items;

import com.xeross.skyutilities.helpers.items.api.ItemAPI;
import com.xeross.skyutilities.helpers.items.utils.SkullCreator;

public class ItemHandler implements ItemAPI {
    
    private final SkullCreator skullCreator;
    
    public ItemHandler() {
        this.skullCreator = new SkullCreator();
    }
    
    @Override
    public SkullCreator getSkullAPI() {
        return skullCreator;
    }
}
