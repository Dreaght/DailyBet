package com.megadev.dailybet.manager;

import com.megadev.dailybet.object.menu.Menu;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.util.cache.inventory.InventoryStateHandler;

import java.util.*;

public class MenuManager {
    @Getter
    private static MenuManager instance;
    @Getter
    private final List<Menu> menus = new ArrayList();
    private final Plugin plugin;
    private BetManager betManager;

    private MenuManager(Plugin plugin) {
        this.plugin = plugin;

        this.startUpdating();
        this.reopenInventoryForPlayers();
    }

    public void loadAllPlayerMenus() {
        Bukkit.getServer().getOnlinePlayers().forEach(this::loadPlayerMenus);
    }

    public void loadPlayerMenus(Player player) {
        Menu menu = new Menu(player);
        this.menus.add(menu);

        menu.loadContent();
    }

    public static void init(Plugin plugin) {
        if (instance == null) {
            instance = new MenuManager(plugin);
        }

    }

    private void reopenInventoryForPlayers() {
        Inventory loadedInventory = null;

        try {
            loadedInventory = InventoryStateHandler.loadInventoryAndDeleteFile(this.plugin);
        } catch (IllegalStateException var7) {
        }

        if (loadedInventory != null) {
            Set<Player> playersToReopen = new HashSet();
            Iterator var3 = Bukkit.getOnlinePlayers().iterator();

            Player player;
            while(var3.hasNext()) {
                player = (Player)var3.next();
                Inventory playerInventory = player.getOpenInventory().getTopInventory();
                if (this.inventoriesEqual(playerInventory, loadedInventory)) {
                    playersToReopen.add(player);
                }
            }

            var3 = playersToReopen.iterator();

            while(var3.hasNext()) {
                player = (Player)var3.next();
                Iterator var8 = this.menus.iterator();

                while(var8.hasNext()) {
                    Menu menu = (Menu)var8.next();
                    if (menu.getPlayer().equals(player)) {
                        player.closeInventory();
                        player.openInventory(menu.getInventory());
                    }
                }
            }

        }
    }

    private boolean inventoriesEqual(Inventory inv1, Inventory inv2) {
        if (inv1.getSize() != inv2.getSize()) {
            return false;
        } else {
            for(int i = 0; i < inv1.getSize(); ++i) {
                ItemStack item1 = inv1.getItem(i);
                ItemStack item2 = inv2.getItem(i);
                if (item1 == null && item2 != null || item1 != null && !item1.equals(item2)) {
                    return false;
                }
            }

            return true;
        }
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

                for (Menu menu : menus) {
                    menu.fillHeads(targetHeads, bets);
                }
            }
        };

        if (Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
            runnable.runTaskTimer(plugin, 0, 20);
        }
    }
}
