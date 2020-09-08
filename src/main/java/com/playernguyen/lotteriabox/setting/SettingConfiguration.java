package com.playernguyen.lotteriabox.setting;

import com.playernguyen.lotteriabox.configuration.Yamlist;
import org.bukkit.plugin.Plugin;

public class SettingConfiguration extends Yamlist<SettingFlag> {

    public SettingConfiguration(Plugin plugin) {
        super(plugin, "config.yml", SettingFlag.values());
    }
}
