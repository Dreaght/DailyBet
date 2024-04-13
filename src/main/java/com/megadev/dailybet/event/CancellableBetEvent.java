package com.megadev.dailybet.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public abstract class CancellableBetEvent extends Event implements Cancellable {
    private boolean isCancelled = false;
    @Setter @Getter
    private String cancelMessage = "Sorry, this event was cancelled.";

    public CancellableBetEvent() {
        super(!Bukkit.getServer().isPrimaryThread());
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

}
