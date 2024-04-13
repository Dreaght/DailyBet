package ru.legeu.dailybet.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.legeu.dailybet.config.ConfigManager;

public class MenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        ConfigManager configManager = ConfigManager.getInstance();

        if (!(event.getView().getTitle().equalsIgnoreCase((String) configManager.getMessageConfig().getValue("messages.menu.title")))) {
            return;
        }

        event.setCancelled(true);
    }

}
