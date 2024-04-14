package com.megadev.dailybet.object.economy;

import com.megadev.dailybet.config.ConfigManager;
import com.megadev.dailybet.object.economy.adapter.EconomyAdapter;
import lombok.Getter;

@Getter
public enum EconomyType {
    ECONOMY_FROM(EconomyInstaller.setEconomyFrom(
            ConfigManager.getInstance().getSettingsConfig().getString("economy-from"))),
    ECONOMY_TO(EconomyInstaller.setEconomyTo(
            ConfigManager.getInstance().getSettingsConfig().getString("economy-to")));

    private final EconomyAdapter adapter;

    EconomyType(EconomyAdapter adapter) {
        this.adapter = adapter;
    }
}
