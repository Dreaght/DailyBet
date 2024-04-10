package ru.legeu.dailybet.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.legeu.dailybet.manager.UserManager;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserManager userManager = UserManager.getInstance();

        if (!userManager.isUser(player))
            userManager.saveUser(player);
    }
}
