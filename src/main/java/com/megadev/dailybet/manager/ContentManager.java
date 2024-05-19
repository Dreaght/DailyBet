package com.megadev.dailybet.manager;

import com.megadev.dailybet.object.menu.MenuItem;
import org.bukkit.OfflinePlayer;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.object.menu.Head;
import com.megadev.dailybet.util.parse.ParsePlaceholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ContentManager {
    private final Set<Bet> bets;
    private final MessageConfig messageConfig;
    private final BetManager betManager;

    public ContentManager(Set<Bet> bets) {
        this.bets = bets;
        this.messageConfig = ConfigManager.getInstance().getConfig(MessageConfig.class);
        this.betManager = BetTaskManager.getInstance().getBetManager();
    }

    public List<MenuItem> getTargetHeads() {

        List<MenuItem> targetHeads = new ArrayList<>();

        int index = 1;
        for (Bet bet : bets) {
            fillHeadContent(bet, targetHeads, bet.getUuid(), index++);
        }

        return targetHeads;
    }

    public void fillHeadContent(Bet bet, List<MenuItem> targetHeads, UUID uuid, int index) {
        Head head = new Head(uuid);
        this.setTitle(head, bet, index);
        this.setLore(head, bet);
        MenuItem menuItem = new MenuItem(head.getItemStack(), uuid);
        targetHeads.add(menuItem);
    }

    private void setTitle(Head head, Bet bet, int index) {
        String messageConfigStr = this.messageConfig.getString("menu.head.title");
        String title = ParsePlaceholder.parseWithBraces(messageConfigStr, new String[]{"PLAYER_NAME", "AMOUNT", "NUMBER"}, new Object[]{bet.getPlayerName(), bet.getCash(), index});
        head.setTitle(title);
    }

    private void setLore(Head head, Bet bet) {
        List<String> lore = new ArrayList<>();
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
