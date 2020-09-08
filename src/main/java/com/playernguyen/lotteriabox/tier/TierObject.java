package com.playernguyen.lotteriabox.tier;

import com.playernguyen.lotteriabox.itemstack.ConfigurableItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TierObject {

    private final String id;
    private final String display;
    private final List<ItemStack> stackList;

    public TierObject(String id, String display, List<ItemStack> stackList) {
        this.id = id;
        this.display = display;
        this.stackList = stackList;
    }

    public String getDisplay() {
        return display;
    }

    public String getId() {
        return id;
    }

    public List<ItemStack> getStackList() {
        return stackList;
    }


    public static TierObject loadFromConfiguration(TierConfiguration tierConfiguration) {
        String id = tierConfiguration.getString(TierFlag.ID);
        String name = tierConfiguration.getString(TierFlag.NAME);
        List<ItemStack> stacks = new ArrayList<>();

        List<String> stringList = tierConfiguration.getStringList(TierFlag.ITEM_LIST);
        for (String s : stringList) {
            stacks.add(ConfigurableItemStack.deserialize(s));
        }

        return new TierObject(id, name, stacks);
    }


}
