package com.megadev.dailybet.object;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Bet implements Comparable<Bet> {
    private final UUID uuid;
    private double cash;

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

        if (this.getCash() == otherBet.getCash()) {
            return 1;
        }

        return Double.compare(this.getCash(), otherBet.getCash());
    }
}
