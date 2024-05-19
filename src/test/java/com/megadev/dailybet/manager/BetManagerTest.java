package com.megadev.dailybet.manager;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BetManagerTest {
    BetManager betManager;

    @BeforeEach
    public void setup() {
        betManager = new BetManager(100);
    }

    @Test
    public void testBet() {
        int min = 0;
        int max = 10;
        for (int i = min; i <= max; i++) {
            Player player = mock(Player.class);
            when(player.getUniqueId()).thenReturn(UUID.randomUUID());

            betManager.addBet(player.getUniqueId(), i);
        }

        assertNotEquals(IntStream.rangeClosed(min, max).sum() + 1, betManager.getTotalCash());
    }
}