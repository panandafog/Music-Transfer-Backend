package com.panandafog.mt_server.utility;

import java.util.UUID;

public class Utility {
    public static boolean isNullOrEmpty(String s) {
//        return s == null || s.trim().isEmpty();
        return s == null || s.isBlank();
    }

    public static String makeID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}