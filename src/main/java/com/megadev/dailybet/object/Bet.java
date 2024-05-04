package com.megadev.dailybet.object;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Bet implements Comparable<Bet> {
    private final OfflinePlayer player;
    private double cash;

    public Bet(OfflinePlayer player, int cash) {
        this.player = player;
        this.cash = (double)cash;
    }

    public void addCash(int amount) {
        this.cash += (double)amount;
    }

    public String getPlayerName() {
        return this.player == null ? "unknown" : this.player.getName();
    }

    public int compareTo(Bet otherBet) {

        if (this.getCash() == otherBet.getCash()) {
            return 1;
        }

        return Double.compare(this.getCash(), otherBet.getCash());
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public double getCash() {
        return this.cash;
    }
}