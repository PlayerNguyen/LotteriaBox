package com.playernguyen.lotteriabox;

import com.playernguyen.lotteriabox.listener.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LotteriaBox extends JavaPlugin {

    private static LotteriaBox instance;
    private ListenerManager listeners;

    @Override
    public void onEnable() {
        setupInstance();

        setupListeners();
    }

    private void setupListeners() {
        this.listeners = new ListenerManager(this);
    }

    /**
     * Instance setup
     */
    private void setupInstance() {
        instance = this;
    }

    /**
     * Instance get
     * @return The {@link LotteriaBox} object
     */
    public static LotteriaBox getInstance() {
        return instance;
    }

    /**
     * Listener manager
     * @return The {@link ListenerManager} object
     */
    public ListenerManager getListeners() {
        return listeners;
    }
}
