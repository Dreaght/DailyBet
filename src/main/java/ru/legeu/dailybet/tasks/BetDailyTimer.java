package ru.legeu.dailybet.tasks;

import org.bukkit.plugin.Plugin;
import ru.legeu.dailybet.manager.GiveawayManager;

public class BetDailyTimer extends BetTimerTask {
    public BetDailyTimer(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void run() {
        GiveawayManager.getInstance().distribute();
    }
}
