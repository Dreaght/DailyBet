package com.megadev.dailybet.object.economy;

import com.megadev.dailybet.object.economy.adapter.EconomyAdapter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EconomyInstaller {

    public static EconomyAdapter setEconomyFrom(String economyName) {
        return getEconomyAdapter(economyName);
    }

    public static EconomyAdapter setEconomyTo(String economyName) {
        return getEconomyAdapter(economyName);
    }

    private static EconomyAdapter getEconomyAdapter(String economyName) {
        try {
            return null;
        } catch (ClassCastException e) {
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("DailyBet")));
            return null;
        }
    }

}
