package com.megadev.dailybet.utils.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Color {
    public void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
