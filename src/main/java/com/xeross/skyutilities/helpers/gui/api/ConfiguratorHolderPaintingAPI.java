package com.xeross.skyutilities.helpers.gui.api;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.gui.models.types.ConfiguratorHolderPaintingMaterialType;
import com.xeross.skyutilities.helpers.items.utils.ItemCreator;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface ConfiguratorHolderPaintingAPI {
    
    @SuppressWarnings("rawtypes")
    Map<Integer, ItemStack> painting(final SkyUtilities skyutilities, final int slots, final ItemCreator[] material, final ConfiguratorHolderPaintingMaterialType configuratorHolderPaintingMaterialType);
    
}
