package com.playernguyen.lotteriabox.listener;

import com.playernguyen.lotteriabox.chest.ChestObject;
import com.playernguyen.lotteriabox.language.LanguageFlag;
import com.playernguyen.lotteriabox.player.LotteriaBoxPlayer;
import com.playernguyen.lotteriabox.tier.TierObject;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

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
            && byPlayer.isChestSetter()
            && block.getType() == Material.CHEST) {
            ChestObject chestPoint = getChests().getChestFromLocation(block.getLocation());
            if (chestPoint != null) {
                // Modify the current chest
                new AnvilGUI.Builder()
                        .onComplete((player1, text) -> {
                            TierObject tierObject = getLotteriaBox().getTiers().getById(text);
                            if (tierObject == null) {
                                return AnvilGUI.Response
                                        .text(getLanguageConfiguration()
                                                .getLanguage(LanguageFlag.GUI_TIER_NOT_FOUND));
                            }
                            // Reset the chest object
                            chestPoint.setTier(tierObject);
                            player1.sendMessage(String.format(
                                    getLanguageConfiguration()
                                            .getLanguagePrefix(LanguageFlag.ANNOUNCEMENT_CREATE_CHEST_SUCCESS),
                                    tierObject.getDisplay()
                            ));
                            return AnvilGUI.Response.close();
                        })
                        .onClose((player1) -> {
                            player1.sendMessage(getLanguageConfiguration()
                                    .getLanguagePrefix(LanguageFlag.ANNOUNCEMENT_CANCEL_CHEST_SET));
                        })
                        .title("Tier selector")
                        .text(chestPoint.getTier().getId())
                        .item(new ItemStack(Material.CHEST, 1))
                        .plugin(getLotteriaBox())
                        .open(player);
            } else {
                // Create new one
                new AnvilGUI.Builder()
                        .onComplete((player1, text) -> {
                            TierObject tierObject = getLotteriaBox().getTiers().getById(text);
                            if (tierObject == null) {
                                return AnvilGUI.Response.text(
                                        getLanguageConfiguration().getLanguage(LanguageFlag.GUI_TIER_NOT_FOUND)
                                );
                            }
                            // Build the chest object

                            ChestObject chestObject = new ChestObject(
                                    UUID.randomUUID(),
                                    block.getLocation(),
                                    tierObject
                            );
                            getChests().add(chestObject);
                            getChests().saveAll();
                            player.sendMessage(String.format(
                                    getLanguageConfiguration()
                                            .getLanguagePrefix(LanguageFlag.ANNOUNCEMENT_CREATE_CHEST_SUCCESS),
                                    tierObject.getDisplay()
                            ));
                            return AnvilGUI.Response.close();
                        })
                        .title("Tier selector")
                        .text(String.format(
                                getLanguageConfiguration().getLanguage(LanguageFlag.GUI_TIER_SET_PLACEHOLDER),
                                "default"
                        ))
                        .preventClose()
                        .item(new ItemStack(Material.CHEST, 1))
                        .plugin(getLotteriaBox())
                        .open(player);
            }


            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        LotteriaBoxPlayer byPlayer = getPlayers().getByPlayer(player);

        if (byPlayer.isChestSetter()) {
            player.sendMessage(getLanguageConfiguration().getLanguagePrefix(LanguageFlag.EVENT_MODIFY_WHILE_SETTER_ON));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        LotteriaBoxPlayer byPlayer = getPlayers().getByPlayer(player);

        if (byPlayer.isChestSetter()) {
            player.sendMessage(getLanguageConfiguration().getLanguagePrefix(LanguageFlag.EVENT_MODIFY_WHILE_SETTER_ON));
            event.setCancelled(true);
        }
    }



}
