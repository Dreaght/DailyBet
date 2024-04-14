package com.megadev.dailybet.object.economy;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.economy.adapter.EconomyAdapter;

public enum EconomyType {
    ECONOMY_FROM(EconomyInstaller.setEconomyFrom(
            ConfigManager.getInstance().getSettingsConfig().getString("economy-from"))),
    ECONOMY_TO(EconomyInstaller.setEconomyTo(
            ConfigManager.getInstance().getSettingsConfig().getString("economy-to")));

    final EconomyAdapter adapter;

    EconomyType(EconomyAdapter adapter) {
        this.adapter = adapter;
    }
}
