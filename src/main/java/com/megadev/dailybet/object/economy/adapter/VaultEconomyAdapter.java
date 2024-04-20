package com.megadev.dailybet.object.economy.adapter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.entity.Player;

public class VaultEconomyAdapter implements EconomyAdapter {
    private final Economy economy;

    public VaultEconomyAdapter(Economy economy) {
        this.economy = economy;
    }

    @Override
    public boolean add(Player player, double amount) {
        return economy.depositPlayer(player, amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        return economy.withdrawPlayer(player, amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public double getBalance(Player player) {
        return economy.getBalance(player);
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        double currentBalance = this.getBalance(player);
        double diff = Math.abs(amount - currentBalance);

        if (amount > currentBalance) {
            return add(player, diff);
        }else if (amount < currentBalance) {
            return subtract(player, diff);
        }

        return true;
    }
}
