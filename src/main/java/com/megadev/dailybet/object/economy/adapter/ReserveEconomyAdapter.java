package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ReserveEconomyAdapter implements EconomyAdapter {
    public ReserveEconomyAdapter() {
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
