package com.playernguyen.lotteriabox.command;

import com.playernguyen.lotteriabox.LotteriaBoxImplement;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommandAbstract extends LotteriaBoxImplement implements SubCommand {

    private final String command;
    private final String argument;
    private final String description;

    private final List<String> alias;
    private final List<String> permissions;

    public SubCommandAbstract(String command,
                              String argument,
                              String description,
                              List<String> alias,
                              String primaryPermission) {
        this.command = command;
        this.argument = argument;
        this.description = description;
        this.alias = alias;
        // Permissions setter
        this.permissions = new ArrayList<>();
        permissions.add(primaryPermission);
        permissions.add("lotteriabox.*");
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getArgument() {
        return argument;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getAlias() {
        return alias;
    }

    @Override
    public String getPrimaryPermission() {
        return permissions.get(0);
    }

    @Override
    public boolean hasPermissions(CommandSender sender) {
        for (String permission : getPermissions()) {
            if (sender.hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toSlashes() {
        StringBuilder builder = new StringBuilder();
        for (String s : getAlias()) {
            builder.append(s);
            if (getAlias().indexOf(s) < getAlias().size() - 1) {
                builder.append("/");
            }
        }
        return builder.toString() ;
    }
}
