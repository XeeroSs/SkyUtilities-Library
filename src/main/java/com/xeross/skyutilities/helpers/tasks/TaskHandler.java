package com.xeross.skyutilities.helpers.tasks;

import com.xeross.skyutilities.helpers.tasks.api.TaskAPI;
import com.xeross.skyutilities.helpers.tasks.api.TaskLaterAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;

public class TaskHandler<P extends Plugin> implements TaskAPI {
    
    private final HashMap<Class<? extends BukkitRunnable>, BukkitRunnable> tasks;
    private final P plugin;
    
    public TaskHandler(P plugin) {
        this.plugin = plugin;
        this.tasks = new HashMap<>();
    }
    
    @Override
    public void startLater(long delayInSecond, TaskLaterAPI api) {
        Bukkit.getScheduler().runTaskLater(plugin, api::perform, (20 * delayInSecond));
    }
    
    @Override
    public void startTimer(BukkitRunnable runnable) {
        if (tasks.containsKey(runnable.getClass())) return;
        try {
            runnable.runTaskTimer(plugin, 0, 20);
        } catch (IllegalStateException ignored) {
        }
        tasks.put(runnable.getClass(), runnable);
    }
    
    @Override
    public void startTimer(BukkitRunnable runnable, long periodInSecond) {
        if (tasks.containsKey(runnable.getClass())) return;
        try {
            runnable.runTaskTimer(plugin, 0, (20 * periodInSecond));
        } catch (IllegalStateException ignored) {
        }
        tasks.put(runnable.getClass(), runnable);
    }
    
    @Override
    public void startTimerWithTicks(BukkitRunnable runnable, long ticks) {
        if (tasks.containsKey(runnable.getClass())) return;
        try {
            runnable.runTaskTimer(plugin, 0, ticks);
        } catch (IllegalStateException ignored) {
        }
        tasks.put(runnable.getClass(), runnable);
    }
    
    @Override
    public void startTimer(BukkitRunnable runnable, long delayInSecond, long periodInSecond) {
        if (tasks.containsKey(runnable.getClass())) return;
        try {
            runnable.runTaskTimer(plugin, (20 * delayInSecond), (20 * periodInSecond));
        } catch (IllegalStateException ignored) {
        }
        tasks.put(runnable.getClass(), runnable);
    }
    
    @Override
    public void startDelay(BukkitRunnable runnable, long delayInSecond) {
        if (tasks.containsKey(runnable.getClass())) return;
        try {
            runnable.runTaskTimer(plugin, 0, (20 * delayInSecond));
        } catch (IllegalStateException ignored) {
        }
        tasks.put(runnable.getClass(), runnable);
    }
    
    @Override
    public void cancel(Class<? extends BukkitRunnable> runnable) {
        final BukkitRunnable task = tasks.remove(runnable);
        if (task == null) return;
        try {
            task.cancel();
        } catch (IllegalStateException ignored) {
        }
    }
    
    @Override
    public void onReload() {
    }
    
    @Override
    public void onDisable() {
        this.tasks.values().stream().filter(Objects::nonNull).forEach(runnable -> {
            try {
                runnable.cancel();
            } catch (IllegalStateException ignored) {
            }
        });
    }
}
