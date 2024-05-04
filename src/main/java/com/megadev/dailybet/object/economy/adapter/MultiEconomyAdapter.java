package com.megadev.dailybet.object.economy.adapter;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.economy.EconomyHandler;

import me.glaremasters.multieconomy.api.API;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MultiEconomyAdapter implements EconomyAdapter, Currency {
    public MultiEconomyAdapter() {
    }

    public String getCurrency() {
        return EconomyHandler.getEconomyFrom().equals(this) ? ConfigManager.getInstance().getMessageConfig().getString("economy-from-currency") : ConfigManager.getInstance().getMessageConfig().getString("economy-to-currency");
    }

    public boolean add(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player.getPlayer(), uuid, this.getCurrency())) {
            double balance = this.getBalance(player);
            API.setAmount(uuid, this.getCurrency(), (int)(amount + balance));
            return true;
        } else {
            return true;
        }
    }

    public boolean subtract(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player.getPlayer(), uuid, this.getCurrency())) {
            API.setAmount(uuid, this.getCurrency(), (int)(this.getBalance(player) - amount));
            return true;
        } else {
            return true;
        }
    }

    public double getBalance(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();
        return API.checkDataExist(player.getPlayer(), uuid, this.getCurrency()) ? Double.parseDouble(API.getAmount(uuid, this.getCurrency())) : 0.0;
    }

    public boolean setBalance(OfflinePlayer player, double amount) {
        String uuid = player.getUniqueId().toString();
        if (API.checkDataExist(player.getPlayer(), uuid, this.getCurrency())) {
            API.setAmount(uuid, this.getCurrency(), (int)amount);
            return true;
        } else {
            return true;
        }
    }
}
