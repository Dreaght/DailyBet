package com.megadev.dailybet.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class Configurable {
    protected Plugin plugin;
    protected FileConfiguration config;
    protected File configFile;

    protected Configurable(@NotNull Plugin plugin, String fileName, Map<String, Object> defaults) {
        this.plugin = plugin;
        configFile = new File(plugin.getDataFolder(), fileName + "." + "yml");

        saveResource(fileName);

        this.config = YamlConfiguration.loadConfiguration(configFile);
        saveDefaults(defaults);
    }

    public void saveDefaults(Map<String, Object> defaults) {
        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!config.contains(key)) {
                if (value == null) {
                    config.createSection(key);
                } else {
                    config.set(key, value);
                }
            }
        }
        saveConfig();
    }

    public Object getValue(String key) {
        return config.get(key);
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public void setValue(String key, String value) {
        config.set(key, value);
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveResource(String fileName) {
        if (plugin.getResource(fileName + "." + "yml") != null) {
            plugin.saveResource(fileName + "." + "yml", false);
        } else {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
