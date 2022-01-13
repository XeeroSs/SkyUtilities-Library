package com.xeross.skyutilities.commands.base;

import com.xeross.skyutilities.SkyUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BaseCommand (Old CommandHandler)
 * <p>
 * command - Command name.
 * <p>
 * usages - ',' between each command, without '/'. Example: "command,command <arg>".
 * <p>
 * isOnlyPlayer - Prevent the use of command by the console.
 *
 * @author XeroSs
 * @version 1.4.0
 */
public abstract class BaseCommand implements CommandExecutor {
    
    private final SkyUtilities main;
    private final String command;
    private final String usages;
    private final String usage;
    private final String messageOnlyPlayer;
    private final Boolean isOnlyPlayer;
    private final Boolean isMultipleCommands;
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command oCommand, String s, String[] strings) {
        if (!isMultipleCommands) if (!oCommand.getName().equalsIgnoreCase(command)) return false;
        if (!(commandSender instanceof Player)) {
            if (isOnlyPlayer) {
                if (messageOnlyPlayer != null) Bukkit.getConsoleSender().sendMessage(messageOnlyPlayer);
                return true;
            }
            getConsoleCommand(commandSender, oCommand, strings, main);
            return true;
        }
        final Player player = ((Player) commandSender);
        getPlayerCommand(player, oCommand, strings, main);
        return true;
    }
    
    protected boolean isInAntiSpam(Player player, @Nullable String messageAntiSpam) {
        final HashMap<Player, Long> antiSpam = main.getAPI().getGlobalAPI().getAntiSpam();
        final Long register = antiSpam.get(player);
        if (register != null) {
            if (((register / 1000) + main.getAPI().getGlobalAPI().getCooldownAntiSpam()) > (System.currentTimeMillis() / 1000)) {
                player.sendMessage(messageAntiSpam);
                return true;
            }
        }
        antiSpam.put(player, System.currentTimeMillis());
        return false;
    }
    
    protected void getPlayerCommand(final Player player, final Command command, final String[] strings, final SkyUtilities main) {
    
    }
    
    protected void getConsoleCommand(final CommandSender sender, final Command command, final String[] strings, final SkyUtilities main) {
    
    }
    
    protected void sendUsage(final CommandSender player) {
        player.sendMessage(usage);
    }
    
    public BaseCommand(SkyUtilities main, String command, String usages, @Nullable String messageOnlyPlayer) {
        this.main = main;
        this.command = command;
        this.usages = usages;
        this.isOnlyPlayer = true;
        this.isMultipleCommands = false;
        this.messageOnlyPlayer = messageOnlyPlayer;
        this.usage = usageForm(usages);
    }
    
    public BaseCommand(SkyUtilities main, String command, @Nullable String messageOnlyPlayer) {
        this.main = main;
        this.command = command;
        this.usages = command;
        this.isOnlyPlayer = true;
        this.isMultipleCommands = false;
        this.messageOnlyPlayer = messageOnlyPlayer;
        this.usage = usageForm(usages);
        build();
    }
    
    public BaseCommand(SkyUtilities main, String command, String usages, @Nullable String messageOnlyPlayer, Boolean isOnlyPlayer, Boolean isMultipleCommands) {
        this.main = main;
        this.command = command;
        this.usages = usages;
        this.isOnlyPlayer = isOnlyPlayer;
        this.isMultipleCommands = isMultipleCommands;
        this.messageOnlyPlayer = messageOnlyPlayer;
        
        this.usage = usageForm(usages);
        build();
    }
    
    private void build() {
    }
    
    protected boolean hasPermission(Player player, String permission, @Nullable String messagePlayerHasNotPermission) {
        if (player.hasPermission(permission)) return true;
        if (messagePlayerHasNotPermission != null) player.sendMessage(messagePlayerHasNotPermission);
        return false;
    }
    
    protected Player getTargetOnlineByName(Player player, String nameTarget, boolean acceptSenderIsTarget,
                                           @Nullable String messageTargetIsOffline,
                                           @Nullable String messageNotAcceptSenderIsTarget
    ) {
        final Player target = Bukkit.getPlayer(nameTarget);
        if (target == null) {
            if (messageTargetIsOffline != null) player.sendMessage(messageTargetIsOffline);
            return null;
        }
        if (!acceptSenderIsTarget) if (target == player) {
            if (messageNotAcceptSenderIsTarget != null) player.sendMessage(messageNotAcceptSenderIsTarget);
            return null;
        }
        return target;
    }
    
    private String usageForm(String usageList) {
        if (Objects.equals(usages, "")) return usages;
        return Arrays.stream(usageList.split(",")).map(c -> " §c• /" + c).collect(Collectors.joining("\n"));
    }
    
    protected Integer toIntOrNull(final String stringToInt, final Player player, @Nullable String message) {
        int i;
        try {
            i = Integer.parseInt(stringToInt);
        } catch (Exception ignored) {
            if (message != null) player.sendMessage(message);
            return null;
        }
        
        return i;
    }
}
