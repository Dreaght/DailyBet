package com.megadev.dailybet.object.economy.adapter;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.economy.EconomyHandler;

import me.glaremasters.multieconomy.api.API;

import org.bukkit.entity.Player;

public class MultiEconomyAdapter implements EconomyAdapter, Currency {

    @Override
    public String getCurrency() {
        if (EconomyHandler.getEconomyFrom().equals(this))
            return ConfigManager.getInstance().getMessageConfig().getString("economy-from-currency");
        else {
            return ConfigManager.getInstance().getMessageConfig().getString("economy-to-currency");
        }
    }

    @Override
    public boolean add(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player, uuid, getCurrency())) {
            double balance = getBalance(player);
            API.setAmount(uuid, getCurrency(), (int) (amount + balance));
            return true;
        }

        return true;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player, uuid, getCurrency())) {
            API.setAmount(uuid, getCurrency(), (int) (getBalance(player) - amount));
            return true;
        }

        return true;
    }

    @Override
    public double getBalance(Player player) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player, uuid, getCurrency())) {
            return Double.parseDouble(API.getAmount(uuid, getCurrency()));
        }
        return 0;
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player, uuid, getCurrency())) {
            API.setAmount(uuid, getCurrency(), (int) amount);
            return true;
        }


        return true;
    }
}
