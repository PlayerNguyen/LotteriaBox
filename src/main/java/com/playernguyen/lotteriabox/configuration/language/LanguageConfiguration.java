package com.playernguyen.lotteriabox.configuration.language;

import com.playernguyen.lotteriabox.configuration.Yamlist;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class LanguageConfiguration extends Yamlist<LanguageFlag> {

    public LanguageConfiguration(Plugin plugin) {
        super(plugin, "language.yml", LanguageFlag.values(), true);
    }

    public String getLanguage(LanguageFlag flag) {
        return ChatColor.translateAlternateColorCodes('&', getString(flag));
    }

    public String getLanguagePrefix(LanguageFlag flag) {
        return getLanguage(LanguageFlag.PREFIX)
                .concat(" ")
                .concat(getLanguage(flag));
    }

}
