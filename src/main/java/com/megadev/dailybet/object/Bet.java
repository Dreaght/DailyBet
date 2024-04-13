package com.megadev.dailybet.object;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;

public class Bet implements Comparable<Bet> {
    @Getter private UUID uuid;
    @Getter private double cash;

    public Bet(UUID uuid, int cash) {
        this.uuid = uuid;
        this.cash = cash;
    }

    public Bet(int cash) {
        this.uuid = UUID.randomUUID();
        this.cash = cash;
    }

    public void addCash(int amount) {
        cash += amount;
    }

    public String getPlayerName() {
        if (Bukkit.getPlayer(uuid) == null) {
            return "fake_" + (int) (Math.random() * 1000);
        }

        return Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName();
    }

    @Override
    public int compareTo(Bet otherBet) {
        return Double.compare(this.getCash(), otherBet.getCash());
    }
}
