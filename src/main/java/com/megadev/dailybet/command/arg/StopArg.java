package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.manager.BetTaskManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

        BetTaskManager.getInstance().getTimer().cancel();
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
