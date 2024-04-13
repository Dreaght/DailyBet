package ru.legeu.dailybet.manager;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import ru.legeu.dailybet.config.ConfigManager;
import ru.legeu.dailybet.config.MessageConfig;
import ru.legeu.dailybet.object.Bet;
import ru.legeu.dailybet.utils.BetManager;
import ru.legeu.dailybet.utils.inventory.InventoryStateHandler;
import ru.legeu.dailybet.utils.parse.ParsePlaceholder;

import java.util.*;

public class MenuManager {
    @Getter
    private static MenuManager instance;
    private Plugin plugin;
    @Getter
    private Inventory inventory;

    private static int menuRows;

    private MenuManager(Plugin plugin) {
        this.plugin = plugin;

        ConfigManager configManager = ConfigManager.getInstance();
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
        BetTaskManager betTaskManager = BetTaskManager.getInstance();

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                BetManager betManager = betTaskManager.getBetManager();

                Set<Bet> bets = new HashSet<>();
                if (betManager != null) {
                    bets = betManager.getBets();
                }

                List<ItemStack> targetHeads = getTargetHeads(bets);

                try {
                    inventory.setItem(4, targetHeads.get(0));
                    inventory.setItem(12, targetHeads.get(1));
                    inventory.setItem(14, targetHeads.get(2));

                    for (int i = 19; i < 25; i++) {
                        inventory.setItem(i, targetHeads.get(i - 19));
                    }
                } catch (Exception ignore) {
                }

            }
        };

        if (Bukkit.getServer().getPluginManager().isPluginEnabled(plugin)) {
            runnable.runTaskTimer(plugin, 0, 20);
        }
    }

    public void loadContent() {
        try {
            inventory = Bukkit.createInventory(
                    null, menuRows * 9,
                    (String) ConfigManager.getInstance().getMessageConfig().getValue("messages.menu.title"));
        } catch (IllegalArgumentException exception) {
            Bukkit.getLogger().severe("Invalid menu row value. Must be between 3 and 6.");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        for (int i = 0; i < menuRows * 9; i++) {
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("§0");
            item.setItemMeta(itemMeta);

            inventory.setItem(i, item);
        }
    }

    public List<ItemStack> getTargetHeads(Set<Bet> bets) {
        List<ItemStack> targetHeads = new ArrayList<>();
        ConfigManager configManager = ConfigManager.getInstance();
        BetManager betManager = BetTaskManager.getInstance().getBetManager();
        MessageConfig messageConfig = configManager.getMessageConfig();

        for (Bet bet : bets) {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            Player player = Bukkit.getPlayer(bet.getPlayer());

            List<String> lore = new ArrayList<>();

            setDisplayName(bet, skullMeta, messageConfig);
            setLore(lore, betManager, player, messageConfig);

            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);

            targetHeads.add(skull);
        }

        return targetHeads;
    }

    private void setDisplayName(Bet bet, SkullMeta skullMeta, MessageConfig messageConfig) {
        Player player = Bukkit.getPlayer(bet.getPlayer());

        String itsFuckingName;

        if (player == null) {
            itsFuckingName = "fake_" +  (int) (Math.random() * 4);
        } else {
            itsFuckingName = player.getName();
        }

        skullMeta.setDisplayName(ParsePlaceholder.parseWithBraces((String) messageConfig.getValue("messages.menu.head.title"),
                new String[]{"PLAYER_NAME", "AMOUNT"},
                new Object[]{itsFuckingName, bet.getCash()
        }));
    }

    private void setLore(List<String> lore, BetManager betManager, Player player, MessageConfig messageConfig) {
        lore.add(ParsePlaceholder.parseWithBraces((String) messageConfig.getValue("messages.menu.head.award"),
                new String[]{"PERCENT", "AWARD"},
                new Object[]{
                        (int) (betManager.calcPercent(player) * 100),
                        (int) betManager.getUserAward(player)
                }));
    }
}
