package ru.legeu.dailybet.manager;

import org.bukkit.inventory.ItemStack;
import ru.legeu.dailybet.config.ConfigManager;
import ru.legeu.dailybet.config.MessageConfig;
import ru.legeu.dailybet.object.Bet;
import ru.legeu.dailybet.object.menu.Head;
import ru.legeu.dailybet.utils.parse.ParsePlaceholder;

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
        String messageConfigStr = (String) messageConfig.getValue("messages.menu.head.title");

        String title = ParsePlaceholder.parseWithBraces(messageConfigStr,
                new String[]{"PLAYER_NAME", "AMOUNT"},
                new Object[]{bet.getPlayerName(), bet.getCash()});
        head.setTitle(title);
    }

    private void setLore(Head head, Bet bet) {
        List<String> lore = new ArrayList<>();

        String messageCfgStr = (String) messageConfig.getValue("messages.menu.head.lore");
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
