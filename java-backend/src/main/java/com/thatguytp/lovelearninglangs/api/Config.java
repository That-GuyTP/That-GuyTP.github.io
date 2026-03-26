package com.thatguytp.lovelearninglangs.api;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Config {
    public static final int API_PORT = envInt("LLL_API_PORT", 8787);
    public static final String TOKEN_SECRET = env("LLL_TOKEN_SECRET", "change-this-token-secret");
    public static final long TOKEN_TTL_SECONDS = envLong("LLL_TOKEN_TTL_SECONDS", 60L * 60L * 24L * 7L);
    public static final long USER_RETENTION_MS = envLong("LLL_USER_RETENTION_MS", 7L * 24L * 60L * 60L * 1000L);
    public static final int SAVE_LIMIT_MAX = envInt("LLL_SAVE_LIMIT_MAX", 50);
    public static final long SAVE_LIMIT_WINDOW_MS = envLong("LLL_SAVE_LIMIT_WINDOW_MS", 60L * 60L * 1000L);

    public static final int COURSE_LEVEL_MIN = 1;
    public static final int COURSE_LEVEL_MAX = 3;

    public static final List<String> SUPPORTED_LANGUAGES = List.of("ENGLISH", "SPANISH", "GERMAN");

    private static final Path DATA_DIR = resolveDataDirectory();
    public static final Path USERS_FILE = DATA_DIR.resolve("Users.json");
    public static final Path WORDS_FILE = DATA_DIR.resolve("Words.json");
    public static final Path PHRASES_FILE = DATA_DIR.resolve("Phrases.json");
    public static final Path IP_SAVE_HISTORY_FILE = DATA_DIR.resolve("IpSaveHistory.json");

    public static final Set<String> ALLOWED_ORIGINS = resolveAllowedOrigins();

    private Config() {
    }

    private static Set<String> resolveAllowedOrigins() {
        String raw = env("LLL_ALLOWED_ORIGINS", "*");
        return Arrays.stream(raw.split(","))
            .map(String::trim)
            .filter(value -> !value.isEmpty())
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Path resolveDataDirectory() {
        String override = System.getenv("LLL_DATA_DIR");
        if (override != null && !override.isBlank()) {
            return Paths.get(override.trim());
        }
        return Paths.get("server", "data");
    }

    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        return value.trim();
    }

    private static int envInt(String key, int fallback) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static long envLong(String key, long fallback) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return fallback;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }
}

