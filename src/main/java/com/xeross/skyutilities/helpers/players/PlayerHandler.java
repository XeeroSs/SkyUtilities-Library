package com.xeross.skyutilities.helpers.players;

import com.xeross.skyutilities.helpers.players.api.PlayerAPI;
import com.xeross.skyutilities.helpers.players.models.SoundType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class PlayerHandler implements PlayerAPI {
    
    public PlayerHandler() {
        onReload();
    }
    
    @Override
    public void onReload() {
    
    }
    
    @Override
    public void onDisable() {
    
    }
    
    @Override
    public void playerSound(Sound sound, Player player) {
        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
    }
    
    @Override
    public void playerSound(SoundType sound, Player player) {
        playerSound(sound.getSound(), player);
    }
    
    @Override
    public void playerSound(SoundType sound, Player player, float level) {
        player.playSound(player.getLocation(), sound.getSound(), level, level);
    }
    
    @Override
    public void reset(Player player) {
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(5);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        final PlayerInventory inventory = player.getInventory();
        inventory.clear();
        inventory.setHelmet(null);
        inventory.setChestplate(null);
        inventory.setLeggings(null);
        inventory.setBoots(null);
    }
}
