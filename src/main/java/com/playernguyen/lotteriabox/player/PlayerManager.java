package com.playernguyen.lotteriabox.player;

import com.playernguyen.lotteriabox.manager.ManagerSet;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class PlayerManager extends ManagerSet<LotteriaBoxPlayer> {

    public PlayerManager() {
        super(new HashSet<>());
    }

    public LotteriaBoxPlayer createNewPlayer(Player player) {
        LotteriaBoxPlayer lotteriaBoxPlayer = new LotteriaBoxPlayer(player);
        // Add to the box
        this.add(lotteriaBoxPlayer);
        // Return the player
        return lotteriaBoxPlayer;
    }

    public LotteriaBoxPlayer getByPlayer(Player player) {
        return getContainer()
                .stream()
                .filter(e -> e.getUniqueId().equals(player.getUniqueId()))
                .findAny()
                .orElse(null);
    }

    public boolean containPlayer(Player player) {
        return getContainer()
                .stream()
                .anyMatch(e -> e.getUniqueId().equals(player.getUniqueId()));
    }

}
