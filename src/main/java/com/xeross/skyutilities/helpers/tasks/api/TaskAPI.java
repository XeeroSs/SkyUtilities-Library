package com.xeross.skyutilities.helpers.tasks.api;

import com.xeross.skyutilities.core.api.MainAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public interface TaskAPI extends MainAPI {

    void startTimer(final BukkitRunnable runnable, Plugin plugin);

    void startTimer(final BukkitRunnable runnable, Plugin plugin, final long periodInSecond);
    void startTimerWithTicks(final BukkitRunnable runnable, Plugin plugin, final long ticks);

    void startLater(final long delayInSecond, Plugin plugin, final TaskLaterAPI api);

    void startTimer(final BukkitRunnable runnable, Plugin plugin, final long delayInSecond, final long periodInSecond);

    void startDelay(final BukkitRunnable runnable, Plugin plugin, final long delayInSecond);

    void cancel(final Class<? extends BukkitRunnable> runnable);

}
