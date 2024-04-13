package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.entity.Player;

public interface EconomyAdapter {
    boolean add(Player player, double amount);

    boolean subtract(Player player, double amount);

    double getBalance(Player player);

    boolean setBalance(Player player, double amount);
}
