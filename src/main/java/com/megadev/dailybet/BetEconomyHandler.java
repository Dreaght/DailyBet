package com.megadev.dailybet;

import lombok.Getter;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.megadev.dailybet.object.economy.adapter.CoinsEngineAdapter;
import com.megadev.dailybet.object.economy.adapter.EconomyAdapter;
import com.megadev.dailybet.object.economy.adapter.VaultEconomyAdapter;

public class BetEconomyHandler {
    @Getter
    private static BetEconomyHandler instance;

    private final Plugin plugin;
    private static EconomyAdapter economyFrom = null;
    private static EconomyAdapter economyTo = null;

    private BetEconomyHandler(Plugin plugin) {
        this.plugin = plugin;

        if (!setupVault()) {
            plugin.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", plugin.getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        setupCoinsEngine();
    }

    public static void init(Plugin plugin) {
        instance = new BetEconomyHandler(plugin);
    }

    public boolean setupVault() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> vaultEcoProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (vaultEcoProvider == null) return false;

        economyFrom = new VaultEconomyAdapter(vaultEcoProvider.getProvider());
        return economyFrom != null;
    }

    public void setupCoinsEngine() {
        economyTo = new CoinsEngineAdapter();
    }
}
