package ru.legeu.dailybet.config;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SettingsConfig extends Configurable {
    protected SettingsConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("time", "19:20");
        defaults.put("date", "");
        defaults.put("points", 500);
        return defaults;

    }

    public String getTimeZone() {
        return config.getString("time-zone");
    }

    public void setTimeZone(String time) {
        config.set("time-zone", time);
        saveConfig();
    }
}
