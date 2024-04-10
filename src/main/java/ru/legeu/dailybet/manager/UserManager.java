package ru.legeu.dailybet.manager;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.legeu.dailybet.DailyBet;
import ru.legeu.dailybet.object.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserManager {
    @Getter
    private final Set<User> users = new HashSet<>();
    @Getter
    private static UserManager instance;

    private UserManager() {
        loadUsers();
    }

    public void init() {
        instance = new UserManager();
    }

    public void loadUsers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            saveUser(player);
        }
    }

    public void saveUser(Player player) {
        users.add(new User(player));
    }

    public boolean isUser(Player player) {
        return getOptUser(player).isPresent();
    }

    private Optional<User> getOptUser(Player player) {
        return users.stream().filter(u -> u.getPlayer().equals(player)).findFirst();
    }

    public void sendMessage(String... strings) {
        for (User user : users) user.sendMessage(strings);
    }

    public User getUser(Player player) {
        Optional<User> optionalUser = getOptUser(player);
        if (!optionalUser.isPresent()) UserManager.getInstance().saveUser(player);
        return optionalUser.orElse(null);
    }
}
