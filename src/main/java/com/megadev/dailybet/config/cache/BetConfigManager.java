package com.megadev.dailybet.config.cache;

import dev.mega.megacore.config.Manager;

import org.bukkit.plugin.Plugin;

public class BetConfigManager extends Manager {
    public BetConfigManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(BetConfig.class, new BetConfig(plugin, getDataFolder(), "bets"));
    }

}
