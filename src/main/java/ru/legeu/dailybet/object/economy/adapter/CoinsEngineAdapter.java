package ru.legeu.dailybet.object.economy.adapter;

import org.bukkit.entity.Player;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.util.Objects;

public class CoinsEngineAdapter implements EconomyAdapter {
    @Override
    public boolean add(Player player, double amount) {
        CoinsEngineAPI.addBalance(player, getCurrency(), amount);
        return true;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        CoinsEngineAPI.removeBalance(player, getCurrency(), amount);
        return true;
    }

    @Override
    public double getBalance(Player player) {
        return CoinsEngineAPI.getBalance(player, getCurrency());
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        CoinsEngineAPI.setBalance(player, getCurrency(), amount);
        return true;
    }

    private Currency getCurrency() {
        return Objects.requireNonNull(CoinsEngineAPI.getCurrency("coins"));
    }
}
