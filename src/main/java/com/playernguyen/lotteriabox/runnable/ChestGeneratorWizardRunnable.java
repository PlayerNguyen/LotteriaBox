package com.playernguyen.lotteriabox.runnable;

import com.playernguyen.lotteriabox.LotteriaBox;
import com.playernguyen.lotteriabox.player.LotteriaBoxPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ChestGeneratorWizardRunnable extends BukkitRunnable {

    private final Player player;
    private final LotteriaBox lotteriaBox;


    public ChestGeneratorWizardRunnable(LotteriaBox lotteriaBox,
                                        Player player,
                                        Location location) {
        this.lotteriaBox = lotteriaBox;
        this.player = player;
    }

    public LotteriaBox getLotteriaBox() {
        return lotteriaBox;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {
        LotteriaBoxPlayer lotteriaBoxPlayer =
                getLotteriaBox().getPlayers().getByPlayer(getPlayer());
        // Player not found
        if (Objects.isNull(lotteriaBoxPlayer)) {
            getLotteriaBox().getLogger().info(String.format(
                    "Wizard: not found %s on storage data! Close Runnable...",
                    getPlayer().getName()
            ));
            cancel();
            return;
        }

        //if (lotteriaBoxPlayer.)
    }
}
