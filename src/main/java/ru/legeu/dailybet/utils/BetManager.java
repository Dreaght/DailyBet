package ru.legeu.dailybet.utils;

import lombok.Getter;

import org.bukkit.entity.Player;
import ru.legeu.dailybet.object.Bet;

import java.util.*;

@Getter
public class BetManager {
    private final HashMap<Player, Bet> bets = new HashMap<>();
    private int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(Player player, int amount) {
        if (!bets.containsKey(player))
            bets.put(player, new Bet(player, amount));
        else
            bets.get(player).addCash(amount);
    }

    public void addBet(int amount) {
        bets.put(null, new Bet(amount));
    }

    public boolean isPresent(Player user) {
        return bets.containsKey(user);
    }

    public double getInvestedCash(Player user) {
        return bets.get(user).getCash();
    }

    public double getUserAward(Player user) {
        return calcPercent(user) * points;
    }
    
    public double calcPercent(Player user) {
        return bets.get(user).getCash() / getTotalCash();
    }

    public double getTotalCash() {
        double cash = 0;
        for (Player player : bets.keySet())
            cash += bets.get(player).getCash();

        return cash;
    }

    public Set<Bet> getBets() {
        Set<Bet> descendingBets = new TreeSet<>(Comparator.reverseOrder());

        descendingBets.addAll(bets.values());

        return descendingBets;
    }
}
