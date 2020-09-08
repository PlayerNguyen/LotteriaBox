package com.playernguyen.lotteriabox.itemstack;

import com.playernguyen.lotteriabox.util.NumberUtils;
import com.playernguyen.weaponist.Weaponist;
import com.playernguyen.weaponist.manager.ManagerSet;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ConfigurableItemStack {

    enum ItemPlugin {
        BUKKIT("bukkit"),
        WEAPONIST("weaponist");

        private final String id;

        ItemPlugin(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        private static ItemPlugin getFromId(String id) {
            for (ItemPlugin value : ItemPlugin.values()) {
                if (value.getId().toLowerCase().equalsIgnoreCase(id)) {
                    return value;
                }
            }
            return null;
        }
    }


    public static RateItemStack deserialize(String string) {
        // Split the string
        List<String> _divineList = Arrays.asList(string.split(":"));
        if (_divineList.size() <= 2) {
            throw new NullPointerException(String.format("Empty fields with config %s", string));
        }
        if (!NumberUtils.isDouble(_divineList.get(0))) {
            throw new IllegalStateException(String.format("The value %s is not a number", _divineList.get(0)));
        }
        double d = Double.parseDouble(_divineList.get(0));
        ItemPlugin itemPlugin = ItemPlugin.getFromId(_divineList.get(1));
        if (itemPlugin == null) {
            throw new IllegalStateException("Invalid item configuration");
        }

        switch (itemPlugin) {
            case BUKKIT: {
                // Example for Bukkit
                // bukkit:material:amount:name:[lore1,lore2]
                List<String> _bukkitArgs = _divineList.subList(2, _divineList.size());
                if (_bukkitArgs.size() < 1) {
                    throw new IllegalStateException(String.format("Fields empty of config %s", string));
                }
                // First define
                // Match Material
                Material material = Material.matchMaterial(_bukkitArgs.get(0));
                if (material == null) {
                    throw new NullPointerException(String.format(
                            "Material not found %s",
                            _bukkitArgs.get(0)
                    ));
                }
                // Amount checker
                int amount = 1;
                if (!NumberUtils.isInteger(_bukkitArgs.get(1))) {
                    throw new IllegalArgumentException(String.format(
                            "The amount must be integer number. Not %s",
                            _bukkitArgs.get(1)
                    ));
                } else amount = Integer.parseInt(_bukkitArgs.get(1));

                // Build ItemStack
                ItemStack stack = new ItemStack(material, amount);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    // Name set
                    if (_bukkitArgs.size() > 2) {
                        String name = _bukkitArgs.get(2);
                        if (name != null)
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    }
                    // Lore set
                    if (_bukkitArgs.size() > 3) {
                        String rawLore = _bukkitArgs.get(3);
                        if (rawLore != null) {
                            List<String> lore = deserializeLore(rawLore);
                            meta.setLore(lore);
                        }
                    }
                }
                // Set meta and return
                stack.setItemMeta(meta);
                return new RateItemStack(d, stack);
            }
            case WEAPONIST: {
                // Example:
                // weaponist:weapon-type:weapon-id
                //
                List<String> _weaponistArguments = _divineList.subList(2, _divineList.size());
                // Valid type
                if (_weaponistArguments.size() < 2) {
                    throw new IllegalStateException(String.format("Fields empty of config %s", string));
                }
                // Weapon manager get
//                ManagerSet wp = getWeaponistManager(_weaponistArguments.get(0));
//                if (wp == null) {
//                    throw new NullPointerException(String.format("Not found type %s", _weaponistArguments.get(0)));
//                }

                return new RateItemStack(d,
                        Weaponist.getWeaponist().getGunManager().getRegisteredWeapon("ak_47").toItem(null, 1));
            }
            default: throw new NullPointerException("The id of plugin item not found...");
        }

    }

    private static List<String> deserializeLore(String string) {
        String _compileString = string
                .replace("[", "")
                .replace("]", "");
        String[] split = _compileString.split(",");
        return Arrays.asList(split);
    }

    private static boolean isValidWeaponType(String type) {
        if (type.toLowerCase().equalsIgnoreCase("gun")) {
            return true;
        }
        return false;
    }

    private static ManagerSet<?> getWeaponistManager(String type) {
        if (type.toLowerCase().equalsIgnoreCase("gun")) {
            return Weaponist.getWeaponist().getGunManager();
        }
        return null;
    }


}
