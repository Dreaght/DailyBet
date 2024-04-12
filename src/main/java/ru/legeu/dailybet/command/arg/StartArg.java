package ru.legeu.dailybet.command.arg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import ru.legeu.dailybet.command.AbstractCommand;
import ru.legeu.dailybet.manager.BetTaskManager;
import ru.legeu.dailybet.object.User;
import ru.legeu.dailybet.utils.ParseData;

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
    public void commandHandler(User user, String[] args) {
        if (BetTaskManager.getInstance().isRunning()) {
            user.sendMessage("§cИ так запущено уже");
            return;
        }
        handleCommand(user, args);
    }

    private boolean hasRights(CommandSender sender) {
        return sender.hasPermission(getPermission()) || sender.isOp();
    }

    private void handleCommand(User user, String[] args) {
        Player player = user.getPlayer();
        if (!hasRights(player))
            return;

        try {
            Date date = ParseData.getDateTimeFromString(Arrays.copyOfRange(args, 2, args.length));
            BetTaskManager.getInstance().startBetProcess(Integer.parseInt(args[0]), date);
        } catch (ParseException e) {
            sendUsageMessage(user);
        }
    }

    @Override
    public void sendUsageMessage(User user) {
        sendUsageMessage(user, "start <points> (optional: <hh:mm>) (optional: <dd.MM.yyyy>)");
    }

    @Override
    public String getPermission() {
        return "bet.command.start";
    }
}
