package com.playernguyen.lotteriabox.util;

import java.util.Random;

public class StringUtils {


    public static String randomId(int length) {
        String defining = "1234567890ABCDEFGHKabcdefghk";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            int charAt = new Random().nextInt(defining.length());
            builder.append(defining.charAt(charAt));
        }

        return builder.toString();
    }

}
