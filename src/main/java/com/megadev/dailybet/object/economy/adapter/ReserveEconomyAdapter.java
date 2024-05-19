package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReserveEconomyAdapter implements EconomyAdapter {
    public ReserveEconomyAdapter() {
    }

    public boolean add(UUID player, double amount) {
        return false;
    }

    public boolean subtract(UUID player, double amount) {
        return false;
    }

    public double getBalance(UUID player) {
        return 0.0;
    }

    public boolean setBalance(UUID player, double amount) {
        return false;
    }
}
