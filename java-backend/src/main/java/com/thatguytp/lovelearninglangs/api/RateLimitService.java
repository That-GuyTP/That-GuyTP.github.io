package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class RateLimitService {
    private RateLimitService() {
    }

    public static final class SaveCheck {
        private final boolean allowed;
        private final int retryAfterSeconds;
        private final int remaining;

        public SaveCheck(boolean allowed, int retryAfterSeconds, int remaining) {
            this.allowed = allowed;
            this.retryAfterSeconds = retryAfterSeconds;
            this.remaining = remaining;
        }

        public boolean isAllowed() {
            return allowed;
        }

        public int getRetryAfterSeconds() {
            return retryAfterSeconds;
        }

        public int getRemaining() {
            return remaining;
        }
    }

    public static synchronized SaveCheck checkAndRecordSave(String ipAddress) throws IOException {
        JSONObject history = JsonFileStore.readObject(Config.IP_SAVE_HISTORY_FILE);
        long now = System.currentTimeMillis();
        long cutoff = now - Config.SAVE_LIMIT_WINDOW_MS;
        JSONObject trimmed = new JSONObject();

        for (Object key : history.keySet()) {
            String ip = String.valueOf(key);
            Object rawValues = history.get(key);
            if (!(rawValues instanceof JSONArray values)) {
                continue;
            }
            JSONArray inWindow = new JSONArray();
            for (Object rawValue : values) {
                long parsed = parseLong(rawValue);
                if (parsed >= cutoff) {
                    inWindow.add(parsed);
                }
            }
            if (!inWindow.isEmpty()) {
                trimmed.put(ip, inWindow);
            }
        }

        JSONArray currentEntries = trimmed.get(ipAddress) instanceof JSONArray existingEntries
            ? existingEntries
            : new JSONArray();

        if (currentEntries.size() >= Config.SAVE_LIMIT_MAX) {
            List<Long> sorted = new ArrayList<>();
            for (Object item : currentEntries) {
                sorted.add(parseLong(item));
            }
            sorted.sort(Long::compareTo);
            long oldest = sorted.isEmpty() ? now : sorted.get(0);
            int retryAfterSeconds = (int) Math.max(1L, Math.ceil((oldest + Config.SAVE_LIMIT_WINDOW_MS - now) / 1000.0));
            return new SaveCheck(false, retryAfterSeconds, 0);
        }

        currentEntries.add(now);
        trimmed.put(ipAddress, currentEntries);
        JsonFileStore.write(Config.IP_SAVE_HISTORY_FILE, trimmed);

        int remaining = Math.max(0, Config.SAVE_LIMIT_MAX - currentEntries.size());
        return new SaveCheck(true, 0, remaining);
    }

    private static long parseLong(Object value) {
        if (value == null) {
            return 0L;
        }
        try {
            return Long.parseLong(String.valueOf(value).trim());
        } catch (NumberFormatException ignored) {
            return 0L;
        }
    }
}

