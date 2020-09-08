package com.playernguyen.lotteriabox.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class LocationSerialize {

    public static String serialize(Location location) {
        StringBuilder builder = new StringBuilder();

        builder.append(location.getWorld().getUID().toString()).append(":");
        builder.append(location.getX()).append(":");
        builder.append(location.getY()).append(":");
        builder.append(location.getZ()).append(":");
        builder.append(location.getYaw()).append(":");
        builder.append(location.getPitch());

        return builder.toString();
    }

    public static Location deserialize(String serializedLocation) {
        // world:x:y:z:yaw:pitch
        ArrayList<String> path
                = new ArrayList<>(Arrays.asList(serializedLocation.split(":")));
        if (path.size() != 6) {
            throw new IllegalStateException(String.format("The configuration %s not valid for location...", serializedLocation));
        }
        World world = Bukkit.getWorld(UUID.fromString(path.get(0)));
        double x = Double.parseDouble(path.get(1));
        double y = Double.parseDouble(path.get(2));
        double z = Double.parseDouble(path.get(3));
        float yaw = Float.parseFloat(path.get(4));
        float pitch = Float.parseFloat(path.get(5));

        return new Location(world, x, y ,z, yaw, pitch);
    }

}
