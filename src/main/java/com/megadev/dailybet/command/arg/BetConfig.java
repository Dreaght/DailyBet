package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.config.Configurable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class BetConfig extends Configurable {
    public BetConfig(@NotNull Plugin plugin, String fileName, Map<String, Object> defaults) {
        super(plugin, fileName, defaults);
    }
}
