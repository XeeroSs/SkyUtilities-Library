package com.xeross.skyutilities.helpers.nms.api;

import org.bukkit.entity.Player;

public interface NMS {

    void sendTitle(Player player, int fadeInTime, int showTime, int fadeOutTime, String title, String subtitle);
}
