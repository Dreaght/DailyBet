package com.megadev.dailybet.object.economy;

import org.bukkit.entity.Player;

public class EconomyTo {
    public static boolean deposit(Player player, double amount) {
        return EconomyHandler.getEconomyTo().add(player, amount);
    }

    public static boolean withdraw(Player player, int amount) {
        return EconomyHandler.getEconomyTo().subtract(player, amount);
    }

    public static boolean setBalance(Player player, int amount) {
        return EconomyHandler.getEconomyTo().setBalance(player, amount);
    }

    public static double getBalance(Player player) {
        return EconomyHandler.getEconomyTo().getBalance(player);
    }
}
