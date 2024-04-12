package ru.legeu.dailybet;

import lombok.Getter;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.java.JavaPlugin;

import ru.legeu.dailybet.command.BetCommand;
import ru.legeu.dailybet.config.ConfigManager;
import ru.legeu.dailybet.manager.BetTaskManager;
import ru.legeu.dailybet.manager.GiveawayManager;
import ru.legeu.dailybet.manager.MenuManager;
import ru.legeu.dailybet.utils.inventory.InventoryStateHandler;

import java.util.Objects;

public final class DailyBet extends JavaPlugin {
    @Getter
    private static DailyBet instance;
    @Getter
    public static Economy economy;

    @Override
    public void onEnable() {
        BetEconomyHandler.init(this);

        instance = this;

        ConfigManager.init(this);
        BetTaskManager.init(this);
        GiveawayManager.init(this);
        MenuManager.init(this);

        Objects.requireNonNull(getCommand("bet")).setExecutor(new BetCommand());
    }

    @Override
    public void onDisable() {
        try {
            InventoryStateHandler.saveInventory(MenuManager.getInstance().getInventory(), this);
        } catch (NullPointerException ignored) {
        }
    }
}
