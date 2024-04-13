package com.megadev.dailybet.tasks;

import org.bukkit.plugin.Plugin;

import java.util.TimerTask;

public abstract class BetTimerTask extends TimerTask {
    protected final Plugin plugin;

    public BetTimerTask(Plugin plugin) {
        this.plugin = plugin;
    }
}
