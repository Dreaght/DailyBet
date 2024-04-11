package ru.legeu.dailybet.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ru.legeu.dailybet.manager.UserManager;
import ru.legeu.dailybet.object.User;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if (args.length == 0) return false;
        Player player = (Player) commandSender;

        UserManager userManager =  UserManager.getInstance();
        User user = userManager.getUser(player);

        if (player.hasPermission(getPermission())) {
            return false;
        }

        commandHandler(user, args);

        return true;
    }

    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                               @NotNull String alias, @NotNull String[] args);

    public void sendUsageMessage(User user, String message) {
        user.sendMessage(String.format("Usage: /%s %s", "bet", message));
    }

    public abstract void commandHandler(User user, String[] args);

    public abstract void sendUsageMessage(User user);

    public abstract String getPermission();
}
