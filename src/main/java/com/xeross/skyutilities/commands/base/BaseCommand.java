package com.xeross.skyutilities.commands.base;

import com.xeross.skyutilities.SkyUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
@SuppressWarnings("unused")
public abstract class BaseCommand<P extends Plugin> implements CommandExecutor {
    
    private final SkyUtilities<P> skyUtilities;
    private final P main;
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
            getConsoleCommand(main, commandSender, oCommand, strings);
            return true;
        }
        final Player player = ((Player) commandSender);
        getPlayerCommand(main, player, oCommand, strings);
        return true;
    }
    
    protected boolean isInAntiSpam(Player player, String messageAntiSpam) {
        final HashMap<Player, Long> antiSpam = skyUtilities.getAPI().getGlobalAPI().getAntiSpam();
        final Long register = antiSpam.get(player);
        if (register != null) {
            if (((register / 1000) + skyUtilities.getAPI().getGlobalAPI().getCooldownAntiSpam()) > (System.currentTimeMillis() / 1000)) {
                player.sendMessage(messageAntiSpam);
                return true;
            }
        }
        antiSpam.put(player, System.currentTimeMillis());
        return false;
    }
    
    protected void getPlayerCommand(final P main, final Player player, final Command command, final String[] strings) {
    
    }
    
    protected void getConsoleCommand(final P main, final CommandSender sender, final Command command, final String[] strings) {
    
    }
    
    protected void sendUsage(final CommandSender player) {
        player.sendMessage(usage);
    }
    
    public BaseCommand(P main, SkyUtilities<P> skyUtilities, String command, String usages, String messageOnlyPlayer) {
        this.skyUtilities = skyUtilities;
        this.main = main;
        this.command = command;
        this.usages = usages;
        this.isOnlyPlayer = true;
        this.isMultipleCommands = false;
        this.messageOnlyPlayer = messageOnlyPlayer;
        this.usage = usageForm(usages);
    }
    
    public BaseCommand(P main, SkyUtilities<P> skyUtilities, String command, String messageOnlyPlayer) {
        this.skyUtilities = skyUtilities;
        this.main = main;
        this.command = command;
        this.usages = command;
        this.isOnlyPlayer = true;
        this.isMultipleCommands = false;
        this.messageOnlyPlayer = messageOnlyPlayer;
        this.usage = usageForm(usages);
        build();
    }
    
    public BaseCommand(P main, SkyUtilities<P> skyUtilities, String command, String usages, String messageOnlyPlayer, Boolean isOnlyPlayer, Boolean isMultipleCommands) {
        this.skyUtilities = skyUtilities;
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
    
    protected boolean hasPermission(Player player, String permission, String messagePlayerHasNotPermission) {
        if (player.hasPermission(permission)) return true;
        if (messagePlayerHasNotPermission != null) player.sendMessage(messagePlayerHasNotPermission);
        return false;
    }
    
    protected Player getTargetOnlineByName(Player player, String nameTarget, boolean acceptSenderIsTarget,
                                           String messageTargetIsOffline,
                                           String messageNotAcceptSenderIsTarget
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
    
    protected Integer toIntOrNull(final String stringToInt, final Player player, String message) {
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
