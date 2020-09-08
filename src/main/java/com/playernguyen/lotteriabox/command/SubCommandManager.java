package com.playernguyen.lotteriabox.command;

import com.playernguyen.lotteriabox.manager.ManagerSet;

import java.util.HashSet;

public class SubCommandManager extends ManagerSet<SubCommand> {

    public SubCommandManager() {
        super(new HashSet<>());
    }

    public SubCommand getSubCommandFromAlias(String alias) {
        for (SubCommand subCommand : getContainer()) {
            for (String subCommandAlias : subCommand.getAlias()) {
                if (subCommandAlias.equalsIgnoreCase(alias)) return subCommand;
            }
        }
        return null;
    }

    public SubCommand findCommand(String query) {
        for (SubCommand subCommand : getContainer()) {
            if (subCommand.getCommand().equalsIgnoreCase(query)) {
                return subCommand;
            } else {
                for (String alias : subCommand.getAlias()) {
                    if (alias.equalsIgnoreCase(query)) return subCommand;
                }
            }
        }
        return null;
    }

}
