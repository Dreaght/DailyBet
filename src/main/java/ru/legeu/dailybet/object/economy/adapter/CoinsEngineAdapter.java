package ru.legeu.dailybet.object.economy.adapter;

import org.bukkit.entity.Player;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;

import java.util.Objects;

public class CoinsEngineAdapter implements EconomyAdapter {
    @Override
    public boolean add(Player player, double amount) {
        CoinsEngineAPI.addBalance(player, Objects.requireNonNull(CoinsEngineAPI.getCurrency("coins")), amount);
        return true;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        CoinsEngineAPI.removeBalance(player, Objects.requireNonNull(CoinsEngineAPI.getCurrency("coins")), amount);
        return true;
    }

    @Override
    public double getBalance(Player player) {
        return CoinsEngineAPI.getBalance(player, Objects.requireNonNull(CoinsEngineAPI.getCurrency("coins")));
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        CoinsEngineAPI.setBalance(player, Objects.requireNonNull(CoinsEngineAPI.getCurrency("coins")), amount);
        return true;
    }
}
