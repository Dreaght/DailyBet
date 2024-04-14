package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.entity.Player;

public class MultiEconomyAdapter implements EconomyAdapter, Currency {
    @Override
    public String getCurrency() {
        return "";
    }

    @Override
    public boolean add(Player player, double amount) {
        return false;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        return false;
    }

    @Override
    public double getBalance(Player player) {
        return 0;
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        return false;
    }
}
