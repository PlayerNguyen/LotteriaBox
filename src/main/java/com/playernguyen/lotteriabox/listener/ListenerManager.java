package com.playernguyen.lotteriabox.listener;

import com.playernguyen.lotteriabox.LotteriaBox;
import com.playernguyen.lotteriabox.manager.ManagerSet;
import org.bukkit.Bukkit;

import java.util.HashSet;

public class ListenerManager extends ManagerSet<LotteriaBoxListener> {

    private final LotteriaBox lotteriaBox;

    public ListenerManager(LotteriaBox lotteriaBox) {
        super(new HashSet<>());
        this.lotteriaBox = lotteriaBox;
    }

    @Override
    public void add(LotteriaBoxListener item) {
        super.add(item);

        // Register
        Bukkit.getPluginManager().registerEvents(item, lotteriaBox);
    }
}
