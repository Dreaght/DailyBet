package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.plugin.Plugin;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.task.BetDailyTimer;
import com.megadev.dailybet.util.parse.ParseData;
import com.megadev.dailybet.util.parse.ParsePeriod;

import java.util.Date;

public class BetTaskManager {
    @Getter
    private static BetTaskManager instance;
    @Getter
    private final Plugin plugin;
    @Getter
    private BetManager betManager;
    @Getter
    private BetDailyTimer betDailyTimer;

    private BetTaskManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new BetTaskManager(plugin);
    }

    public void startBetProcess(int points, Date date) {
        this.betManager = new BetManager(points);
        ConfigManager configManager = ConfigManager.getInstance();

        String periodStr = (String) configManager.getSettingsConfig().getValue("interval");
        String timeZoneStr = (String) configManager.getSettingsConfig().getValue("time-zone");

        date = ParseData.getTimezonedDate(date, timeZoneStr);

        long period = ParsePeriod.getPeriodFromString(periodStr);

        Date finalDate = new Date(date.getTime() + period);

        System.out.println(date);
        System.out.println(finalDate);

        System.out.println(ParseData.difference(date, finalDate));

        betDailyTimer = new BetDailyTimer(plugin);
        betDailyTimer.runTaskTimer(plugin, ParseData.difference(date, finalDate) / 50, period / 50);
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
