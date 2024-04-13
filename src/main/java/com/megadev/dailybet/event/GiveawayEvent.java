package com.megadev.dailybet.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class GiveawayEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final int points;
    private final List<Player> players;

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public GiveawayEvent(int points, List<Player> players) {
        this.points = points;
        this.players = players;
    }
}
