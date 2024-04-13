package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.manager.BetManager;
import com.megadev.dailybet.manager.BetTaskManager;
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
            player.sendMessage("You can't do it right now!");
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

        BetEconomyHandler.getInstance().subtract(user.getPlayer(), cash);
        betManager.addBet(user, cash);

        if (betManager.isPresent(user)) {
            user.sendMessage("§aВы успешно добавили сумму в свою ставку!");
        } else {
            user.sendMessage("§aВы успешно поставили ставку!");
        }

        user.sendMessage("&aВаш счёт на ставках: §e" + betManager.getInvestedCash(user) + "§a$");
    }
}
