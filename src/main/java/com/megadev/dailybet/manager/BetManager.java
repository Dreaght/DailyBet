package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.OfflinePlayer;
import com.megadev.dailybet.object.Bet;

import java.util.*;

@Getter
public class BetManager {
    private final HashMap<OfflinePlayer, Bet> bets = new HashMap();
    @Getter
    private final int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(OfflinePlayer player, int amount) {
        if (!this.bets.containsKey(player)) {
            this.bets.put(player, new Bet(player, amount));
        } else {
            ((Bet)this.bets.get(player)).addCash(amount);
        }

    }

    public boolean isPresent(OfflinePlayer player) {
        return this.bets.containsKey(player);
    }

    public double getInvestedCash(OfflinePlayer player) {
        return ((Bet)this.bets.get(player)).getCash();
    }

    public double getUserAward(OfflinePlayer player) {
        return this.calcPercent(player) * (double)this.points;
    }

    public double getUserAward(Bet bet, Set<Bet> bets) {
        return this.calcPercent(bet, bets) * (double)this.points;
    }

    public double calcPercent(OfflinePlayer player) {
        return ((Bet)this.bets.get(player)).getCash() / this.getTotalCash();
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

        OfflinePlayer player;
        for(Iterator var3 = this.bets.keySet().iterator(); var3.hasNext(); cash += ((Bet)this.bets.get(player)).getCash()) {
            player = (OfflinePlayer)var3.next();
        }

        return cash;
    }

    public Set<Bet> getBets() {
        Set<Bet> descendingBets = new TreeSet(Comparator.reverseOrder());
        descendingBets.addAll(this.bets.values());
        return descendingBets;
    }

}
