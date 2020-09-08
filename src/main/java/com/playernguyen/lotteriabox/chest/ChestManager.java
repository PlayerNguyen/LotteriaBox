package com.playernguyen.lotteriabox.chest;

import com.playernguyen.lotteriabox.manager.ManagerSet;
import org.bukkit.Location;

import java.util.HashSet;

public class ChestManager extends ManagerSet<ChestObject> {

    public ChestManager() {
        super(new HashSet<>());
    }

    public void saveAll() {
        for (ChestObject chestObject : getContainer()) {
            new ChestConfiguration(chestObject).saveChest(chestObject);
        }
    }

    public ChestObject getChestFromLocation(Location location) {
        return getContainer()
                .stream()
                .filter(e -> e.getLocation().equals(location))
                .findAny()
                .orElse(null);
    }

    public void refresh() {
        for (ChestObject chestObject : getContainer()) {
            chestObject.refresh();
        }
    }
}
