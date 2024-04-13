package ru.legeu.dailybet;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import ru.legeu.dailybet.object.economy.adapter.CoinsEngineAdapter;
import ru.legeu.dailybet.object.economy.adapter.EconomyAdapter;
import ru.legeu.dailybet.object.economy.adapter.VaultEconomyAdapter;

public class BetEconomyHandler {
    @Getter
    private static BetEconomyHandler instance;

    private Plugin plugin;
    private static EconomyAdapter economyVault = null;
    private static EconomyAdapter economyCoinsEngine = null;

    private BetEconomyHandler(Plugin plugin) {
        this.plugin = plugin;

        if (!setupEconomy()) {
            plugin.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", plugin.getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        setupCoinsEngine();
    }

    public static void init(Plugin plugin) {
        instance = new BetEconomyHandler(plugin);
    }

    public boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> vaultEcoProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (vaultEcoProvider == null) return false;

        economyVault = new VaultEconomyAdapter(vaultEcoProvider.getProvider());
        return economyVault != null;
    }

    public void setupCoinsEngine() {
        economyCoinsEngine = new CoinsEngineAdapter();
    }

    public boolean add(Player player, double amount) {
        return economyVault.add(player, amount);
    }

    public boolean subtract(Player player, double amount) {
        return economyVault.subtract(player, amount);
    }

    public boolean setBalance(Player player, double amount) {
        return economyVault.setBalance(player, amount);
    }

    public boolean addCoins(Player player, double amount) {
        return economyCoinsEngine.add(player, amount);
    }

    public boolean subtractCoins(Player player, double amount) {
        return economyCoinsEngine.subtract(player, amount);
    }

    public boolean setBalanceCoins(Player player, double amount) {
        return economyCoinsEngine.setBalance(player, amount);
    }
}
