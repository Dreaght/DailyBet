package com.megadev.dailybet.util.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Color {
    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessage(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendMessage(player, message);
        }
    }
}
