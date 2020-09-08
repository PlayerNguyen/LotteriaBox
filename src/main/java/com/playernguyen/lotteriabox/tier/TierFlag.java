package com.playernguyen.lotteriabox.tier;

import com.playernguyen.lotteriabox.configuration.Flagable;

import java.util.Arrays;

public enum  TierFlag implements Flagable {

    ID("Id", "default"),
    NAME("Name", "&6Example name"),
    ITEM_LIST("ItemList", Arrays.asList(
            "Bukkit:WOODEN_PICKAXE:1",
            "Weaponist:Gun:ak_47"
    ));


    private final String path;
    private final Object define;

    TierFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }

}
