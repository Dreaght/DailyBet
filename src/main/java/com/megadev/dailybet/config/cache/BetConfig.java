package com.megadev.dailybet.config.cache;

import dev.mega.megacore.config.Configurable;
import dev.mega.megacore.config.Deletable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BetConfig extends Configurable {
    protected BetConfig(@NotNull Plugin plugin, String... path) {
        super(plugin, path);
    }

    public double getCash(UUID uuid) {
        return Double.parseDouble((String) getValue(uuid.toString()));
    }

    public void setCash(UUID uuid, double cash) {
        setValue(uuid.toString(), cash);
    }
    
    public int getPoints() {
        return (int) getValue("points");
    }
    
    public void setPoints(double points) {
        setValue("points", points);
    }
    
    public Date getDate() throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        return timeDateFormat.parse((String) getValue("date"));
    }
    
    public void setDate(Date date) {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        setValue("date", timeDateFormat.format(date));
    }

    public void delete() {
        deleteConfig();
    }
}
