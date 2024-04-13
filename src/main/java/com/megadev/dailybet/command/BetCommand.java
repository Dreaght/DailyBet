package com.megadev.dailybet.command;

import com.megadev.dailybet.command.arg.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BetCommand implements CommandExecutor, TabCompleter {
    private final Map<String, AbstractCommand> subcommands;

    public BetCommand() {
        this.subcommands = new HashMap<>();
        subcommands.put("start", new StartArg());
        subcommands.put("stop", new StopArg());
        subcommands.put("top", new TopArg());
        subcommands.put("fake", new FakeBetAmountArg());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0 && contains(args[0])) {
            subcommands.get(args[0].toLowerCase()).onCommand(sender, command, label, args);
        } else if (args.length == 1 && !contains(args[0])) {
            new BetAmountArg().onCommand(sender, command, label, args);
        } else {
            sender.sendMessage(String.format("Usage: /bet %s", subcommands.keySet()));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(subcommands.keySet());
        } else if (subcommands.containsKey(args[0].toLowerCase())) {
            return subcommands.get(args[0].toLowerCase()).onTabComplete(sender, command, alias, args);
        }
        return Collections.emptyList();
    }

    private boolean contains(String argStr) {
        return subcommands.containsKey(argStr.toLowerCase());
    }
}
