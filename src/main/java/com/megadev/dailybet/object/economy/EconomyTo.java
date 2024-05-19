package com.megadev.dailybet.object.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyTo {
    public EconomyTo() {
    }

    public static void deposit(UUID player, double amount) {
        EconomyHandler.getEconomyTo().add(player, amount);
    }

    public static boolean withdraw(UUID player, int amount) {
        return EconomyHandler.getEconomyTo().subtract(player, (double)amount);
    }

    public static boolean setBalance(UUID player, int amount) {
        return EconomyHandler.getEconomyTo().setBalance(player, (double)amount);
    }

    public static double getBalance(UUID player) {
        return EconomyHandler.getEconomyTo().getBalance(player);
    }
}
