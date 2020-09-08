package com.playernguyen.lotteriabox.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Command {

    String getCommand();

    String getArgument();

    String getDescription();

    String[] getAlias();

    String getPrimaryPermission();

    boolean hasPermissions(CommandSender sender);

    List<String> getPermissions();

    CommandResultEnum onExecute(CommandSender sender, List<String> args);

    List<String> onTab(CommandSender sender, List<String> args);

}
