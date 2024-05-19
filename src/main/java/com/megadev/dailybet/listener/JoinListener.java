package com.megadev.dailybet.listener;

import com.megadev.dailybet.manager.MenuManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        MenuManager menuManager = MenuManager.getInstance();

        menuManager.loadPlayerMenus(player.getUniqueId());
    }
}
