package com.playernguyen.lotteriabox.setting;

import com.playernguyen.lotteriabox.configuration.Flagable;

public enum  SettingFlag implements Flagable {

    GENERAL_CHEST_UPDATE_PERIOD("General.ChestUpdatePeriod", 120),

    GENERAL_BROADCAST_TO_PLAYER("General.BroadcastToPlayer", true)
    ;

   private final String path;
   private final Object define;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }

    SettingFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }
}
