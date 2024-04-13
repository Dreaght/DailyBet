package ru.legeu.dailybet.command.arg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import ru.legeu.dailybet.command.AbstractCommand;
import ru.legeu.dailybet.manager.BetTaskManager;
import ru.legeu.dailybet.manager.MenuManager;

import java.util.ArrayList;
import java.util.List;

public class TopArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {//&& hasRights(sender)) {
            completions.add("top");
        }

        return completions;
    }

    private boolean hasRights(CommandSender sender) {
        return sender.hasPermission(getPermission()) || sender.isOp();
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        handleCommand(player);
    }

    private void handleCommand(Player player) {
        player.openInventory(MenuManager.getInstance().getInventory());
    }

    @Override
    public void sendUsageMessage(Player player) {
        sendUsageMessage(player, "stop");
    }

    @Override
    public String getPermission() {
        return "bet.command.top";
    }
}
