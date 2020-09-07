package com.playernguyen.lotteriabox.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public abstract class Yamlist<T extends Flagable> {

    protected final File file;
    protected final FileConfiguration fileConfiguration;

    public Yamlist(Plugin plugin, String name, T[] flags, boolean b) {
        // Initial class
        this.file = new File(plugin.getDataFolder(), name);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        // Load default
        // the define
        for (T flag : flags) {
            if (!this.fileConfiguration.contains(flag.getPath())) {
                fileConfiguration.addDefault(flag.getPath(), flag.getDefine());
            }
        }
        // Save the config
        if (b) { this.save(); }
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public File getFile() {
        return file;
    }

    public void save() {
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(T flag) {
        return this.fileConfiguration.get(flag.getPath());
    }

    public int getInt(T flag) {
        return (int) get(flag);
    }

    public double getDouble(T flag) {
        return (double) get(flag);
    }

    public String getString(T flag) {
        return (String) get(flag);
    }

    public boolean getBoolean(T flag) {
        return (boolean) get(flag);
    }
}
