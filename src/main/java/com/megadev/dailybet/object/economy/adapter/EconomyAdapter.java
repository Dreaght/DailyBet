package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface EconomyAdapter {
    boolean add(OfflinePlayer var1, double var2);

    boolean subtract(OfflinePlayer var1, double var2);

    double getBalance(OfflinePlayer var1);

    boolean setBalance(OfflinePlayer var1, double var2);
}
