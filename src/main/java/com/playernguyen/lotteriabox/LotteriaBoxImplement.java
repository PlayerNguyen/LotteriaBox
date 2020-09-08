package com.playernguyen.lotteriabox;

import com.playernguyen.lotteriabox.configuration.language.LanguageConfiguration;
import com.playernguyen.lotteriabox.player.PlayerManager;

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

}
