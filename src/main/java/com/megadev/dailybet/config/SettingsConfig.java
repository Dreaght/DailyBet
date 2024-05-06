package com.megadev.dailybet.config;

import dev.mega.megacore.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SettingsConfig extends Configurable {
    protected SettingsConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, fileName);
    }

}
