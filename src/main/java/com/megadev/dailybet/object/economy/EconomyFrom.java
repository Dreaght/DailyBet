package com.megadev.dailybet.object.economy;

import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyFrom {
    public static boolean deposit(UUID player, int amount) {
        return EconomyHandler.getEconomyFrom().add(player, amount);
    }

    public static boolean withdraw(UUID player, int amount) {
        return EconomyHandler.getEconomyFrom().subtract(player, amount);
    }

    public static boolean setBalance(UUID player, int amount) {
        return EconomyHandler.getEconomyFrom().setBalance(player, amount);
    }

    public static double getBalance(UUID player) {
        return EconomyHandler.getEconomyFrom().getBalance(player);
    }
}
