package ru.legeu.dailybet.utils;

import lombok.Getter;

import org.bukkit.entity.Player;
import ru.legeu.dailybet.object.Bet;

import java.util.*;

@Getter
public class BetManager {
    private final HashMap<UUID, Bet> bets = new HashMap<>();
    private int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(Player player, int amount) {
        UUID uuid = player.getUniqueId();

        if (!bets.containsKey(player.getUniqueId()))
            bets.put(uuid, new Bet(player, amount));
        else
            bets.get(uuid).addCash(amount);
    }

    public boolean isPresent(Player user) {
        return bets.containsKey(user.getUniqueId());
    }

    public int getInvestedCash(Player user) {
        return bets.get(user.getUniqueId()).getCash();
    }

    public int getUserAward(Player user) {
        return calcPercent(user) * points;
    }
    
    public int calcPercent(Player user) {
        return bets.get(user.getUniqueId()).getCash() / getTotalCash();
    }

    public int getTotalCash() {
        int cash = 0;
        for (UUID uuid : bets.keySet())
            cash += bets.get(uuid).getCash();

        return cash;
    }

    public Set<Bet> getBets() {
        Set<Bet> descendingBets = new TreeSet<>(Comparator.reverseOrder());

        descendingBets.addAll(bets.values());

        return descendingBets;
    }
}
