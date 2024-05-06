package com.megadev.dailybet.object.menu;

import com.megadev.dailybet.DailyBet;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.config.SettingsConfig;
import com.megadev.dailybet.object.Bet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
    private Inventory inventory;
    private final Player player;
    private static int menuRows;

    public Menu(Player player) {
        this.player = player;
        ConfigManager configManager = ConfigManager.getInstance();
        menuRows = (Integer)configManager.getConfig(SettingsConfig.class).getValue("menu-rows");
    }

    public void loadContent() {
        try {
            this.inventory = Bukkit.createInventory(this.player, menuRows * 9,
                    (String)ConfigManager.getInstance().getConfig(MessageConfig.class).getValue("menu.title"));
        } catch (IllegalArgumentException var2) {
            Bukkit.getLogger().severe("Invalid menu row value. Must be between 3 and 6.");
            Bukkit.getServer().getPluginManager().disablePlugin(DailyBet.getInstance());
            return;
        }

        this.fillEmpties();
    }

    public void fillHeads(List<ItemStack> targetHeads, Set<Bet> bets) {
        try {
            this.fillEmpties();
            int indexCurrentPlayer = this.getCurrentPlayerHeadIndex(bets);
            if (indexCurrentPlayer >= -1) {
                this.inventory.setItem(8, (ItemStack)targetHeads.get(indexCurrentPlayer));
            }

            this.inventory.setItem(4, (ItemStack)targetHeads.get(0));
            this.inventory.setItem(12, (ItemStack)targetHeads.get(1));
            this.inventory.setItem(14, (ItemStack)targetHeads.get(2));

            for(int i = 19; i < 26; ++i) {
                this.inventory.setItem(i, (ItemStack)targetHeads.get(i - 19));
            }
        } catch (IndexOutOfBoundsException var5) {
        } catch (Exception var6) {
            Exception exception = var6;
            exception.printStackTrace();
        }

    }

    private int getCurrentPlayerHeadIndex(Set<Bet> bets) {
        int index = 0;

        for(Iterator var3 = bets.iterator(); var3.hasNext(); ++index) {
            Bet bet = (Bet)var3.next();
            if (bet.getPlayer().equals(this.player)) {
                return index;
            }
        }

        return -1;
    }

    private void fillEmpties() {
        for(int i = 0; i < menuRows * 9; ++i) {
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("ยง0");
            item.setItemMeta(itemMeta);
            this.inventory.setItem(i, item);
        }

    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }
}
