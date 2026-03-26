package com.thatguytp.lovelearninglangs.api;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordUtil {
    private static final int ITERATIONS = 200_000;
    private static final int KEY_LENGTH = 512;
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtil() {
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return HexFormat.of().formatHex(salt);
    }

    public static String hashPassword(String password, String saltHex) {
        char[] chars = password.toCharArray();
        byte[] salt = HexFormat.of().parseHex(saltHex);
        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Unable to hash password.", e);
        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verifyPassword(String password, String saltHex, String expectedHashHex) {
        if (password == null || saltHex == null || expectedHashHex == null) {
            return false;
        }
        String actual = hashPassword(password, saltHex);
        return slowEquals(actual, expectedHashHex);
    }

    private static boolean slowEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            diff |= a.charAt(i) ^ b.charAt(i);
        }
        return diff == 0;
    }
}

