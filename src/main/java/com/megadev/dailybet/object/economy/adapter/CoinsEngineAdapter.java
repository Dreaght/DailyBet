package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.OfflinePlayer;

public class CoinsEngineAdapter implements EconomyAdapter, Currency {
    public CoinsEngineAdapter() {
    }

    public String getCurrency() {
        return "";
    }

    public boolean add(OfflinePlayer player, double amount) {
        return false;
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        return false;
    }

    public double getBalance(OfflinePlayer player) {
        return 0.0;
    }

    public boolean setBalance(OfflinePlayer player, double amount) {
        return false;
    }
}
