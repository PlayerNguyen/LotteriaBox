package com.playernguyen.lotteriabox.chest;

import com.playernguyen.lotteriabox.configuration.Flagable;

public enum ChestFlag implements Flagable {

    LOCATION("Location", "world:0:0:0:0:0"),
    TIER("Tier", "default")
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

    ChestFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }
}
