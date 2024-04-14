package com.megadev.dailybet.object.economy;

import com.megadev.dailybet.DailyBet;
import com.megadev.dailybet.event.PreInstallEconomyFromEvent;
import com.megadev.dailybet.event.PreInstallEconomyToEvent;
import com.megadev.dailybet.object.economy.adapter.*;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyInstaller {

    public static EconomyAdapter setEconomyFrom(String economyName) {
        EconomyAdapter economyFrom = getEconomyAdapter(economyName);

        PreInstallEconomyFromEvent event = new PreInstallEconomyFromEvent(economyFrom);
        Bukkit.getPluginManager().callEvent(event);

        economyFrom = event.getEconomyFrom();

        return economyFrom;
    }

    public static EconomyAdapter setEconomyTo(String economyName) {
        EconomyAdapter economyTo = getEconomyAdapter(economyName);

        PreInstallEconomyToEvent event = new PreInstallEconomyToEvent(economyTo);
        Bukkit.getPluginManager().callEvent(event);

        economyTo = event.getEconomyTo();

        return economyTo;
    }

    private static EconomyAdapter getEconomyAdapter(String economyName) {
        return switch (economyName) {
            case "CoinsEngine" -> new CoinsEngineAdapter();
            case "MultiEconomy" -> new MultiEconomyAdapter();
            case "Reserve" -> new ReserveEconomyAdapter();
            default -> setupVault();
        };
    }

    private static EconomyAdapter setupVault() {
        Plugin plugin = DailyBet.getInstance();
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return null;
        }

        RegisteredServiceProvider<Economy> vaultEcoProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (vaultEcoProvider == null) return null;
        return new VaultEconomyAdapter(vaultEcoProvider.getProvider());
    }
}
