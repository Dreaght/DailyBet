package com.megadev.dailybet.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


public class PreBetStartEvent extends CancellableBetEvent {
    @Getter private static final HandlerList HANDLER_LIST = new HandlerList();

    @Getter @Setter private int points;
    @Getter @Setter private Date date;

    public PreBetStartEvent(int points, Date date) {
        this.points = points;
        this.date = date;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
