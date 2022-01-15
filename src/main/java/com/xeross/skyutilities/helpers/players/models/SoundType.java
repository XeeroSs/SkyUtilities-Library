package com.xeross.skyutilities.helpers.players.models;

import org.bukkit.Sound;

@SuppressWarnings("unused")
public enum SoundType {
    ENDER_DRAGON(Sound.ENDERDRAGON_GROWL),
    DING_BASS(Sound.NOTE_SNARE_DRUM),
    DING(Sound.NOTE_PLING),
    GAME_DONE(Sound.ENDERDRAGON_DEATH),
    NO(Sound.VILLAGER_NO),
    POP(Sound.CHICKEN_EGG_POP);
    
    private final Sound sound;
    
    SoundType(Sound sound) {
        this.sound = sound;
    }
    
    public Sound getSound() {
        return sound;
    }
}
