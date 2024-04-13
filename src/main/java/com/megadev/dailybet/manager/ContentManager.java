package com.megadev.dailybet.manager;

import org.bukkit.inventory.ItemStack;
import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.config.MessageConfig;
import com.megadev.dailybet.object.Bet;
import com.megadev.dailybet.object.menu.Head;
import com.megadev.dailybet.utils.parse.ParsePlaceholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContentManager {
    private Set<Bet> bets;

    private ConfigManager configManager;
    private MessageConfig messageConfig;

    private BetManager betManager;


    public ContentManager(Set<Bet> bets) {
        this.bets = bets;

        configManager = ConfigManager.getInstance();
        messageConfig = configManager.getMessageConfig();

        betManager = BetTaskManager.getInstance().getBetManager();
    }

    public List<ItemStack> getTargetHeads() {
        List<ItemStack> targetHeads = new ArrayList<>();

        for (Bet bet : bets) {
            fillHeadContent(bet, targetHeads);
        }

        return targetHeads;
    }

    private void fillHeadContent(Bet bet, List<ItemStack> targetHeads) {
        Head head = new Head();
        setTitle(head, bet);
        setLore(head, bet);

        targetHeads.add(head.getItemStack());
    }

    private void setTitle(Head head, Bet bet) {
        String messageConfigStr = messageConfig.getString("messages.menu.head.title");

        String title = ParsePlaceholder.parseWithBraces(messageConfigStr,
                new String[]{"PLAYER_NAME", "AMOUNT"},
                new Object[]{bet.getPlayerName(), bet.getCash()});
        head.setTitle(title);
    }

    private void setLore(Head head, Bet bet) {
        List<String> lore = new ArrayList<>();

        String messageCfgStr = messageConfig.getString("messages.menu.head.lore");
        int userPercent = getBetPercent(bet);
        int userAward = getBetAward(bet);

        lore.add(ParsePlaceholder.parseWithBraces(messageCfgStr,
                new String[]{"PERCENT", "AWARD"},
                new Object[]{ userPercent, userAward }));

        head.setLore(lore);
    }

    private int getBetPercent(Bet bet) {
        return (int) (betManager.calcPercent(bet, bets) * 100);
    }

    private int getBetAward(Bet bet) {
        return (int) betManager.getUserAward(bet, bets);
    }
}
