package ru.legeu.dailybet;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.legeu.dailybet.object.economy.adapter.CoinsEngineAdapter;
import ru.legeu.dailybet.object.economy.adapter.EconomyAdapter;
import ru.legeu.dailybet.object.economy.adapter.VaultEconomyAdapter;

public class BetEconomyHandler {
    private static DailyBet plugin = null;
    private static EconomyAdapter economyVault = null;
    private static EconomyAdapter economyCoinsEngine = null;

    public static void init(DailyBet plugin) {
        BetEconomyHandler.plugin = plugin;

        if (!BetEconomyHandler.setupEconomy()) {
            plugin.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", plugin.getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        BetEconomyHandler.setupCoinsEngine();
    }

    public static boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> vaultEcoProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (vaultEcoProvider == null) return false;

        economyVault = new VaultEconomyAdapter(vaultEcoProvider.getProvider());
        return economyVault != null;
    }

    public static void setupCoinsEngine() {
        economyCoinsEngine = new CoinsEngineAdapter();
    }

    public static boolean add(Player player, double amount) {
        return economyVault.add(player, amount);
    }

    public static boolean subtract(Player player, double amount) {
        return economyVault.subtract(player, amount);
    }

    public static boolean setBalance(Player player, double amount) {
        return economyVault.setBalance(player, amount);
    }

    public static boolean addCoins(Player player, double amount) {
        return economyCoinsEngine.add(player, amount);
    }

    public static boolean subtractCoins(Player player, double amount) {
        return economyCoinsEngine.subtract(player, amount);
    }

    public static boolean setBalanceCoins(Player player, double amount) {
        return economyCoinsEngine.setBalance(player, amount);
    }
}
