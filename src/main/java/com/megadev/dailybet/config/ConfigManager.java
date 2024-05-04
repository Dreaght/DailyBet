package com.megadev.dailybet.config;

import com.megadev.dailybet.command.arg.BetConfig;
import lombok.Getter;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ConfigManager {
    @Getter
    private static ConfigManager instance;
    @Getter
    private final SettingsConfig settingsConfig;
    @Getter
    private final MessageConfig messageConfig;
    @Getter
    private final BetConfig betConfig;

    private ConfigManager(Plugin plugin) {
        this.settingsConfig = new SettingsConfig(plugin, "config");
        this.messageConfig = new MessageConfig(plugin, "message");
        this.betConfig = new BetConfig(plugin, "bets", new HashMap());
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }

    }
}
