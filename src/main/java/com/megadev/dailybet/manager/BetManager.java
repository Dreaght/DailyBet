package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.OfflinePlayer;
import com.megadev.dailybet.object.Bet;

import java.util.*;

@Getter
public class BetManager {
    private final HashMap<UUID, Bet> bets = new HashMap();
    @Getter
    private final int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(UUID player, int amount) {
        if (!this.bets.containsKey(player)) {
            this.bets.put(player, new Bet(player, amount));
        } else {
            (this.bets.get(player)).addCash(amount);
        }

    }

    public void addBet(Bet bet) {
        bets.put(bet.getUuid(), bet);
    }

    public boolean isPresent(UUID player) {
        return this.bets.containsKey(player);
    }

    public double getInvestedCash(UUID player) {
        return this.bets.get(player).getCash();
    }

    public double getUserAward(UUID player) {
        return this.calcPercent(player) * (double)this.points;
    }

    public double getUserAward(Bet bet, Set<Bet> bets) {
        return this.calcPercent(bet, bets) * (double)this.points;
    }

    public double calcPercent(UUID player) {
        return this.bets.get(player).getCash() / this.getTotalCash();
    }

    public double calcPercent(Bet bet, Set<Bet> bets) {
        return bet.getCash() / this.getTotalCash(bets);
    }

    public double getTotalCash(Set<Bet> bets) {
        double cash = 0.0;

        Bet bet;
        for(Iterator var4 = bets.iterator(); var4.hasNext(); cash += bet.getCash()) {
            bet = (Bet)var4.next();
        }

        return cash;
    }

    public double getTotalCash() {
        double cash = 0.0;

        for (UUID uuid : bets.keySet()) {
            cash += bets.get(uuid).getCash();
        }

        return cash;
    }

    public Set<Bet> getBets() {
        Set<Bet> descendingBets = new TreeSet(Comparator.reverseOrder());
        descendingBets.addAll(this.bets.values());
        return descendingBets;
    }
}
