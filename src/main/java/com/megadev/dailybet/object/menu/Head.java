package com.megadev.dailybet.object.menu;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

@Setter @Getter
public class Head {
    private UUID uuid;
    private String title;
    private List<String> lore;

    public Head(UUID uuid, String title, List<String> lore) {
        this.uuid = uuid;
        this.title = title;
        this.lore = lore;
    }

    public Head(UUID uuid) {
        this.uuid = uuid;
    }

    public ItemStack getItemStack() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setLore(this.getLore());
        skullMeta.setDisplayName(this.getTitle());
        skull.setItemMeta(skullMeta);
        return skull;
    }

}
