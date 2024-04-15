package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.util.chat.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.megadev.dailybet.util.parse.ParseData;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StartArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1 && hasRights(sender)) {
            completions.add("start");
        }

        return completions;
    }

    @Override
    public void commandHandler(Player user, String[] args) {
        if (BetTaskManager.getInstance().isRunning()) {
            Color.sendMessage(user, ConfigManager.getInstance().getMessageConfig().getString("messages.command.already-running"));
            return;
        }
        if (args.length < 2) {
            String inputPoints = ConfigManager.getInstance().getMessageConfig().getString("messages.command.input-points");
            Color.sendMessage(user, inputPoints);
            return;
        }

        handleCommand(user, args);
    }

    private boolean hasRights(CommandSender sender) {
        return sender.hasPermission(getPermission()) || sender.isOp();
    }

    private void handleCommand(Player player, String[] args) {
        if (!hasRights(player))
            return;

        try {
            if (args.length < 2) {
                sendUsageMessage(player);
                return;
            }

            if (Integer.parseInt(args[1]) < 0) {
                Color.sendMessage(player, ConfigManager.getInstance().getMessageConfig().getString("messages.command.incorrect-cash"));
                return;
            }

            Date date = ParseData.getDateTimeFromString(Arrays.copyOfRange(args, 2, args.length));
            BetTaskManager.getInstance().startBetProcess(Integer.parseInt(args[1]), date);
            Color.sendMessage(player, ConfigManager.getInstance().getMessageConfig().getString("messages.command.event-started"));
        } catch (ParseException e) {
            sendUsageMessage(player);
        }
    }

    @Override
    public void sendUsageMessage(Player player) {
        sendUsageMessage(player, "start <points> (optional: <hh:mm>) (optional: <dd.MM.yyyy>)");
    }

    @Override
    public String getPermission() {
        return "bet.command.start";
    }
}
