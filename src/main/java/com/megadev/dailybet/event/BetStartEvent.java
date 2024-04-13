package com.megadev.dailybet.event;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
public class BetStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final int points;
    private final Date date;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public BetStartEvent(int points, Date date) {
        this.points = points;
        this.date = date;
    }
}
