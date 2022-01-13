package com.xeross.skyutilities;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GlobalHandler implements GlobalAPI {

    private final SkyUtilities main;
    private final Map<Player, Player> privateMessages;
    private final HashMap<Player, Long> antiSpam;
    private final String[] keysAntiSpam;
    private final String[] valuesAntiSpam;
    private final int cooldownAntiSpam;

    public GlobalHandler(SkyUtilities main) {
        this.main = main;
        this.privateMessages = new HashMap<>();
        this.antiSpam = new HashMap<>();
        this.keysAntiSpam = new String[]{"{time}"};
        this.cooldownAntiSpam = 1;

        this.valuesAntiSpam = new String[]{String.valueOf(cooldownAntiSpam)};
    }

    @Override
    public String[] getKeysAntiSpam() {
        return keysAntiSpam;
    }

    @Override
    public String[] getValuesAntiSpam() {
        return valuesAntiSpam;
    }

    @Override
    public int getCooldownAntiSpam() {
        return cooldownAntiSpam;
    }

    @Override
    public HashMap<Player, Long> getAntiSpam() {
        return antiSpam;
    }
}
