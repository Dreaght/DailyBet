package com.megadev.dailybet.event;

import com.megadev.dailybet.object.Bet;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class BetPercentCalculationEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Bet bet;
    @Setter private int totalCash;

    public BetPercentCalculationEvent(Bet bet, int totalCash) {
        this.bet = bet;
        this.totalCash = totalCash;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
