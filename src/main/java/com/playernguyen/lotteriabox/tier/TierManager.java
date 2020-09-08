package com.playernguyen.lotteriabox.tier;

import com.playernguyen.lotteriabox.manager.ManagerSet;

import java.util.HashSet;


public class TierManager extends ManagerSet<TierObject> {

    public TierManager() {
        super(new HashSet<>());
    }

    @Override
    public void add(TierObject item) {
        if (getById(item.getId()) != null) {
            throw new IllegalStateException(String.format("The tier id %s has existed", item.getId()));
        }
        super.add(item);
    }

    public TierObject getById(String id) {
        return getContainer().stream().filter(e -> e.getId().equalsIgnoreCase(id)).findAny().orElse(null);
    }
}
