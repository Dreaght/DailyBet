package com.megadev.dailybet.object.menu;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public record MenuItem(ItemStack itemStack, OfflinePlayer player) {}
