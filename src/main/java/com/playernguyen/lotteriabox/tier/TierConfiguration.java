package com.playernguyen.lotteriabox.tier;

import com.playernguyen.lotteriabox.configuration.Yamlist;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class TierConfiguration extends Yamlist<TierFlag> {

    private static final String TIER_FOLDER = "tier";

    public TierConfiguration(Plugin plugin, String fileName) {
        super(plugin, fileName, TierFlag.values(), true, TIER_FOLDER);
    }


    public static File getTierFolder(Plugin plugin) {
        return new File(plugin.getDataFolder(), TIER_FOLDER);
    }

}
