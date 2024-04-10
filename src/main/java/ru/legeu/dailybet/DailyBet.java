package ru.legeu.dailybet;

import lombok.Getter;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import ru.legeu.dailybet.commands.BetCommand;
import ru.legeu.dailybet.listener.JoinListener;
import ru.legeu.dailybet.manager.BetManager;
import ru.legeu.dailybet.manager.UserManager;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

public final class DailyBet extends JavaPlugin {
    @Getter
    private static DailyBet instance;
    @Getter
    public static Economy economy;

    @Override
    public void onEnable() {
        BetEconomyHandler.init(this);
        if (!BetEconomyHandler.setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Objects.requireNonNull(getCommand("bet")).setExecutor(new BetCommand());
    }
}
