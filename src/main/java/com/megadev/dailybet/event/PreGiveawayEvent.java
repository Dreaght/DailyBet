package com.megadev.dailybet.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PreGiveawayEvent extends CancellableBetEvent {
    @Getter private static final HandlerList HANDLER_LIST = new HandlerList();

    @Getter @Setter private int points;
    @Getter @Setter private List<Player> players;

    public PreGiveawayEvent(int points, List<Player> players) {
        this.points = points;
        this.players = players;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
