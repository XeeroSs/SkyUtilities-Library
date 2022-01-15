package com.xeross.skyutilities.helpers.tasks.api;

import com.xeross.skyutilities.core.api.MainAPI;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("unused")
public interface TaskAPI extends MainAPI {
    
    void startTimer(final BukkitRunnable runnable);
    
    void startTimer(final BukkitRunnable runnable, final long periodInSecond);
    void startTimerWithTicks(final BukkitRunnable runnable, final long ticks);
    
    void startLater(final long delayInSecond, final TaskLaterAPI api);
    
    void startTimer(final BukkitRunnable runnable, final long delayInSecond, final long periodInSecond);
    
    void startDelay(final BukkitRunnable runnable, final long delayInSecond);
    
    void cancel(final Class<? extends BukkitRunnable> runnable);
    
}
