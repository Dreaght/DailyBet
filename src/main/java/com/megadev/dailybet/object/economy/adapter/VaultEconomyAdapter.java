package com.megadev.dailybet.object.economy.adapter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;

import java.util.UUID;

public class VaultEconomyAdapter implements EconomyAdapter {
    private final Economy economy;

    public VaultEconomyAdapter(Economy economy) {
        this.economy = economy;
    }

    public boolean add(UUID player, double amount) {
        return this.economy.depositPlayer(Bukkit.getOfflinePlayer(player), amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    public boolean subtract(UUID player, double amount) {
        return this.economy.withdrawPlayer(Bukkit.getOfflinePlayer(player), amount).type == ResponseType.SUCCESS;
    }

    public double getBalance(UUID player) {
        return this.economy.getBalance(Bukkit.getOfflinePlayer(player));
    }

    public boolean setBalance(UUID player, double amount) {
        double currentBalance = this.getBalance(player);
        double diff = Math.abs(amount - currentBalance);
        if (amount > currentBalance) {
            return this.add(player, diff);
        } else {
            return !(amount < currentBalance) || this.subtract(player, diff);
        }
    }
}
