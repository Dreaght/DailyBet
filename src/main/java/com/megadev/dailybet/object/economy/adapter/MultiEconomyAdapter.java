package com.megadev.dailybet.object.economy.adapter;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.SettingsConfig;
import com.megadev.dailybet.object.economy.EconomyHandler;

import me.glaremasters.multieconomy.api.API;

import org.bukkit.Bukkit;

import java.util.UUID;

public class MultiEconomyAdapter implements EconomyAdapter, Currency {
    SettingsConfig settingsConfig = ConfigManager.getInstance().getConfig(SettingsConfig.class);

    public MultiEconomyAdapter() {
    }

    public String getCurrency() {
        if (EconomyHandler.getEconomyFrom() instanceof MultiEconomyAdapter)
            return settingsConfig.getString("economy-from-currency");
        else {
            return settingsConfig.getString("economy-to-currency");
        }
    }

    public boolean add(UUID uuid, double amount) {
        if (dataExist(uuid)) {
            double balance = getBalance(uuid);

            API.setAmount(uuid.toString(), getCurrency(), (int) (amount + balance));

            return true;
        }
        return true;
    }

    public boolean subtract(UUID uuid, double amount) {
        if (dataExist(uuid)) {
            API.setAmount(uuid.toString(), getCurrency(), (int) (getBalance(uuid) - amount));
            return true;
        }
        return true;
    }

    public double getBalance(UUID uuid) {
        if (dataExist(uuid)) {
            return Double.parseDouble(API.getAmount(uuid.toString(), getCurrency()));
        }
        return 0;
    }

    public boolean setBalance(UUID uuid, double amount) {
        if (dataExist(uuid)) {
            API.setAmount(uuid.toString(), getCurrency(), (int) amount);
            return true;
        }

        return true;
    }

    private boolean dataExist(UUID player) {
        return API.checkDataExist(Bukkit.getPlayer(player), player.toString(), getCurrency());
    }
}
