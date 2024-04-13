package com.megadev.dailybet.event;

import com.megadev.dailybet.object.Bet;
import lombok.Getter;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
public class BetStopEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final int points;
    private final Set<Bet> bets;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public BetStopEvent(int points, Set<Bet> bets) {
        this.points = points;
        this.bets = bets;
    }
}
