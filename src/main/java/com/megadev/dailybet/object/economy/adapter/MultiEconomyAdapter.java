package com.megadev.dailybet.object.economy.adapter;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.SettingsConfig;
import com.megadev.dailybet.object.economy.EconomyHandler;

import me.glaremasters.multieconomy.api.API;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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

    public boolean add(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();

        if (dataExist((Player) player)) {
            double balance = getBalance(player);

            System.out.println(getBalance(player));

            API.setAmount(uuid, getCurrency(), (int) (amount + balance));

            System.out.println(getBalance(player));
            return true;
        }
        return true;
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();

        if (dataExist(player.getPlayer())) {
            API.setAmount(uuid, getCurrency(), (int) (getBalance(player) - amount));
            return true;
        }
        return true;
    }

    public double getBalance(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        if (dataExist((Player) player)) {
            return Double.parseDouble(API.getAmount(uuid, getCurrency()));
        }
        return 0;
    }

    public boolean setBalance(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();

        if (dataExist((Player) player)) {
            API.setAmount(uuid, getCurrency(), (int) amount);
            return true;
        }

        return true;
    }

    private boolean dataExist(Player player) {
        return API.checkDataExist(player, String.valueOf(player.getUniqueId()), getCurrency());
    }
}
