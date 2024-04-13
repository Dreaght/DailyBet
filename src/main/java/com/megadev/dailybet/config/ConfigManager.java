package com.megadev.dailybet.config;

import lombok.Getter;

import org.bukkit.plugin.Plugin;

public class ConfigManager {
    @Getter
    private static ConfigManager instance;
    @Getter
    private final SettingsConfig settingsConfig;
    @Getter
    private final MessageConfig messageConfig;

    private ConfigManager(Plugin plugin) {
        this.settingsConfig = new SettingsConfig(plugin, "config");
        this.messageConfig = new MessageConfig(plugin, "message");
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
    }
}
