package com.megadev.dailybet.object.economy;

import org.bukkit.entity.Player;

public class EconomyFrom {
    public static boolean deposit(Player player, int amount) {
        return EconomyType.ECONOMY_FROM.getAdapter().add(player, amount);
    }

    public static boolean withdraw(Player player, int amount) {
        return EconomyType.ECONOMY_FROM.getAdapter().subtract(player, amount);
    }

    public static boolean setBalance(Player player, int amount) {
        return EconomyType.ECONOMY_FROM.getAdapter().setBalance(player, amount);
    }

    public static double getBalance(Player player) {
        return EconomyType.ECONOMY_FROM.getAdapter().getBalance(player);
    }
}
