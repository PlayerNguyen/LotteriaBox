package com.playernguyen.lotteriabox.command;

import com.playernguyen.lotteriabox.manager.ManagerSet;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

import java.util.HashSet;

public class HubCommandManager extends ManagerSet<HubCommand> {

    public HubCommandManager() {
        super(new HashSet<>());
    }

    @Override
    public void add(HubCommand item) {
        super.add(item);

        PluginCommand pluginCommand = Bukkit.getPluginCommand(item.getCommand());
        if (pluginCommand == null) {
            throw new NullPointerException(
                    String.format("Not found command %s on server. Please trying to config the plugin.yml file", item.getCommand())
            );
        }
        // Set executor
        pluginCommand.setExecutor(item);
    }
}
