package com.xeross.skyutilities.helpers.blocks;

import com.xeross.skyutilities.helpers.blocks.api.BlockAPI;
import com.xeross.skyutilities.helpers.blocks.models.ColorType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class BlockHandler implements BlockAPI {
    
    public BlockHandler() {
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void setGlass(Block block, ColorType colorType, boolean isPlane) {
        if (!isPlane) block.setType(Material.STAINED_GLASS);
        else block.setType(Material.STAINED_GLASS_PANE);
        block.setData(colorType.getWoolData());
    }
    
    @Override
    public void removeBlock(Block block) {
        block.setType(Material.AIR);
        // block.getState().update(true);
    }
    
    @Override
    public void removeBlock(BlockState blockState) {
        blockState.setType(Material.AIR);
        //    blockState.update(true);
    }
    
    @Override
    public void set(Block block, Material material) {
        block.setType(material);
    }
    
    @SuppressWarnings({"deprecation", "RedundantIfStatement"})
    @Override
    public Boolean isSameBlock(Block block, ColorType colorType) {
        if (block.getData() == colorType.getWoolData()) return true;
        return false;
    }
}
