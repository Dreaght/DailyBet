package ru.legeu.dailybet.command.arg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import ru.legeu.dailybet.BetEconomyHandler;
import ru.legeu.dailybet.command.AbstractCommand;
import ru.legeu.dailybet.manager.BetManager;
import ru.legeu.dailybet.manager.BetTaskManager;
import ru.legeu.dailybet.object.User;

import java.util.ArrayList;
import java.util.List;

public class BetAmountArg extends AbstractCommand {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void commandHandler(User user, String[] args) {
        if (!BetTaskManager.getInstance().isRunning()) {
            user.sendMessage("You can't do it right now!");
        }

        int cash = Integer.parseInt(args[1]);

        handleBet(user, cash);
    }

    @Override
    public void sendUsageMessage(User user) {
        sendUsageMessage(user, "<amount>");
    }

    @Override
    public String getPermission() {
        return "";
    }

    private void handleBet(User user, int cash) {
        BetManager betManager = BetTaskManager.getInstance().getBetManager();

        BetEconomyHandler.subtract(user.getPlayer(), cash);
        betManager.addBet(user, cash);

        if (betManager.userExist(user)) {
            user.sendMessage("§aВы успешно добавили сумму в свою ставку!");
        } else {
            user.sendMessage("§aВы успешно поставили ставку!");
        }

        user.sendMessage("&aВаш счёт на ставках: §e" + betManager.getInvestedCash(user) + "§a$");
    }
}
