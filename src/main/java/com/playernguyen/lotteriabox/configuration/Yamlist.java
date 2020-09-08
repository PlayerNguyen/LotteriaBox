package com.playernguyen.lotteriabox.configuration;

import com.playernguyen.lotteriabox.itemstack.ConfigurableItemStack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public abstract class Yamlist<T extends Flagable> {

    protected final File file;
    protected final FileConfiguration fileConfiguration;

    public Yamlist(Plugin plugin, String name, T[] flags, boolean b) {
        this(plugin, name, flags, b, "");
    }

    public Yamlist(Plugin plugin, File file, T[] flags) {
        this(plugin, file.getName(), flags, true);
    }

    public Yamlist(Plugin plugin, String name, T[] flags, boolean save, String parent) {
        File _parent = plugin.getDataFolder();
        if (parent != null) {
             _parent = new File(plugin.getDataFolder(), parent);
            if (!_parent.exists() && !_parent.mkdir()) {
                throw new NullPointerException(String.format("Parent %s not found...", parent));
            }
        }
        // Initial class
        this.file = new File(_parent, name);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);

        // Load default
        // the define
        for (T flag : flags) {
            if (!this.fileConfiguration.contains(flag.getPath())) {
                fileConfiguration.set(flag.getPath(), flag.getDefine());
            }
        }
        // Save the config
        if (save) {
            this.save();
        }
    }

    public Yamlist(Plugin plugin, String name, InputStream stream, String parent) {
        File _parent = plugin.getDataFolder();
        if (parent != null) {
            _parent = new File(plugin.getDataFolder(), parent);
            if (!_parent.exists() && !_parent.mkdir()) {
                throw new NullPointerException(String.format("Parent %s not found...", parent));
            }
        }
        // Initial class
        this.file = new File(_parent, name);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));

        // Save the config
        save();
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

    public Map<?, ?> getMap(T flag) {
        return (Map<?, ?>) get(flag);
    }

    public List<String> getStringList(T flag) {
        return (List<String>) get(flag);
    }

    public List<?> getList(T flag) {
        return (List<?>) get(flag);
    }
}
