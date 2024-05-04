package com.megadev.dailybet.manager;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.object.menu.Head;
import com.megadev.dailybet.util.parse.ParsePlaceholder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContentManager {
    private final Set<Bet> bets;
    private final MessageConfig messageConfig;
    private final BetManager betManager;

    public ContentManager(Set<Bet> bets) {
        this.bets = bets;
        this.messageConfig = ConfigManager.getInstance().getMessageConfig();
        this.betManager = BetTaskManager.getInstance().getBetManager();
    }

    public List<ItemStack> getTargetHeads() {

        List<ItemStack> targetHeads = new ArrayList<>();

        while(var2.hasNext()) {
            Bet bet = (Bet)var2.next();
            this.fillHeadContent(bet, targetHeads, bet.getPlayer());
        }

        return targetHeads;
    }

    private void fillHeadContent(Bet bet, List<ItemStack> targetHeads, OfflinePlayer player) {
        Head head = new Head(player);
        this.setTitle(head, bet);
        this.setLore(head, bet);
        targetHeads.add(head.getItemStack());
    }

    private void setTitle(Head head, Bet bet) {
        String messageConfigStr = this.messageConfig.getString("menu.head.title");
        String title = ParsePlaceholder.parseWithBraces(messageConfigStr, new String[]{"PLAYER_NAME", "AMOUNT"}, new Object[]{bet.getPlayerName(), bet.getCash()});
        head.setTitle(title);
    }

    private void setLore(Head head, Bet bet) {
        List<String> lore = new ArrayList();
        String messageCfgStr = this.messageConfig.getString("menu.head.lore");
        int userPercent = this.getBetPercent(bet);
        int userAward = this.getBetAward(bet);
        lore.add(ParsePlaceholder.parseWithBraces(messageCfgStr, new String[]{"PERCENT", "AWARD"}, new Object[]{userPercent, userAward}));
        head.setLore(lore);
    }

    private int getBetPercent(Bet bet) {
        return (int)(this.betManager.calcPercent(bet, this.bets) * 100.0);
    }

    private int getBetAward(Bet bet) {
        return (int)this.betManager.getUserAward(bet, this.bets);
    }
}
