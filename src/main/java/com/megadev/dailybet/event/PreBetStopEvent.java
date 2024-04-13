package com.megadev.dailybet.event;

import com.megadev.dailybet.object.Bet;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PreBetStopEvent extends CancellableBetEvent {
    @Getter private static final HandlerList HANDLER_LIST = new HandlerList();

    @Getter @Setter private int points;
    @Getter @Setter private Set<Bet> bets;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
