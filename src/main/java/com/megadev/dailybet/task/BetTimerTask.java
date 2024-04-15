package com.megadev.dailybet.task;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class BetTimerTask extends BukkitRunnable {
    protected final Plugin plugin;

    public BetTimerTask(Plugin plugin) {
        this.plugin = plugin;
    }
}
