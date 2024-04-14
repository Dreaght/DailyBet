package com.megadev.dailybet;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.megadev.dailybet.command.BetCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.listener.MenuListener;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.manager.GiveawayManager;
import com.megadev.dailybet.manager.MenuManager;
import com.megadev.dailybet.util.inventory.InventoryStateHandler;

import java.util.Objects;

public final class DailyBet extends JavaPlugin {
    @Getter
    private static DailyBet instance;

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.init(this);
        BetTaskManager.init(this);
        GiveawayManager.init(this);
        MenuManager.init(this);

        BetEconomyHandler.init(this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        Objects.requireNonNull(getCommand("bet")).setExecutor(new BetCommand());
    }

    @Override
    public void onDisable() {
        try {
            InventoryStateHandler.saveInventory(MenuManager.getInstance().getInventory(), this);
        } catch (NullPointerException exception) {
            Bukkit.getLogger().warning("Tried to save an inventory, but it does not exist yet!");
        }
    }
}
