package com.xeross.skyutilities.helpers.utils;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class PingUtils {
    
    private static String getServerVersion() {
        String version;
        try {
            
            version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            
        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return "unknown";
        }
        return version;
    }
    
    @SuppressWarnings("unused")
    public static String getPing(Player player) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
        Class<?> entityPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.EntityPlayer");
        Class<?> cast = clazz.cast(player).getClass();
        Method handle = cast.getDeclaredMethod("getHandle");
        Class<?> invoke = handle.invoke(entityPlayer).getClass();
        Method pingMethod = invoke.getMethod("ping");
        int ping = (int) pingMethod.invoke(int.class);
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
