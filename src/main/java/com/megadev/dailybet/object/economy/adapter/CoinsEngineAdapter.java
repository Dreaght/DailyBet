package com.megadev.dailybet.object.economy.adapter;

import java.util.UUID;

public class CoinsEngineAdapter implements EconomyAdapter, Currency {
    public CoinsEngineAdapter() {
    }

    public String getCurrency() {
        return "";
    }

    public boolean add(UUID player, double amount) {
        return false;
    }

    public boolean subtract(UUID player, double amount) {
        return false;
    }

    public double getBalance(UUID player) {
        return 0.0;
    }

    public boolean setBalance(UUID player, double amount) {
        return false;
    }
}
