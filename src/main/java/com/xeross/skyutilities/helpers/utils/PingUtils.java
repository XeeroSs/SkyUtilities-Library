package com.xeross.skyutilities.helpers.utils;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingUtils {
    
    public static String getPing(@NonNull Player player) {
        final int ping = ((CraftPlayer) player).getHandle().ping;
        String pingToString = PingType.GOOD.getColor().toString() + ping;
        if (ping <= PingType.GOOD.getMaxPing()) {
            pingToString = PingType.GOOD.getColor().toString() + ping;
        } else if (ping <= PingType.NORMAL.getMaxPing()) {
            pingToString = PingType.NORMAL.getColor().toString() + ping;
        } else if (ping <= PingType.BAD.getMaxPing()) {
            pingToString = PingType.BAD.getColor().toString() + ping;
        } else if (ping <= PingType.VERY_BAD.getMaxPing()) {
            pingToString = PingType.VERY_BAD.getColor().toString() + ping;
        } else if (ping > PingType.HORRIBLE.getMaxPing()) {
            pingToString = PingType.HORRIBLE.getColor().toString() + ping;
        }
        
        return pingToString;
    }
    
    private enum PingType {
        GOOD(ChatColor.GREEN, 50),
        NORMAL(ChatColor.GREEN, 100),
        BAD(ChatColor.YELLOW, 150),
        VERY_BAD(ChatColor.GOLD, 200),
        HORRIBLE(ChatColor.RED, 201);
        
        private final ChatColor color;
        private final int maxPing;
        
        PingType(ChatColor color, int maxPing) {
            this.color = color;
            this.maxPing = maxPing;
        }
        
        public ChatColor getColor() {
            return color;
        }
        
        public int getMaxPing() {
            return maxPing;
        }
    }
}
