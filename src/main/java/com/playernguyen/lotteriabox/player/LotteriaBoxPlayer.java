package com.playernguyen.lotteriabox.player;

import org.bukkit.entity.Player;

import java.util.UUID;

public class LotteriaBoxPlayer {

    private final UUID uniqueId;
    private boolean chestSetter;
    public LotteriaBoxPlayer(Player player) {
        this.uniqueId = player.getUniqueId();
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public boolean isChestSetter() {
        return chestSetter;
    }

    public void setChestSetter(boolean chestSetter) {
        this.chestSetter = chestSetter;
    }
}
