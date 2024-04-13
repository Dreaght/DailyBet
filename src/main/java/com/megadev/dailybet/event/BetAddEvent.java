package com.megadev.dailybet.event;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

@Getter
public class BetAddEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final int cash;
    private final int award;
    private final double percent;
    private final Player investor;

    public BetAddEvent(Player investor, int cash, int award, double percent) {
        super(!Bukkit.getServer().isPrimaryThread());
        this.investor = investor;
        this.cash = cash;
        this.award = award;
        this.percent = percent;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
