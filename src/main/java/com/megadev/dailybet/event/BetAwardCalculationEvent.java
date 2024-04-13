package com.megadev.dailybet.event;

import com.megadev.dailybet.object.Bet;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class BetAwardCalculationEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Bet bet;
    @Setter private int award;

    public BetAwardCalculationEvent(Bet bet, int award) {
        this.bet = bet;
        this.award = award;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
