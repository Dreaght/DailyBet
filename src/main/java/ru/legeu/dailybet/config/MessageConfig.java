package ru.legeu.dailybet.config;

import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MessageConfig extends Configurable {
    protected MessageConfig(@NotNull Plugin plugin, String fileName) {
        super(plugin, fileName, createDefaults());
    }

    private static Map<String, Object> createDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        fillMenu(defaults);
        return defaults;
    }

    private static void fillMenu(Map<String, Object> defaults) {
        defaults.put("messages.menu.title", "§8Bets top");
        defaults.put("messages.menu.head.title", "§c%PLAYER_NAME% §7(§8%AMOUNT%§7)");
        defaults.put("messages.menu.head.award", "§6%PERCENT% §7- §c%AWARD%");
    }
}
