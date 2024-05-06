package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.config.cache.BetConfig;
import com.megadev.dailybet.manager.BetManager;
import com.megadev.dailybet.manager.BetTaskManager;
import com.megadev.dailybet.object.economy.EconomyFrom;
import com.megadev.dailybet.util.chat.Color;
import com.megadev.dailybet.util.parse.ParsePlaceholder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BetAmountArg extends AbstractCommand {
    MessageConfig messageConfig = ConfigManager.getInstance().getConfig(MessageConfig.class);

    public BetAmountArg() {
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList();
    }

    public void commandHandler(Player player, String[] args) {

        if (!BetTaskManager.getInstance().isRunning()) {
            Color.sendMessage(player, messageConfig.getString("messages.command.already-running"));
        }

        int cash = Integer.parseInt(args[0]);
        if (cash < 1) {
            Color.sendMessage(player, messageConfig.getString("messages.command.incorrect-cash"));
        } else if (EconomyFrom.getBalance(player) < (double)cash) {
            Color.sendMessage(player, messageConfig.getString("messages.command.not-enough-cash"));
        } else {
            this.handleBet(player, cash);
        }
    }

    public void sendUsageMessage(Player player) {
        this.sendUsageMessage(player, "<amount>");
    }

    public String getPermission() {
        return "";
    }

    private void handleBet(Player user, int cash) {
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        if (betManager == null) {
            Color.sendMessage(user, messageConfig.getString("messages.command.not-started"));
        } else {
            BetConfig betConfig = ConfigManager.getInstance().getConfig(BetConfig.class);
            betConfig.setValue(user.getUniqueId().toString(), String.valueOf(cash));
            EconomyFrom.withdraw(user, cash);
            betManager.addBet(user, cash);
            if (betManager.isPresent(user)) {
                Color.sendMessage(user, messageConfig.getString("messages.command.bet-added"));
            } else {
                Color.sendMessage(user, messageConfig.getString("messages.command.bet-set"));
            }

            String betBalance = messageConfig.getString("messages.command.bet-cash");
            betBalance = ParsePlaceholder.parseWithBraces(betBalance, new String[]{"BET_BALANCE"}, new Object[]{betManager.getInvestedCash(user)});
            Color.sendMessage(user, betBalance);
        }
    }
}

