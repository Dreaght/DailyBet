package ru.legeu.dailybet.manager;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import ru.legeu.dailybet.tasks.BetDailyTimer;

import java.util.Date;
import java.util.Timer;

public class BetTaskManager {
    @Getter
    private static BetTaskManager instance;
    @Getter
    private final Plugin plugin;
    @Getter
    private BetManager betManager;
    @Getter
    private BetDailyTimer betDailyTimer;
    @Getter
    private final Timer timer;

    private BetTaskManager(Plugin plugin) {
        this.plugin = plugin;
        timer = new Timer();
    }

    public static void init(Plugin plugin) {
        instance = new BetTaskManager(plugin);
    }

    public void startBetProcess(int points, Date date) {
        this.betManager = new BetManager(points);
        timer.schedule(betDailyTimer, date, 1000 * 60 * 60 * 24);
    }

    public void stopBetProcess() {
        timer.cancel();
    }

    public boolean isRunning() {
        return betManager != null;
    }
}
