package com.megadev.dailybet;

import dev.mega.megacore.MegaCore;

import lombok.Getter;

import org.bukkit.Bukkit;

import java.util.Iterator;
import java.util.Objects;

public final class DailyBet extends MegaCore {
    @Getter
    private static DailyBet instance;

    @Override
    public void enable() {
        instance = this;
        ConfigManager.init(this);
        BetTaskManager.init(this);
        GiveawayManager.init(this);
        MenuManager.init(this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        Objects.requireNonNull(this.getCommand("bet")).setExecutor(new BetCommand());
        EconomyHandler.installEconomies();
    }

    @Override
    public void disable() {
        try {
            for (Menu menu : MenuManager.getInstance().getMenus()) {
                InventoryStateHandler.saveInventory(menu.getInventory(), this);
            }
        } catch (NullPointerException var3) {
            Bukkit.getLogger().warning("Tried to save an inventory, but it does not exist yet!");
        }
    }

}
