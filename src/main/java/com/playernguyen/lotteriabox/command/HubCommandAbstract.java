package com.playernguyen.lotteriabox.command;

import com.playernguyen.lotteriabox.LotteriaBoxImplement;
import com.playernguyen.lotteriabox.language.LanguageFlag;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class HubCommandAbstract extends LotteriaBoxImplement
        implements HubCommand {

    private final String command;
    private final String argument;
    private final String description;

    private final List<String> permissions;
    private final List<String> alias;

    private final SubCommandManager subCommandManager = new SubCommandManager();


    public HubCommandAbstract(String command, String argument, String description, String permission, List<String> alias) {
        this.command = command;
        this.argument = argument;
        this.description = description;
        this.permissions = new ArrayList<>();
        // Permission put
        permissions.add(permission);
        permissions.add("lotteriabox.*");
        this.alias = alias;
    }

    @Override
    public String getPrimaryPermission() {
        return getPermissions().get(0);
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public List<String> getAlias() {
        return alias;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Permission check
        if (!hasPermissions(commandSender)) {
            commandSender.sendMessage(getLanguageConfiguration()
                    .getLanguagePrefix(LanguageFlag.COMMAND_NOT_PERMISSION));
            return true;
        }
        // Loading command
        CommandResultEnum commandResultEnum = onExecute(commandSender, Arrays.asList(strings));
        switch (commandResultEnum) {
            case MISSING_ARGUMENT: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguagePrefix(LanguageFlag.COMMAND_MISSING_ARGUMENTS));
                break;
            }
            case COMMAND_NOT_FOUND: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguagePrefix(LanguageFlag.COMMAND_NOT_FOUND));
                break;
            }
            case NO_PERMISSION: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguagePrefix(LanguageFlag.COMMAND_NOT_PERMISSION));
                break;
            }
            case INVALID_SENDER: {
                commandSender.sendMessage(getLanguageConfiguration()
                        .getLanguagePrefix(LanguageFlag.COMMAND_INVALID_SENDER));
                break;
            }
            case NULL: case NOTHING: {
                break;
            }
        }
        return true;
    }

    public SubCommandManager getSubCommandManager() {
        return subCommandManager;
    }

    public void addSubCommand(SubCommand command) {
        getSubCommandManager().add(command);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return onTab(commandSender, Arrays.asList(strings));
    }

    public boolean hasPermissions(CommandSender sender) {
        for (String permission : getPermissions()) {
            if (sender.hasPermission(permission)) return true;
        }
        return false;
    }
}
