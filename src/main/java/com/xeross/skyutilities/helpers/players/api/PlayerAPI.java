package com.xeross.skyutilities.helpers.players.api;

import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.players.models.SoundType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface PlayerAPI extends MainAPI {
    
    void reset(final Player player);
    void playerSound(final Sound sound, final Player player);
    void playerSound(final SoundType sound, final Player player, final float level);
    void playerSound(final SoundType sound, final Player player);
}
