package com.playernguyen.lotteriabox.itemstack;

import org.bukkit.inventory.ItemStack;

public class RateItemStack {

    private final double rate;
    private final ItemStack stack;

    public double getRate() {
        return rate;
    }

    public ItemStack getStack() {
        return stack;
    }

    public RateItemStack(double rate, ItemStack stack) {
        this.rate = rate;
        this.stack = stack;
    }
}
