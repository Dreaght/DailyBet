package com.megadev.dailybet.listener;

import com.megadev.dailybet.config.MessageConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.megadev.dailybet.config.ConfigManager;

public class MenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        ConfigManager configManager = ConfigManager.getInstance();

        if (!(event.getView().getTitle().equalsIgnoreCase(configManager.getConfig(MessageConfig.class).getString("menu.title")))) {
            return;
        }

        event.setCancelled(true);
    }

}
