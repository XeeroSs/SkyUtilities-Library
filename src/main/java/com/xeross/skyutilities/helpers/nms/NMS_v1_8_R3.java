package com.xeross.skyutilities.helpers.nms;

import com.xeross.skyutilities.helpers.nms.api.NMS;
import com.xeross.skyutilities.helpers.nms.models.ServerPackage;
import org.bukkit.entity.Player;

public class NMS_v1_8_R3 implements NMS {

    @Override
    public void sendTitle(Player player, int fadeInTime, int showTime, int fadeOutTime, String title, String subtitle) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            // NMS Classes
            Class<?> clsPacketPlayOutTitle = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle");
            Class<?> clsPacket = ServerPackage.MINECRAFT.getClass("Packet");
            Class<?> clsIChatBaseComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent");
            Class<?> clsChatSerializer = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer");
            Class<?> clsEnumTitleAction = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle$EnumTitleAction");
            Object timesPacket = clsPacketPlayOutTitle.getConstructor(int.class, int.class, int.class).newInstance(fadeInTime, showTime, fadeOutTime);
            playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, timesPacket);
            if (title != null && !title.isEmpty()) {
                Object titleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, title);
                Object titlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction,
                        clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("TITLE").get(null), titleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, titlePacket);
            }
            if (subtitle != null && !subtitle.isEmpty()) {
                Object subtitleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, subtitle);
                Object subtitlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction,
                        clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("SUBTITLE").get(null), subtitleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, subtitlePacket);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
