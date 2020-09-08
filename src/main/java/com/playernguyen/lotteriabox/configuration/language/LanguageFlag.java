package com.playernguyen.lotteriabox.configuration.language;

import com.playernguyen.lotteriabox.configuration.Flagable;

public enum  LanguageFlag  implements Flagable {

    PREFIX("Prefix", "&7[&cLotteriaBox&7]"),

    COMMAND_NOT_PERMISSION("Command.NotPermission", "&cNot allow to access this command"),
    COMMAND_MISSING_ARGUMENTS("Command.MissingArguments", "&cArgument are missing!"),
    COMMAND_NOT_FOUND("Command.CommandNotFound", "&cCommand not found"),

    ;

    private final String path;
    private final Object define;

    LanguageFlag(String path, Object define) {
        this.path = path;
        this.define = define;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefine() {
        return define;
    }
}
