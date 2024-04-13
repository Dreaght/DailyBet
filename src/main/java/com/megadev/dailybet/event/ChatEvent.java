package com.megadev.dailybet.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class ChatEvent extends CancellableBetEvent {
    private static final HandlerList handlers = new HandlerList();

    @Setter private String message;
    private final Player player;

    public ChatEvent(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
