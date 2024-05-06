package com.megadev.dailybet.config;

import dev.mega.megacore.config.Configurable;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MessageConfig extends Configurable {
    protected MessageConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, fileName);
    }
}
