package com.megadev.dailybet.config;

import dev.mega.megacore.config.Manager;

import lombok.Getter;

import org.bukkit.plugin.Plugin;

public class ConfigManager extends Manager {
    @Getter
    private static ConfigManager instance;

    public ConfigManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(BetConfig.class, new BetConfig(plugin, "bets"));
        addConfig(SettingsConfig.class, new SettingsConfig(plugin, "config"));
        addConfig(MessageConfig.class, new MessageConfig(plugin, "messages"));
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin, ".");
        }
    }
}
