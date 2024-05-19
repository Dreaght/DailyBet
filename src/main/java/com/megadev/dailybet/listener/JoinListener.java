package com.megadev.dailybet.listener;

import com.megadev.dailybet.manager.MenuManager;
import com.megadev.dailybet.object.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        MenuManager menuManager = MenuManager.getInstance();

        for (Menu menu : menuManager.getMenus()) {
            if (player.getUniqueId().equals(menu.getUuid()))
                menu.setPlayer(player);
        }

        menuManager.loadPlayerMenus(player);
    }
}
