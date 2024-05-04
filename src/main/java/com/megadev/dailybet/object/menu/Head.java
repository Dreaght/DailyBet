package com.megadev.dailybet.object.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

@Getter @Setter
public class Head {
    private OfflinePlayer player;
    private String title;
    private List<String> lore;

    public Head(OfflinePlayer player, String title, List<String> lore) {
        this.player = player;
        this.title = title;
        this.lore = lore;
    }

    public Head(OfflinePlayer player) {
        this.player = player;
    }

    public ItemStack getItemStack() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setLore(this.getLore());
        skullMeta.setDisplayName(this.getTitle());
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public String getTitle() {
        return this.title;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }
}
