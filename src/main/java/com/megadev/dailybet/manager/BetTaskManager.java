package com.megadev.dailybet.manager;

import com.megadev.dailybet.config.SettingsConfig;
import com.megadev.dailybet.config.BetConfig;
import com.megadev.dailybet.object.Bet;
import lombok.Getter;

import org.bukkit.plugin.Plugin;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.task.BetDailyTimer;
import com.megadev.dailybet.util.parse.ParseDate;
import com.megadev.dailybet.util.parse.ParsePeriod;

import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BetTaskManager {
    @Getter
    private static BetTaskManager instance;
    @Getter
    private final Plugin plugin;
    @Getter
    private BetManager betManager;
    private BetDailyTimer betDailyTimer;
    @Getter
    private Date giveawayDate;

    private BetTaskManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void init(Plugin plugin) {
        instance = new BetTaskManager(plugin);
    }

    public void startBetProcess(int points, Date date) {
        SettingsConfig settingsConfig = ConfigManager.getInstance().getConfig(SettingsConfig.class);

        if (settingsConfig == null) {
            return;
        }

        String periodStr = settingsConfig.getString("interval");
        String timeZoneStr = settingsConfig.getString("time-zone");

        startBetProcess(points, date, periodStr, timeZoneStr);
    }

    public void startBetProcess(int points, Date date, String interval, String timeZone) {
        ConfigManager.getInstance().getConfig(BetConfig.class).setRunning(true);
        this.betManager = new BetManager(points);
        date = ParseDate.getTimezonedDate(date, timeZone);
        long period = ParsePeriod.getPeriodFromString(interval);

        this.giveawayDate = date;

        betDailyTimer = new BetDailyTimer(plugin);
        betDailyTimer.runTaskTimer(plugin, ParseDate.difference(new Date(), date) / 50, period / 50);
    }

    public void safeDeleteBetProcess() {
        if (betManager != null) {
            betManager = null;
        }
    }

    public void removeBetProcess() {
        if (betDailyTimer != null && !betDailyTimer.isCancelled()) {
            betDailyTimer.cancel();
        }

        ConfigManager.getInstance().getConfig(BetConfig.class).setRunning(false);

        betDailyTimer = new BetDailyTimer(plugin);
        betManager = null;
    }

    public void restartBetManager() {
        int points = betManager.getPoints();

        this.giveawayDate = new Date();

        this.betManager = null;
        this.betManager = new BetManager(points);
    }

    public boolean isRunning() {
        return betManager != null;
    }

    public void installBets() throws ParseException {
        BetConfig betConfig = ConfigManager.getInstance().getConfig(BetConfig.class);

        if (!betConfig.isRunning())
            return;

        Date newDate = new Date();

        String interval = ConfigManager.getInstance().getConfig(SettingsConfig.class).getString("interval");
        long period = ParsePeriod.getPeriodFromString(interval);

        if (betConfig.getDate().before(newDate)) {
            Calendar c = Calendar.getInstance();
            c.setTime(betConfig.getDate());
            c.add(Calendar.MILLISECOND, (int) period);
            newDate = c.getTime();
        }

        startBetProcess(betConfig.getPoints(), newDate);

        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        for (Bet bet : betConfig.getAllBets())
            betManager.addBet(bet);
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
