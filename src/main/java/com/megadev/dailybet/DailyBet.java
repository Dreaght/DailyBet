package com.megadev.dailybet;

import com.megadev.dailybet.object.economy.EconomyHandler;
import com.megadev.dailybet.object.menu.Menu;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import com.megadev.dailybet.command.BetCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.listener.MenuListener;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.manager.GiveawayManager;
import com.megadev.dailybet.manager.MenuManager;
import com.megadev.dailybet.util.inventory.InventoryStateHandler;

import java.util.Iterator;
import java.util.Objects;

public final class DailyBet extends JavaPlugin {
    @Getter
    private static DailyBet instance;

    public DailyBet() {
    }

    public void onEnable() {
        instance = this;
        ConfigManager.init(this);
        BetTaskManager.init(this);
        GiveawayManager.init(this);
        MenuManager.init(this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        Objects.requireNonNull(this.getCommand("bet")).setExecutor(new BetCommand());
        EconomyHandler.installEconomies();
    }

    public void onDisable() {
        try {
            for (Menu menu : MenuManager.getInstance().getMenus()) {
                InventoryStateHandler.saveInventory(menu.getInventory(), this);
            }
        } catch (NullPointerException var3) {
            Bukkit.getLogger().warning("Tried to save an inventory, but it does not exist yet!");
        }

    }

}
