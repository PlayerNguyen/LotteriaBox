package com.playernguyen.lotteriabox.listener;

import com.playernguyen.lotteriabox.player.LotteriaBoxPlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class LotteriaBoxPlayerListener extends LotteriaBoxListener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Without player
        if (!getPlayers().containPlayer(player)) {
            getPlayers().createNewPlayer(player);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        LotteriaBoxPlayer byPlayer = getPlayers().getByPlayer(player);

        // Remove player if player quit itself
        if (!Objects.isNull(byPlayer)) {
            getPlayers().remove(byPlayer);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        LotteriaBoxPlayer byPlayer = getPlayers().getByPlayer(player);
        Block block = event.getClickedBlock();

        // Set the block
        if (!Objects.isNull(block)
            && byPlayer.isChestSetter()) {

        }
    }

}
