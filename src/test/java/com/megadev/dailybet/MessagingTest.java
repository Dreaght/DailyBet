package com.megadev.dailybet;

import com.earth2me.essentials.*;
import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.Console;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import com.earth2me.essentials.commands.IEssentialsCommand;
import com.earth2me.essentials.commands.NoChargeException;
import com.megadev.dailybet.command.AbstractCommand;
import com.megadev.dailybet.command.arg.BetAmountArg;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MessagingTest {
    private final OfflinePlayerStub base1;
    private final Essentials ess;
    private final FakeServer server;

    public MessagingTest() {
        server = FakeServer.getServer();
        ess = new Essentials(server);
        try {
            ess.setupForTesting(server);
        } catch (final InvalidDescriptionException ex) {
            fail("InvalidDescriptionException");
        } catch (final IOException ex) {
            fail("IOException");
        }
        base1 = createFakePlayer(server, "testPlayer1");
        addPlayer(server, base1);
        ess.getUser(base1);
    }

    private OfflinePlayerStub createFakePlayer(FakeServer server, String name) {
        try {
            Method createPlayerMethod = FakeServer.class.getDeclaredMethod("createPlayer", String.class);
            createPlayerMethod.setAccessible(true);
            return (OfflinePlayerStub) createPlayerMethod.invoke(server, name);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addPlayer(FakeServer server, Player player) {
        try {
            Method addPlayerMethod = FakeServer.class.getDeclaredMethod("addPlayer", Player.class);
            addPlayerMethod.setAccessible(true);
            addPlayerMethod.invoke(server, player);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommand() {
        runCommand("bet", "start 100");
    }

    private void runCommand(final String command, final String args) {
        runCommand(args.split("\\s+"));
    }

    private void runCommand(final String[] args) {
        try {
            BetAmountArg obj = new BetAmountArg();
            Method commandHandlerMethod = BetAmountArg.class.getDeclaredMethod("commandHandler", Player.class, String[].class);
            commandHandlerMethod.setAccessible(true);
            commandHandlerMethod.invoke(obj, base1, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
