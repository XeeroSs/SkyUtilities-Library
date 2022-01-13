package com.xeross.skyutilities.helpers.blocks.api;

import com.xeross.skyutilities.helpers.blocks.models.ColorType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public interface BlockAPI {

    void setGlass(final Block block, final ColorType colorType, final boolean isPlane);
    void set(final Block block, final Material material);
    Boolean isSameBlock(Block block, ColorType colorType);
    void removeBlock(final Block block);
    void removeBlock(final BlockState block);

}
