package com.megadev.dailybet.command.arg;

import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.manager.MenuManager;
import com.megadev.dailybet.object.menu.Menu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TopArg extends AbstractCommand {
    public TopArg() {
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("top");
        }

        return completions;
    }

    public void commandHandler(Player player, String[] args) {
        this.handleCommand(player);
    }

    private void handleCommand(Player player) {
        for (Menu menu : MenuManager.getInstance().getMenus())
            player.openInventory(menu.getInventory());

    }

    public void sendUsageMessage(Player player) {
        this.sendUsageMessage(player, "stop");
    }

    public String getPermission() {
        return "";
    }
}
