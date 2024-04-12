package ru.legeu.dailybet.manager;

import lombok.Getter;

import ru.legeu.dailybet.object.User;

import java.util.HashMap;
import java.util.Set;

@Getter
public class BetManager {
    private final HashMap<User, Integer> bets = new HashMap<>();
    private int points;

    public BetManager(int points) {
        this.points = points;
    }

    public void addBet(User user, int amount) {
        int finiteCash = amount;
        if (bets.containsKey(user))
            finiteCash += bets.get(user);
        bets.put(user, finiteCash);
    }

    public boolean userExist(User user) {
        for (User u : bets.keySet())
            if (u.equals(user)) return true;
        return false;
    }

    public int getInvestedCash(User user) {
        return bets.get(user);
    }

    public int getUserAward(User user) {
        return calcPercent(user) * points;
    }
    
    public int calcPercent(User user) {
        return bets.get(user) / getTotalCash();
    }

    public int getTotalCash() {
        int cash = 0;
        for (User user : bets.keySet())
            cash = bets.get(user);

        return cash;
    }

    public Set<User> getUsers() {
        return bets.keySet();
    }
}



