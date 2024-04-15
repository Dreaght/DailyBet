package com.megadev.dailybet.task;

import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.manager.GiveawayManager;
import org.bukkit.plugin.Plugin;

public class BetDailyTimer extends BetTimerTask {
    public BetDailyTimer(Plugin plugin) {
        super(plugin);
    }

    @Override
    public void run() {
        if (BetTaskManager.getInstance().getBetManager() == null) cancel();
        GiveawayManager.getInstance().distribute();
        BetTaskManager.getInstance().restartBetManager();
    }
}
