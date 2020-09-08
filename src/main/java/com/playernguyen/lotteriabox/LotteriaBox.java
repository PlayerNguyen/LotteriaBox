package com.playernguyen.lotteriabox;

import com.playernguyen.lotteriabox.chest.ChestConfiguration;
import com.playernguyen.lotteriabox.chest.ChestManager;
import com.playernguyen.lotteriabox.chest.ChestObject;
import com.playernguyen.lotteriabox.command.HubCommandManager;
import com.playernguyen.lotteriabox.command.LotteriaCommand;
import com.playernguyen.lotteriabox.language.LanguageConfiguration;
import com.playernguyen.lotteriabox.listener.ListenerManager;
import com.playernguyen.lotteriabox.listener.LotteriaBoxPlayerListener;
import com.playernguyen.lotteriabox.player.PlayerManager;
import com.playernguyen.lotteriabox.runnable.ChestUpdateRunnable;
import com.playernguyen.lotteriabox.setting.SettingConfiguration;
import com.playernguyen.lotteriabox.tier.TierConfiguration;
import com.playernguyen.lotteriabox.tier.TierManager;
import com.playernguyen.lotteriabox.tier.TierObject;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LotteriaBox extends JavaPlugin {

    private static LotteriaBox instance;
    private ListenerManager listeners;
    private SettingConfiguration settingConfiguration;
    private LanguageConfiguration languageConfiguration;
    private PlayerManager players;
    private TierManager tiers;
    private HubCommandManager hubCommands;
    private ChestManager chests;

    @Override
    public void onEnable() {
        // Setup instance
        setupInstance();
        // Setup settings
        setupSettings();
        // Setup languages
        setupLanguages();
        // Setup tier
        setupTiers();
        // Setup listener
        setupListeners();
        // Setup player
        setupPlayers();
        // Setup command
        setupCommands();
        // setup chests
        setupChests();
    }

    private void setupSettings() {
        this.settingConfiguration = new SettingConfiguration(this);
    }

    private void setupChests() {
        this.chests = new ChestManager();
        // Load chest from list
        File _parent = new File(this.getDataFolder(), ChestConfiguration.CHEST_PARENT_NAME);
        if (!_parent.exists() && !_parent.mkdir()) {
            throw new NullPointerException("Cannot found chest parent folder");
        }
        // Now take all the children and kiss them
        ArrayList<String> children = new ArrayList<>(Arrays.asList(_parent.list()));
            // This is a little boy :)
            children.remove(".DS_Store");
        children.forEach(e -> {
            ChestObject chestObject = new ChestConfiguration(this, e).toChestObject();
            getLogger().info("Load chest with uuid: " + chestObject.getUniqueId());
            getChests().add(chestObject);
        });
        // First refresh
        this.chests.refresh();
        // Run the task
        ChestUpdateRunnable updateRunnable = new ChestUpdateRunnable();
        updateRunnable.runTaskTimer(this, 20, 20);
    }

    private void setupCommands() {
        this.hubCommands = new HubCommandManager();
        // Add command
        getHubCommands().add(new LotteriaCommand());
    }

    private void setupLanguages() {
        this.languageConfiguration = new LanguageConfiguration(this);
    }

    private void setupTiers() {
        this.tiers = new TierManager();

        File tierFolder = TierConfiguration.getTierFolder(this);
        if (!tierFolder.exists() && tierFolder.mkdir()) {
            new TierConfiguration(this, "default.yml");
        }
        ArrayList<String> fileNames = new ArrayList<>(Arrays.asList(tierFolder.list()));
        // With macOS
        fileNames.remove(".DS_Store");
        // Iterable to add new
        fileNames.forEach(e -> {
            this.getLogger().info(String.format("Loading tiers %s...", e));
            TierConfiguration tierConfiguration = new TierConfiguration(this, e);
            // Load from configuration
            getTiers().add(TierObject.loadFromConfiguration(tierConfiguration));
        });
    }


    private void setupPlayers() {
        this.players = new PlayerManager();
        // Players
        Bukkit.getOnlinePlayers().forEach(getPlayers()::createNewPlayer);
    }

    private void setupListeners() {
        this.listeners = new ListenerManager(this);
        // Add new listener here
        getListeners().add(new LotteriaBoxPlayerListener());
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

    public PlayerManager getPlayers() {
        return players;
    }

    public TierManager getTiers() {
        return tiers;
    }

    public LanguageConfiguration getLanguageConfiguration() {
        return languageConfiguration;
    }

    public HubCommandManager getHubCommands() {
        return hubCommands;
    }

    public ChestManager getChests() {
        return chests;
    }

    public SettingConfiguration getSettings() {
        return settingConfiguration;
    }
}
