package com.megadev.dailybet.object.economy;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import org.bukkit.entity.Player;

public class EconomyTo {

    public static boolean deposit(Player player, int amount) {
        MessageConfig messageConfig = ConfigManager.getInstance().getMessageConfig();
//        switch () {
//
//        }
        return false;
    }

    public static boolean withdraw(Player player, int amount) {
        return false;
    }

} // EconomyTo.deposit(Player player, int amount); CONFIG: MultiEconomy | Call -> MultiEconomyAdapter.deposit()
// EconomyFrom.withdraw(Player player, int amount); CONFIG: Vault | Call -> VaultEconomyAdapter.withdraw()
