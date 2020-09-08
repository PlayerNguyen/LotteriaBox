package com.playernguyen.lotteriabox.command.tierlist;

import com.playernguyen.lotteriabox.command.CommandResultEnum;
import com.playernguyen.lotteriabox.command.SubCommandAbstract;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class SubCommandTierList extends SubCommandAbstract {


    public SubCommandTierList() {
        super("tiers",
                "",
                "Show all tiers",
                Arrays.asList("ts", "tiers"),
                "lotteriabox.command.tiers"
        );
    }

    @Override
    public CommandResultEnum onExecute(CommandSender sender, List<String> args) {
        sender.sendMessage(ChatColor.DARK_GRAY + "---------------------");
        getTiers().forEach(e -> sender.sendMessage(ChatColor.GOLD + " - " +e.getId()));
        sender.sendMessage(ChatColor.DARK_GRAY + "---------------------");
        return CommandResultEnum.NULL;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> args) {
        return null;
    }
}
