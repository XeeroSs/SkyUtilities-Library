package com.xeross.skyutilities;

import org.bukkit.entity.Player;

import java.util.HashMap;

@SuppressWarnings("unused")
public interface GlobalAPI {
    String[] getKeysAntiSpam();
    String[] getValuesAntiSpam();
    int getCooldownAntiSpam();
    HashMap<Player, Long> getAntiSpam();
}
