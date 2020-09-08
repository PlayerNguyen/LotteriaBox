package com.playernguyen.lotteriabox.chest;

import com.playernguyen.lotteriabox.itemstack.RateItemStack;
import com.playernguyen.lotteriabox.manager.Picker;
import com.playernguyen.lotteriabox.tier.TierObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class ChestObject {

    private final Location location;
    private TierObject tier;
    private final UUID uniqueId;

    public ChestObject(UUID uniqueId, Location location, TierObject tier) {
        this.tier = tier;
        this.uniqueId = uniqueId;
        this.location = location;

        // Valid the block
        if (!isChest()) {
            throw new IllegalStateException(String.format("The chest's UUID %s has not a block chest!!!", uniqueId));
        }
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Location getLocation() {
        return location;
    }

    public TierObject getTier() {
        return tier;
    }

    public void setTier(TierObject tier) {
        this.tier = tier;
    }

    public void refresh() {
        Block block = location.getBlock();
        if (block.getState() instanceof Chest) {
            Chest chest = ((Chest) block.getState());
            for (int i = 0; i < chest.getBlockInventory().getSize(); i++) {
                chest.getBlockInventory().setItem(i, pick());
            }
        }
    }

    private boolean isChest() {
        Block block = location.getBlock();

        System.out.println(block);

        if (block != null) {
            return block.getState() instanceof Chest;
        }
        return false;
    }

    private ItemStack pick() {
        List<RateItemStack> stackList = getTier().getStackList();
        Picker<ItemStack> itemStackPicker = new Picker<>();

        for (RateItemStack rateItemStack : stackList) {
            itemStackPicker.add(rateItemStack.getStack(), rateItemStack.getRate());
        }

        return itemStackPicker.pick();
    }

}
