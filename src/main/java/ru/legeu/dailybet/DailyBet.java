package ru.legeu.dailybet;

import lombok.Getter;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.legeu.dailybet.command.BetCommand;
import ru.legeu.dailybet.config.ConfigManager;
import ru.legeu.dailybet.listener.JoinListener;
import ru.legeu.dailybet.manager.BetTaskManager;
import ru.legeu.dailybet.manager.GiveawayManager;

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

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Objects.requireNonNull(getCommand("bet")).setExecutor(new BetCommand());
    }
}
