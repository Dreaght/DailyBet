package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.manager.BetManager;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.object.economy.EconomyFrom;
import com.megadev.dailybet.util.chat.Color;
import com.megadev.dailybet.util.parse.ParsePlaceholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.megadev.dailybet.BetEconomyHandler;

import java.util.ArrayList;
import java.util.List;

public class BetAmountArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void commandHandler(Player player, String[] args) {
        if (!BetTaskManager.getInstance().isRunning()) {
            Color.sendMessage(player, ConfigManager.getInstance().getMessageConfig().getString("messages.command.already-running"));
        }

        int cash = Integer.parseInt(args[0]);

        handleBet(player, cash);
    }

    @Override
    public void sendUsageMessage(Player player) {
        sendUsageMessage(player, "<amount>");
    }

    @Override
    public String getPermission() {
        return "";
    }

    private void handleBet(Player user, int cash) {
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        MessageConfig messageConfig = ConfigManager.getInstance().getMessageConfig();

        if (betManager == null) {
            Color.sendMessage(user, messageConfig.getString("messages.command.not-started"));
        }

        EconomyFrom.withdraw(user, cash);
        betManager.addBet(user, cash);

        if (betManager.isPresent(user)) {
            Color.sendMessage(user, messageConfig.getString("messages.command.bet-added"));
        } else {
            Color.sendMessage(user, messageConfig.getString("messages.command.bet-set"));
        }

        String betBalance = messageConfig.getString("messages.command.bet-cash");

        betBalance = ParsePlaceholder.parseWithBraces(betBalance,
                new String[]{"BET_BALANCE"},
                new Object[]{ betManager.getInvestedCash(user) });

        Color.sendMessage(user, betBalance);
    }
}
