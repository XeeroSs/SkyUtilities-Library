package com.xeross.skyutilities.helpers.server.api;

import com.google.common.annotations.Beta;
import com.xeross.skyutilities.core.api.MainAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public interface ServerAPI extends MainAPI {
    @Beta
    void getPlayerCount(final @Nonnull Player player, final @Nonnull JavaPlugin plugin, final @Nonnull String serverName);
    @Beta
    void changeServer(final @Nonnull Player player, final @Nonnull JavaPlugin plugin, final @Nonnull String serverName);
}
