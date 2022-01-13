package com.xeross.skyutilities.events;

import com.xeross.skyutilities.core.api.ListenerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MenuListener implements Listener, ListenerAPI {
    
    private final HashMap<Player, Long> antiSpam;
    
    public MenuListener() {
        this.antiSpam = new HashMap<>();
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(InventoryClickEvent e) {
        
        final ItemStack item = e.getCurrentItem();
        if (item == null) return;
        
        if (!(e.getWhoClicked() instanceof Player)) return;
        final Player player = (Player) e.getWhoClicked();
        // Anti-spam
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName() != null) {
                final Long register = antiSpam.get(player);
                if (register != null) {
                    // 5 = ~1 second
                    if ((register / 100) + 3 > (System.currentTimeMillis() / 100)) {
                        e.setCancelled(true);
                        return;
                    }
                }
                // TODO("remove after leave")
                antiSpam.put(player, System.currentTimeMillis());
            }
        }
    }
    
    @EventHandler
    public void onClick(PlayerQuitEvent e) {
        antiSpam.remove(e.getPlayer());
    }
    
    @Override
    public void onReload() {
    }
}
