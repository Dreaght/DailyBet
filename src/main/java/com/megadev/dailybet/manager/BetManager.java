package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.entity.Player;
import com.megadev.dailybet.object.Bet;

import java.util.*;

@Getter
public class BetManager {
    private final HashMap<UUID, Bet> bets = new HashMap<>();
    private int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(Player player, int amount) {
        addBet(player.getUniqueId(), amount);
    }

    public void addBet(UUID uuid, int amount) {
        if (!bets.containsKey(uuid))
            bets.put(uuid, new Bet(uuid, amount));
        else
            bets.get(uuid).addCash(amount);
    }

    public boolean isPresent(Player user) {
        return bets.containsKey(user);
    }

    public double getInvestedCash(Player user) {
        return bets.get(user.getUniqueId()).getCash();
    }

    public double getUserAward(Player user) {
        return calcPercent(user) * points;
    }

    public double getUserAward(Bet bet, Set<Bet> bets) {
        return calcPercent(bet, bets) * points;
    }
    
    public double calcPercent(Player user) {
        return calcPercent(user.getUniqueId());
    }

    public double calcPercent(UUID uuid) {
        return bets.get(uuid).getCash() / getTotalCash();
    }

    public double calcPercent(Bet bet, Set<Bet> bets) {
        return bet.getCash() / getTotalCash(bets);
    }

    public double calcPercent() {
        return Math.random();
    }

    public double getTotalCash(Set<Bet> bets) {
        double cash = 0;

        for (Bet bet : bets)
            cash += bet.getCash();

        return cash;
    }

    public double getTotalCash() {
        double cash = 0;
        for (UUID uuid : bets.keySet())
            cash += bets.get(uuid).getCash();

        return cash;
    }

    public Set<Bet> getBets() {
        Set<Bet> descendingBets = new TreeSet<>(Comparator.reverseOrder());

        descendingBets.addAll(bets.values());

        return descendingBets;
    }

    public Set<Bet> getBetsWithFakes() {
        Set<Bet> descendingBets = new TreeSet<>(Comparator.reverseOrder());

        descendingBets.addAll(bets.values());

        for (int i = 0; i < 15 - descendingBets.size(); i++) {
            descendingBets.add(new Bet((int) (Math.random() * 1000)));
        }

        return descendingBets;
    }
}
