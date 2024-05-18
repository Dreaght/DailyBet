package com.megadev.dailybet;

import com.megadev.dailybet.config.BetConfig;
import com.megadev.dailybet.listener.JoinListener;
import com.megadev.dailybet.manager.BetManager;
import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.object.economy.EconomyHandler;
import com.megadev.dailybet.object.menu.Menu;
import com.megadev.dailybet.command.BetCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.listener.MenuListener;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.manager.GiveawayManager;
import com.megadev.dailybet.manager.MenuManager;
import com.megadev.dailybet.util.cache.inventory.InventoryStateHandler;

import dev.mega.megacore.MegaCore;

import lombok.Getter;

import org.bukkit.Bukkit;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public final class DailyBet extends MegaCore {
    @Getter
    private static DailyBet instance;

    @Override
    public void enable() {
        instance = this;

        ConfigManager.init(this);
        BetTaskManager.init(this);
        GiveawayManager.init(this);
        MenuManager.init(this);

        MenuManager.getInstance().loadAllPlayerMenus();

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);

        Objects.requireNonNull(this.getCommand("bet")).setExecutor(new BetCommand());
        EconomyHandler.installEconomies();

        try {
            BetTaskManager.getInstance().installBets();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disable() {
        saveBets();
        saveInventory();
    }

    private void saveBets() {
        if (!BetTaskManager.getInstance().isRunning()) {
            return;
        }

        BetConfig betConfig = ConfigManager.getInstance().getConfig(BetConfig.class);
        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        Date date = BetTaskManager.getInstance().getGiveawayDate();

        betConfig.setDate(date);
        betConfig.setPoints(betManager.getPoints());

        for (Bet bet : betManager.getBets())
            betConfig.setCash(bet.getPlayer().getUniqueId(), bet.getCash());
    }

    private void saveInventory() {
        try {
            for (Menu menu : MenuManager.getInstance().getMenus())
                InventoryStateHandler.saveInventory(menu.getInventory(), this);
        } catch (NullPointerException var3) {
            Bukkit.getLogger().warning("Tried to save an inventory, but it does not exist yet!");
        }
    }
}
