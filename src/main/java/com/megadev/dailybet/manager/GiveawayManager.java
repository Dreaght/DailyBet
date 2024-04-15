package com.megadev.dailybet.manager;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.object.economy.EconomyTo;
import com.megadev.dailybet.util.chat.Color;
import com.megadev.dailybet.util.parse.ParsePlaceholder;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.megadev.dailybet.object.Bet;

import java.util.Set;

public class GiveawayManager {
    @Getter
    public static GiveawayManager instance;
    @Getter
    private Plugin plugin;

    private GiveawayManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new GiveawayManager(plugin);
    }

    public void distribute() {
        MessageConfig messageConfig = ConfigManager.getInstance().getMessageConfig();
        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        String stringPoints = messageConfig.getString("messages.event-finished");

        stringPoints = ParsePlaceholder.parseWithBraces(stringPoints,
                new String[]{"POINTS"},
                new Object[]{ betManager.getPoints() });

        Color.broadcastMessage(stringPoints);

        for (Bet bet : getBets(betManager)) {
            handleBet(bet);
        }
    }

    private void handleBet(Bet bet) {
        MessageConfig messageConfig = ConfigManager.getInstance().getMessageConfig();
        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        Player player = Bukkit.getPlayer(bet.getUuid());
        if (player == null) {
            return;
        }

        double award = betManager.getUserAward(player);

        depositPoints(player, award);

        String stringAward = messageConfig.getString("messages.received");

        stringAward = ParsePlaceholder.parseWithBraces(stringAward,
                new String[]{"AWARD"},
                new Object[]{ award });

        Color.sendMessage(player, stringAward);
    }

    private void depositPoints(Player player, double points) {
        EconomyTo.deposit(player, points);
    }

    private Set<Bet> getBets(BetManager betManager) {
        return betManager.getBets();
    }
}
