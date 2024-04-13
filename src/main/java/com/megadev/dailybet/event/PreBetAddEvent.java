package com.megadev.dailybet.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class PreBetAddEvent extends CancellableBetEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Setter private int cash;
    @Setter private int award;
    @Setter private double percent;
    @Setter private Player investor;

    public PreBetAddEvent(Player investor, int cash, int award, double percent) {
        this.investor = investor;
        this.cash = cash;
        this.award = award;
        this.percent = percent;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
