package ru.legeu.dailybet.object;

import lombok.Getter;
import org.bukkit.entity.Player;

public class Bet implements Comparable<Bet> {
    @Getter
    private Player player;
    @Getter
    private double cash;

    public Bet(Player player, int cash) {
        this.player = player;
        this.cash = cash;
    }

    public Bet(int cash) {
        this.cash = cash;
    }

    public void addCash(int amount) {
        cash += amount;
    }

    @Override
    public int compareTo(Bet otherBet) {
        return Double.compare(this.getCash(), otherBet.getCash());
    }
}
