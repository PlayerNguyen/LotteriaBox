package com.playernguyen.lotteriabox.language;

import com.playernguyen.lotteriabox.configuration.Flagable;

public enum  LanguageFlag  implements Flagable {

    PREFIX("Prefix", "&7[&cLotteriaBox&7]"),

    COMMAND_NOT_PERMISSION("Command.NotPermission", "&cNot allow to access this command"),
    COMMAND_MISSING_ARGUMENTS("Command.MissingArguments", "&cArgument are missing!"),
    COMMAND_NOT_FOUND("Command.CommandNotFound", "&cCommand not found"),
    COMMAND_INVALID_SENDER("Command.InvalidSender", "&cYou are not a target for this command!"),

    COMMAND_ANNOUNCE_SETTER_ON("Command.Announce.Setter.On", "&aSetter are on, just click the chest block to run wizard mode!"),
    COMMAND_ANNOUNCE_SETTER_OFF("Command.Announce.Setter.Off", "&cSetter are off!"),

    EVENT_MODIFY_WHILE_SETTER_ON("Event.ModifyWhileSetter", "&cYou must turn off the setter mode to modify block!"),

    ANNOUNCEMENT_CREATE_CHEST_SUCCESS("Announcement.CreateChestSuccess", "&aSuccess create the chest with tier %s"),
    ANNOUNCEMENT_CANCEL_CHEST_SET("Announcement.CancelSetChest", "&cYou are cancelled the setter of chest"),

    GUI_TIER_NOT_FOUND("GUI.TierNotFound", "Tier not found"),
    GUI_TIER_SET_PLACEHOLDER("GUI.TierSetPlaceholder", "Your tier...(%s)"),

    GENERAL_BROADCASTING("General.Broadcasting", "&cRefreshing the chest..."),
    GENERAL_BROADCASTING_REFRESH_IN("General.BroadcastingRefreshIn", "&7Refreshing the chest in &5%s..."),

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
