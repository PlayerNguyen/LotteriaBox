package com.playernguyen.lotteriabox;

import com.playernguyen.lotteriabox.chest.ChestManager;
import com.playernguyen.lotteriabox.language.LanguageConfiguration;
import com.playernguyen.lotteriabox.player.PlayerManager;
import com.playernguyen.lotteriabox.setting.SettingConfiguration;
import com.playernguyen.lotteriabox.tier.TierManager;

public abstract class LotteriaBoxImplement {


    protected LotteriaBox getLotteriaBox() {
        return LotteriaBox.getInstance();
    }

    protected PlayerManager getPlayers() {
        return getLotteriaBox().getPlayers();
    }

    protected LanguageConfiguration getLanguageConfiguration() {
        return getLotteriaBox().getLanguageConfiguration();
    }

    protected TierManager getTiers() {
        return getLotteriaBox().getTiers();
    }

    protected ChestManager getChests () {
        return getLotteriaBox().getChests();
    }

    protected SettingConfiguration getSettings() {
        return getLotteriaBox().getSettings();
    }
}
