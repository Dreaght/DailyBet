package com.megadev.dailybet.object.economy.adapter;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.economy.EconomyHandler;

import me.glaremasters.multieconomy.api.API;

import org.bukkit.entity.Player;

public class MultiEconomyAdapter implements EconomyAdapter, Currency {

    @Override
    public String getCurrency() {
        if (EconomyHandler.getEconomyFrom() instanceof MultiEconomyAdapter)
            return ConfigManager.getInstance().getSettingsConfig().getString("economy-from-currency");
        else {
            return ConfigManager.getInstance().getSettingsConfig().getString("economy-to-currency");
        }
    }

    @Override
    public boolean add(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (dataExist(player)) {
            double balance = getBalance(player);

            System.out.println(getBalance(player));

            API.setAmount(uuid, getCurrency(), (int) (amount + balance));

            System.out.println(getBalance(player));
            return true;
        }
        return true;
    }

    @Override
    public boolean subtract(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (dataExist(player)) {
            API.setAmount(uuid, getCurrency(), (int) (getBalance(player) - amount));
            return true;
        }
        return true;
    }

    @Override
    public double getBalance(Player player) {
        String uuid = player.getUniqueId().toString();
        if (dataExist(player)) {
            return Double.parseDouble(API.getAmount(uuid, getCurrency()));
        }
        return 0;
    }

    @Override
    public boolean setBalance(Player player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (dataExist(player)) {
            API.setAmount(uuid, getCurrency(), (int) amount);
            return true;
        }

        return true;
    }

    private boolean dataExist(Player player) {
        return API.checkDataExist(player, String.valueOf(player.getUniqueId()), getCurrency());
    }
}
