package com.megadev.dailybet.object.economy;

import org.bukkit.entity.Player;

public class EconomyTo {
    public static boolean deposit(Player player, double amount) {
        return EconomyType.ECONOMY_TO.getAdapter().add(player, amount);
    }

    public static boolean withdraw(Player player, int amount) {
        return EconomyType.ECONOMY_TO.getAdapter().subtract(player, amount);
    }

    public static boolean setBalance(Player player, int amount) {
        return EconomyType.ECONOMY_TO.getAdapter().setBalance(player, amount);
    }

    public static double getBalance(Player player) {
        return EconomyType.ECONOMY_TO.getAdapter().getBalance(player);
    }
}
