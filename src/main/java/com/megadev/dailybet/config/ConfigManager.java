package com.megadev.dailybet.config;

import com.megadev.dailybet.config.cache.BetConfigManager;
import dev.mega.megacore.config.Manager;

import lombok.Getter;

import org.bukkit.plugin.Plugin;

public class ConfigManager extends Manager {
    @Getter
    private static ConfigManager instance;

    public ConfigManager(Plugin plugin, String dataFolder) {
        super(plugin, dataFolder);

        addConfig(BetConfigManager.class, new BetConfigManager(plugin, "cache"));
        addConfig(SettingsConfig.class, new SettingsConfig(plugin, "config"));
        addConfig(MessageConfig.class, new MessageConfig(plugin, "messages"));
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin, ".");
        }
    }
}
