package com.megadev.dailybet.object;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.UUID;

@Getter
public class Bet implements Comparable<Bet> {
    private final UUID uuid;
    private double cash;

    public Bet(UUID uuid, int cash) {
        this.uuid = uuid;
        this.cash = cash;
    }

    public void addCash(int amount) {
        this.cash += amount;
    }

    public String getPlayerName() {
        return this.uuid == null ? "unknown" : Bukkit.getPlayer(this.uuid).getName();
    }

    public int compareTo(Bet otherBet) {

        if (this.getCash() == otherBet.getCash()) {
            return 1;
        }

        return Double.compare(this.getCash(), otherBet.getCash());
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public double getCash() {
        return this.cash;
    }
}