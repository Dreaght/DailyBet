package com.megadev.dailybet.manager;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.object.economy.EconomyTo;
import com.megadev.dailybet.util.chat.Color;
import com.megadev.dailybet.util.parse.ParsePlaceholder;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.megadev.dailybet.object.Bet;

import java.util.UUID;


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
        MessageConfig messageConfig = ConfigManager.getInstance().getConfig(MessageConfig.class);
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        String stringPoints = messageConfig.getString("messages.event-finished");
        stringPoints = ParsePlaceholder.parseWithBraces(stringPoints, new String[]{"POINTS"}, new Object[]{betManager.getPoints()});
        Color.broadcastMessage(stringPoints);

        for (Bet bet : betManager.getBets()) {
            handleBet(bet);
        }
    }

    private void handleBet(Bet bet) {
        MessageConfig messageConfig = ConfigManager.getInstance().getConfig(MessageConfig.class);
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        UUID uuid = bet.getUuid();
        if (uuid != null) {
            double award = betManager.getUserAward(uuid);
            this.depositPoints(uuid, award);
            String stringAward = messageConfig.getString("messages.received");
            stringAward = ParsePlaceholder.parseWithBraces(stringAward, new String[]{"AWARD"}, new Object[]{(int)award});
            Color.sendMessage(Bukkit.getPlayer(uuid), stringAward);
        }
    }

    private void depositPoints(UUID player, double points) {
        EconomyTo.deposit(player, points);
    }

}
