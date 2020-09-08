package com.playernguyen.lotteriabox.command.setter;

import com.playernguyen.lotteriabox.command.CommandResultEnum;
import com.playernguyen.lotteriabox.command.SubCommandAbstract;
import com.playernguyen.lotteriabox.language.LanguageFlag;
import com.playernguyen.lotteriabox.player.LotteriaBoxPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SubCommandSetter extends SubCommandAbstract {

    public SubCommandSetter() {
        super("setter",
                "",
                "Active the setter mode",
                Arrays.asList("setter", "st"),
                "lotteriabox.command.setter"
        );
    }

    @Override
    public CommandResultEnum onExecute(CommandSender sender, List<String> args) {

        // Check invalid sender
        if (!(sender instanceof Player)) {
            return CommandResultEnum.INVALID_SENDER;
        }
        Player player = (Player) sender;
        LotteriaBoxPlayer boxPlayer = getPlayers().getByPlayer(player);
        // Set chest setter
        if (!boxPlayer.isChestSetter()) {
            // Turn on
            boxPlayer.setChestSetter(true);
            player.sendMessage(
                    getLanguageConfiguration().getLanguagePrefix(LanguageFlag.COMMAND_ANNOUNCE_SETTER_ON)
            );
        } else {
            // Turn off
            boxPlayer.setChestSetter(false);
            player.sendMessage(
                    getLanguageConfiguration().getLanguagePrefix(LanguageFlag.COMMAND_ANNOUNCE_SETTER_OFF)
            );
        }

        return CommandResultEnum.NULL;
    }

    @Override
    public List<String> onTab(CommandSender sender, List<String> args) {
        return null;
    }
}
