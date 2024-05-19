package com.megadev.dailybet.object.menu;

import com.megadev.dailybet.DailyBet;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.config.SettingsConfig;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Menu {
    private Inventory inventory;
    @Setter
    private Player player;
    private final UUID uuid;
    private static int menuRows;

    public Menu(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
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

    public void fillHeads(List<MenuItem> targetHeads) {
        try {
            System.out.println(targetHeads);

            this.fillEmpties();

            for (MenuItem targetHead : targetHeads) {
                if (Objects.equals(Bukkit.getPlayer(targetHead.uuid()), player)) {
                    this.inventory.setItem(8, targetHead.itemStack());
                }
            }

            this.inventory.setItem(4, targetHeads.get(0).itemStack());
            this.inventory.setItem(12, targetHeads.get(1).itemStack());
            this.inventory.setItem(14, targetHeads.get(2).itemStack());

            for (int i = 19; i < 26; ++i) {
                this.inventory.setItem(i, targetHeads.get(i - 19).itemStack());
            }

        } catch (IndexOutOfBoundsException ignore ) {
        } catch (Exception var6) {
            Exception exception = var6;
            exception.printStackTrace();
        }

    }

    private void fillEmpties() {
        for(int i = 0; i < menuRows * 9; ++i) {
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("§0");
            item.setItemMeta(itemMeta);
            this.inventory.setItem(i, item);
        }
    }

}
