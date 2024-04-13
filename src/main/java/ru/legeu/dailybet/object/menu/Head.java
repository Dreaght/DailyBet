package ru.legeu.dailybet.object.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

@Getter @Setter
public class Head {
    private String title;
    private List<String> lore;

    public Head(String title, List<String> lore) {
        this.title = title;
        this.lore = lore;
    }

    public Head() {
    }

    public ItemStack getItemStack() {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setLore(getLore());
        skullMeta.setDisplayName(getTitle());

        skull.setItemMeta(skullMeta);

        return skull;
    }
}
