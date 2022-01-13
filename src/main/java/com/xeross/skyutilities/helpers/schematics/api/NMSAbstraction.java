package com.xeross.skyutilities.helpers.schematics.api;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface NMSAbstraction {
    void setBlockSuperFast(Block block, int blockId, byte data, Boolean applyPhysics);
    void setFlowerPotBlock(Block block, ItemStack itemStack);
}
