package ru.legeu.dailybet.manager;

import lombok.Getter;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.legeu.dailybet.BetEconomyHandler;
import ru.legeu.dailybet.object.Bet;
import ru.legeu.dailybet.utils.BetManager;

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
        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        for (Bet bet : getBets(betManager)) {
            Player player = bet.getPlayer();
            int points = betManager.getUserAward(player);

            depositPoints(bet.getPlayer(), points);

            player.sendMessage("You received " + points + " points!");
        }
    }

    private void depositPoints(Player player, int points) {
        BetEconomyHandler.addCoins(player, points);
    }

    private Set<Bet> getBets(BetManager betManager) {
        return betManager.getBets();
    }
}
