package ru.legeu.dailybet.manager;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import ru.legeu.dailybet.object.User;

import java.util.Set;

public class GiveawayManager {
    @Getter
    public static GiveawayManager instance;
    @Getter
    private Plugin plugin;

    private GiveawayManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void init(Plugin plugin) {
        instance = new GiveawayManager(plugin);
    }

    public void distribute() {
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        for (User user : getUsersToGiveaway(betManager)) {
            int points = betManager.getUserAward(user);

            depositPoints(user);

            user.sendMessage("You received " + points + " points!");
        }
    }

    private void depositPoints(User user) {
        //TODO: Добавить монетки к PlayerPoints балансу игрока

    }

    private Set<User> getUsersToGiveaway(BetManager betManager) {
        return betManager.getUsers();
    }
}
