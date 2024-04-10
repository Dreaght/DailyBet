package ru.legeu.dailybet;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.legeu.dailybet.object.economy.adapter.EconomyAdapter;
import ru.legeu.dailybet.object.economy.adapter.VaultEconomyAdapter;

public class BetEconomyHandler {
    private static DailyBet plugin = null;
    private static EconomyAdapter economy = null;

    public static void init(DailyBet plugin) {
        BetEconomyHandler.plugin = plugin;
    }

    public static boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> vaultEcoProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (vaultEcoProvider == null) return false;

        economy = new VaultEconomyAdapter(vaultEcoProvider.getProvider());
        return economy != null;
    }

    public static boolean add(String accountName, double amount) {
        return economy.add(accountName, amount);
    }

    public static boolean subtract(String accountName, double amount) {
        return economy.subtract(accountName, amount);
    }

    public static boolean setBalance(String accountName, double amount) {
        return economy.setBalance(accountName, amount);
    }
}
