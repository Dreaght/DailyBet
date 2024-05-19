package com.megadev.dailybet.object.economy.adapter;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface EconomyAdapter {
    boolean add(UUID var1, double var2);

    boolean subtract(UUID var1, double var2);

    double getBalance(UUID var1);

    boolean setBalance(UUID var1, double var2);
}
