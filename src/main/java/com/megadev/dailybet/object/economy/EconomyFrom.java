package com.megadev.dailybet.object.economy;

import org.bukkit.entity.Player;

public class EconomyFrom {
    public static boolean deposit(Player player, int amount) {
        return EconomyHandler.getEconomyFrom().add(player, amount);
    }

    public static boolean withdraw(Player player, int amount) {
        return EconomyHandler.getEconomyFrom().subtract(player, amount);
    }

    public static boolean setBalance(Player player, int amount) {
        return EconomyHandler.getEconomyFrom().setBalance(player, amount);
    }

    public static double getBalance(Player player) {
        return EconomyHandler.getEconomyFrom().getBalance(player);
    }
}
