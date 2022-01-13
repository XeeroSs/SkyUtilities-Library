package com.xeross.skyutilities.helpers.utils.models.types;

import org.bukkit.GameMode;

public enum GameModeType {
    SURVIVAL("0", "§b§lSurvival", GameMode.SURVIVAL),
    CREATIVE("1", "§a§lCreative", GameMode.CREATIVE),
    ADVENTURE("2", "§6§lAdventure", GameMode.ADVENTURE),
    SPECTATOR("3", "§e§lSpectator", GameMode.SPECTATOR),

    ;

    GameModeType(String value, String name, GameMode gameMode) {
        this.value = value;
        this.name = name;
        this.gameMode = gameMode;
    }

    private final String value;
    private final String name;
    private final GameMode gameMode;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
