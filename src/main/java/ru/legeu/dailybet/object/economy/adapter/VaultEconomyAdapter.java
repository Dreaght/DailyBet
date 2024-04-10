package ru.legeu.dailybet.object.economy.adapter;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class VaultEconomyAdapter implements EconomyAdapter {
    private final Economy economy;

    public VaultEconomyAdapter(Economy economy) {
        this.economy = economy;
    }

    @Override
    public boolean add(String accountName, double amount) {
        return economy.depositPlayer(accountName, amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public boolean subtract(String accountName, double amount) {
        return economy.withdrawPlayer(accountName, amount).type == EconomyResponse.ResponseType.SUCCESS;
    }

    @Override
    public double getBalance(String accountName) {
        return economy.getBalance(accountName);
    }

    @Override
    public boolean setBalance(String accountName, double amount) {
        double currentBalance = getBalance(accountName);
        double diff = Math.abs(amount - currentBalance);

        if (amount > currentBalance) {
            return add(accountName, diff);
        }else if (amount < currentBalance) {
            return subtract(accountName, diff);
        }

        return true;
    }
}
