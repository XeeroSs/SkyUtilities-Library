package com.xeross.skyutilities;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public interface GlobalAPI {
    String[] getKeysAntiSpam();
    String[] getValuesAntiSpam();
    int getCooldownAntiSpam();
    HashMap<Player, Long> getAntiSpam();
}
