package com.playernguyen.lotteriabox.chest;

import com.playernguyen.lotteriabox.LotteriaBox;
import com.playernguyen.lotteriabox.configuration.Yamlist;
import com.playernguyen.lotteriabox.location.LocationSerialize;
import com.playernguyen.lotteriabox.tier.TierObject;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class ChestConfiguration extends Yamlist<ChestFlag> {

    public static final String CHEST_PARENT_NAME = "/chest-object";
    private final UUID uniqueId;

    public ChestConfiguration(Plugin plugin, String name) {
        super(plugin, name, ChestFlag.values(), true, CHEST_PARENT_NAME);
        this.uniqueId = UUID.fromString(name.replace(".yml", ""));
    }


    public ChestConfiguration(ChestObject chestObject) {
        super(LotteriaBox.getInstance(), chestObject.getUniqueId().toString()+".yml",
                ChestFlag.values(),
                true,
                CHEST_PARENT_NAME);
        this.uniqueId = chestObject.getUniqueId();
    }

    public ChestObject toChestObject() {
        Location location = LocationSerialize.deserialize(getString(ChestFlag.LOCATION));
        TierObject tierObject = getTiers().getById(getString(ChestFlag.TIER));
        if (tierObject == null) {
            throw new NullPointerException(String.format("Tier not found %s", getString(ChestFlag.TIER)));
        }
        return new ChestObject(uniqueId, location, tierObject);
    }
    public void saveChest(ChestObject object) {
        this.fileConfiguration.set(ChestFlag.LOCATION.getPath(), LocationSerialize.serialize(object.getLocation()));
        this.fileConfiguration.set(ChestFlag.TIER.getPath(), object.getTier().getId());

        this.save();
    }

}
