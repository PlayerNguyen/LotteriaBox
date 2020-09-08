package com.playernguyen.lotteriabox.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LotteriaCommand extends HubCommandAbstract {


    public LotteriaCommand() {
        super("lotteriabox", "<command>", "Command of LotteriaBox plugin",
                "lotteriabox.command", null);
        // Add sub command here

    }

    @Override
    public CommandResultEnum onExecute(CommandSender sender, List<String> args) {
        // Arguments
        if (args.size() <= 0) {
            // Send help
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&6/%s &5%s&7: %s",
                            getCommand(), getArgument(), getDescription())
            ));
            // Send arguments
            getSubCommandList().forEach(e -> {
                // If has permission, send the command
                if (e.hasPermissions(sender)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            String.format("&7[+]---&c%s %s&7:%s",
                                    e.getCommand(), e.getArgument(), e.getDescription())
                    ));
                }
            });
            return CommandResultEnum.MISSING_ARGUMENT;
        }
        // Get command
        Command command =
                getSubCommandList()
                        .stream()
                        .filter(e -> e.getCommand().equalsIgnoreCase(args.get(0)))
                        .findAny()
                        .orElse(null);
        if (command == null) {
            return CommandResultEnum.COMMAND_NOT_FOUND;
        }
        if (!command.hasPermissions(sender)) {
            return CommandResultEnum.COMMAND_NOT_FOUND;
        }
        return command.onExecute(sender, args.subList(1, args.size()));
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            List<Command> collect = getSubCommandList()
                    .stream()
                    .filter(e -> e.hasPermissions(sender))
                    .collect(Collectors.toList());
            return collect.stream().map(Command::getCommand).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
