package com.megadev.dailybet.object.economy.adapter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.OfflinePlayer;

public class VaultEconomyAdapter implements EconomyAdapter {
    private final Economy economy;

    public VaultEconomyAdapter(Economy economy) {
        this.economy = economy;
    }

    public boolean add(OfflinePlayer player, double amount) {
        return this.economy.depositPlayer(player, amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        return this.economy.withdrawPlayer(player, amount).type == ResponseType.SUCCESS;
    }

    public double getBalance(OfflinePlayer player) {
        return this.economy.getBalance(player);
    }

    public boolean setBalance(OfflinePlayer player, double amount) {
        double currentBalance = this.getBalance(player);
        double diff = Math.abs(amount - currentBalance);
        if (amount > currentBalance) {
            return this.add(player, diff);
        } else {
            return !(amount < currentBalance) || this.subtract(player, diff);
        }
    }
}
