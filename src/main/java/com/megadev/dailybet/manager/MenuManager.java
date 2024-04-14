package com.megadev.dailybet.manager;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.util.inventory.InventoryStateHandler;

import java.util.*;

public class MenuManager {
    @Getter
    private static MenuManager instance;
    private Plugin plugin;
    @Getter
    private Inventory inventory;

    private static int menuRows;

    private ConfigManager configManager;
    private BetManager betManager;

    private MenuManager(Plugin plugin) {
        this.plugin = plugin;

        configManager = ConfigManager.getInstance();

        menuRows = (int) configManager.getSettingsConfig().getValue("menu-rows");
        loadContent();
        startUpdating();
        reopenInventoryForPlayers();
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new MenuManager(plugin);
        }
    }

    public void loadContent() {
        try {
            inventory = Bukkit.createInventory(
                    null, menuRows * 9,
                    (String) ConfigManager.getInstance().getMessageConfig().getValue("menu.title"));
        } catch (IllegalArgumentException exception) {
            Bukkit.getLogger().severe("Invalid menu row value. Must be between 3 and 6.");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        fillEmpties();
    }

    private void fillEmpties() {
        for (int i = 0; i < menuRows * 9; i++) {
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("§0");
            item.setItemMeta(itemMeta);

            inventory.setItem(i, item);
        }
    }

    private void reopenInventoryForPlayers() {
        Inventory loadedInventory = null;
        try {
            loadedInventory = InventoryStateHandler.loadInventoryAndDeleteFile(plugin);
        } catch (IllegalStateException ignored) {
        }

        if (loadedInventory == null) {
            return;
        }

        Set<Player> playersToReopen = new HashSet<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory playerInventory = player.getOpenInventory().getTopInventory();
            if (inventoriesEqual(playerInventory, loadedInventory)) {
                playersToReopen.add(player);
            }
        }

        for (Player player : playersToReopen) {
            player.closeInventory();
            player.openInventory(inventory);
        }
    }

    private boolean inventoriesEqual(Inventory inv1, Inventory inv2) {
        if (inv1.getSize() != inv2.getSize()) {
            return false;
        }
        for (int i = 0; i < inv1.getSize(); i++) {
            ItemStack item1 = inv1.getItem(i);
            ItemStack item2 = inv2.getItem(i);
            if ((item1 == null && item2 != null) || (item1 != null && !item1.equals(item2))) {
                return false;
            }
        }
        return true;
    }

    public void startUpdating() {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                betManager = BetTaskManager.getInstance().getBetManager();
                Set<Bet> bets = new HashSet<>();
                if (betManager != null) {
                    bets = betManager.getBets(); // to make it fakes --> // turn it to #getBetsWithFakes() instead
                }

                List<ItemStack> targetHeads = new ContentManager(bets).getTargetHeads();
                fillHeads(targetHeads);
            }
        };

        if (Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
            runnable.runTaskTimer(plugin, 0, 20);
        }
    }

    private void fillHeads(List<ItemStack> targetHeads) {
        try {
            fillEmpties();

            inventory.setItem(4, targetHeads.get(0));
            inventory.setItem(12, targetHeads.get(1));
            inventory.setItem(14, targetHeads.get(2));

            for (int i = 19; i < 26; i++) {
                inventory.setItem(i, targetHeads.get(i - 19));
            }
        } catch (IndexOutOfBoundsException ignore) {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
