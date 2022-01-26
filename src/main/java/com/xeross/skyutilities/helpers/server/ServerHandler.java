package com.xeross.skyutilities.helpers.server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.xeross.skyutilities.helpers.server.api.ServerAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public class ServerHandler implements ServerAPI {
    
    public ServerHandler() {
    }
    
    @Override
    public void onReload() {
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public void getPlayerCount(@Nonnull Player player, @Nonnull JavaPlugin plugin, @Nonnull String serverName) {
        final ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
        try {
            byteArrayDataOutput.writeUTF("PlayerCount");
            byteArrayDataOutput.writeUTF(serverName);
        } catch (Exception ignored) {
        }
        player.sendPluginMessage(plugin, "BungeeCord", byteArrayDataOutput.toByteArray());
    }
    
    @Override
    public void changeServer(@Nonnull Player player, @Nonnull JavaPlugin plugin, @Nonnull String serverName) {
        final ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
        try {
            byteArrayDataOutput.writeUTF("Connect");
            byteArrayDataOutput.writeUTF(serverName);
        } catch (Exception ignored) {
        }
        player.sendPluginMessage(plugin, "BungeeCord", byteArrayDataOutput.toByteArray());
    }
}
