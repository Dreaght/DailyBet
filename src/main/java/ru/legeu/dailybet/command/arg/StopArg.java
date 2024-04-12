package ru.legeu.dailybet.command.arg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ru.legeu.dailybet.command.AbstractCommand;
import ru.legeu.dailybet.manager.BetTaskManager;

import java.util.ArrayList;
import java.util.List;

public class StopArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1 && hasRights(sender)) {
            completions.add("stop");
        }

        return completions;
    }

    private boolean hasRights(CommandSender sender) {
        return sender.hasPermission(getPermission()) || sender.isOp();
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (!hasRights(player))
            return;

        BetTaskManager.getInstance().stopBetProcess();
    }

    @Override
    public void sendUsageMessage(Player user) {
        sendUsageMessage(user, "stop");
    }

    @Override
    public String getPermission() {
        return "bet.command.stop";
    }
}
