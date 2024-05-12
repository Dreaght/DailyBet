package com.megadev.dailybet.manager;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BetTaskManagerTest {
    static Server server;
    static Plugin plugin;

    static int points;
    static Date date;

    static BetTaskManager betTaskManager;

    @BeforeAll
    public static void setupAll() {
        Logger logger = Logger.getGlobal();
        server = mock(Server.class);

        when(server.getLogger()).thenReturn(logger);
        when(server.getScheduler()).thenReturn(mock(BukkitScheduler.class));

        Bukkit.setServer(server);

        plugin = mock(Plugin.class);

        BetTaskManager.init(plugin);
        betTaskManager = BetTaskManager.getInstance();

        points = 1000;

        date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 0);
        date = c.getTime();

    }

    @BeforeEach
    public void setupEach() {
        betTaskManager.startBetProcess(points, date, "24h", "GMT+3");
    }

    @Test
    public void testBetTaskManagerRunning() {
        assertTrue(betTaskManager.isRunning());
    }

    @Test
    public void testBetTaskManagerNotRunning() {
        betTaskManager.safeDeleteBetProcess();
        assertFalse(betTaskManager.isRunning());
    }

    @Test
    public void testBetTaskManagerRunningAfterRestart() {
        betTaskManager.restartBetManager();
        assertTrue(betTaskManager.isRunning());
    }

    @Test
    public void testBetTaskManagerDateEquals() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date date1 = c.getTime();

        assertEquals(date1.getSeconds(), betTaskManager.getGiveawayDate().getSeconds());
    }
}
