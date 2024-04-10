package ru.legeu.dailybet.object;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private final UUID uuid;
    @Getter
    private Player player;

    public UUID getUniqueID() {
        return uuid;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public User(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public void sendMessage (String...messages) {
        for (String message : messages) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
