package com.megadev.dailybet.config;

import com.megadev.dailybet.object.Bet;
import dev.mega.megacore.config.Configurable;
import dev.mega.megacore.config.Deletable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BetConfig extends Configurable implements Deletable {
    protected BetConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    public Set<Bet> getAllBets() {
        Set<Bet> bets = new HashSet<>();

        ConfigurationSection betsSection = config.getConfigurationSection("bets");

        if (betsSection != null) {
            Set<String> keys = betsSection.getKeys(false);

            for (String key : keys) {
                UUID uuid = UUID.fromString(key);

                Bet bet = new Bet(uuid, (int) betsSection.getDouble(key));
                bets.add(bet);
            }
        }

        return bets;
    }

    public double getCash(UUID uuid) {
        return Double.parseDouble((String) getValue("bets." + uuid.toString()));
    }

    public void setCash(UUID uuid, double cash) {
        if (config.get("bets") == null) {
            config.createSection("bets");
        }
        try {
            setValue("bets." + uuid.toString(), cash);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public int getPoints() {
        if (config.get("bets") == null) {
            config.createSection("bets");
        }
        return config.getInt("points");
    }
    
    public void setPoints(int points) {
        try {
            config.set("points", points);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public Date getDate() throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        return timeDateFormat.parse((String) getValue("date"));
    }
    
    public void setDate(Date date) {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        try {
            setValue("date", timeDateFormat.format(date));
        } catch (Exception exception) {

            exception.printStackTrace();
        }

    }

    public void setRunning(boolean isRunning) {
        setValue("is-running", isRunning);
    }

    public boolean isRunning() {
        return (boolean) getValue("is-running");
    }

    @Override
    public void delete() {
        if (config.get("bets") == null)
            return;
        config.set("bets", null);
    }
}
