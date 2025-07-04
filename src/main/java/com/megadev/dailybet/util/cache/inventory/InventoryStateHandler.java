package com.megadev.dailybet.util.cache.inventory;

import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import com.megadev.dailybet.config.ConfigManager;

import java.io.File;
import java.io.IOException;

public class InventoryStateHandler {
    private static final String FILE_NAME = "inventory_state.base64";

    public static void saveInventory(Inventory inventory, Plugin plugin) {
        try {
            String base64 = InventorySerializerUtil.toBase64(inventory);
            File cacheFolder = new File(plugin.getDataFolder(), "cache");
            if (!cacheFolder.exists()) {
                cacheFolder.mkdirs();
            }
            File filePath = new File(cacheFolder, FILE_NAME);
            FileManager.saveStringToFile(base64, filePath.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventory loadInventoryAndDeleteFile(Plugin plugin) {
        try {
            File cacheFolder = new File(plugin.getDataFolder(), "cache");
            File filePath = new File(cacheFolder, FILE_NAME);
            if (!filePath.exists()) {
                return null;
            }
            String base64 = FileManager.loadStringFromFile(filePath.getPath());
            ItemStack[] contents = InventorySerializerUtil.fromBase64(base64).getContents();
            FileManager.deleteFile(filePath.getPath());
            if (cacheFolder.isDirectory() && cacheFolder.list().length == 0) {
                cacheFolder.delete();
            }
            return getInventory(contents);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Inventory getInventory(ItemStack[] items) {
        if (items == null || items.length == 0) {
            return null;
        }

        int size = items.length;

        String title = ConfigManager.getInstance().getConfig(MessageConfig.class).getString("messages.menu.title");
        if (title == null) {
            return null;
        }

        Inventory inventory = Bukkit.createInventory(
                null, size, title);
        inventory.setContents(items);
        return inventory;
    }
}
