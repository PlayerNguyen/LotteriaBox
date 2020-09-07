package com.playernguyen.lotteriabox.object;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ChestObject {

    Location getLocation();

    void setLocation(Location location);

    void rotation();

    List<ItemStack> getRandomItemList();

}
