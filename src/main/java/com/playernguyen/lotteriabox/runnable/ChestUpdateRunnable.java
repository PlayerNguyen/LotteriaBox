package com.playernguyen.lotteriabox.runnable;

import com.playernguyen.lotteriabox.LotteriaBox;
import com.playernguyen.lotteriabox.language.LanguageFlag;
import com.playernguyen.lotteriabox.setting.SettingFlag;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ChestUpdateRunnable extends BukkitRunnable {

    private int counter;

    public ChestUpdateRunnable() {
        this.counter = getLotteriaBox().getSettings().getInt(SettingFlag.GENERAL_CHEST_UPDATE_PERIOD);
    }

    LotteriaBox getLotteriaBox(){
        return LotteriaBox.getInstance();
    }

    @Override
    public void run() {
        counter --;

        if (counter <= 3) {
            if (getLotteriaBox().getSettings().getBoolean(SettingFlag.GENERAL_BROADCAST_TO_PLAYER)) {
                Bukkit.getOnlinePlayers().forEach(e->e.sendMessage(
                        String.format(
                                getLotteriaBox()
                                        .getLanguageConfiguration()
                                        .getLanguagePrefix(LanguageFlag.GENERAL_BROADCASTING_REFRESH_IN),
                                counter
                        )
                ));
            }
        }

        if (counter <= 0) {
            // Broadcasting
            if (getLotteriaBox().getSettings().getBoolean(SettingFlag.GENERAL_BROADCAST_TO_PLAYER)) {
                Bukkit.getOnlinePlayers().forEach(e->e.sendMessage(getLotteriaBox()
                        .getLanguageConfiguration()
                        .getLanguagePrefix(LanguageFlag.GENERAL_BROADCASTING))
                );
            }
            // Refresh
            getLotteriaBox().getChests().refresh();
            counter = getLotteriaBox().getSettings().getInt(SettingFlag.GENERAL_CHEST_UPDATE_PERIOD);
        }
    }
}
