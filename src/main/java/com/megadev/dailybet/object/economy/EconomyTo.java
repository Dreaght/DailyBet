package com.megadev.dailybet.object.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EconomyTo {
    public EconomyTo() {
    }

    public static void deposit(OfflinePlayer player, double amount) {
        EconomyHandler.getEconomyTo().add(player, amount);
    }

    public static boolean withdraw(OfflinePlayer player, int amount) {
        return EconomyHandler.getEconomyTo().subtract(player, (double)amount);
    }

    public static boolean setBalance(OfflinePlayer player, int amount) {
        return EconomyHandler.getEconomyTo().setBalance(player, (double)amount);
    }

    public static double getBalance(OfflinePlayer player) {
        return EconomyHandler.getEconomyTo().getBalance(player);
    }
}
