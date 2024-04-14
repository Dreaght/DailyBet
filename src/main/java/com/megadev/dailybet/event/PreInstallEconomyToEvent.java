package com.megadev.dailybet.event;

import com.megadev.dailybet.object.economy.adapter.EconomyAdapter;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class PreInstallEconomyToEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

     @Setter EconomyAdapter economyTo;

    public PreInstallEconomyToEvent (EconomyAdapter economyTo) {
        this.economyTo = economyTo;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
