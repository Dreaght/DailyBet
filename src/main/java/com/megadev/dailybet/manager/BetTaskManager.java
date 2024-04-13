package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.plugin.Plugin;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.tasks.BetDailyTimer;
import com.megadev.dailybet.utils.parse.ParseData;
import com.megadev.dailybet.utils.parse.ParsePeriod;

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
    private Timer timer;

    private BetTaskManager(Plugin plugin) {
        this.plugin = plugin;
        betDailyTimer = new BetDailyTimer(plugin);
    }

    public static void init(Plugin plugin) {
        instance = new BetTaskManager(plugin);
    }

    public void startBetProcess(int points, Date date) {
        System.out.println("It has began to be running: " + date);

        this.betManager = new BetManager(points);
        ConfigManager configManager = ConfigManager.getInstance();

        String periodStr = (String) configManager.getSettingsConfig().getValue("interval");
        String timeZoneStr = (String) configManager.getSettingsConfig().getValue("time-zone");

        date = ParseData.getTimezonedDate(date, timeZoneStr);

        long period = ParsePeriod.getPeriodFromString(periodStr);

        Date finalDate = new Date(date.getTime() + period);

        timer = new Timer();

        timer.scheduleAtFixedRate(betDailyTimer, finalDate, period);
    }

    public void removeBetProcess() {
        betDailyTimer.cancel();
        timer.cancel();
    }

    public void restartBetManager() {
        int points = betManager.getPoints();

        this.betManager = null;

        this.betManager = new BetManager(points);
    }

    public boolean isRunning() {
        return betManager != null;
    }
}
