package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class UserService {
    private UserService() {
    }

    public static synchronized void purgeExpiredUsers() throws IOException {
        JSONArray users = loadUsers();
        JSONArray remaining = new JSONArray();
        long cutoff = System.currentTimeMillis() - Config.USER_RETENTION_MS;

        for (Object item : users) {
            if (!(item instanceof JSONObject rawUser)) {
                continue;
            }
            JSONObject user = normalizeUser(rawUser);
            long lastActive = parseTimestamp(asString(user.get("lastActiveAt")));
            if (lastActive >= cutoff) {
                remaining.add(user);
            }
        }

        if (remaining.size() != users.size()) {
            JsonFileStore.write(Config.USERS_FILE, remaining);
        }
    }

    public static synchronized JSONObject register(JSONObject payload) throws IOException, ApiException {
        String username = asString(payload.get("username"));
        String password = asString(payload.get("password"));
        String firstName = asString(payload.get("firstName"));
        String lastName = asString(payload.get("lastName"));
        String email = asString(payload.get("email"));

        if (username.length() < 3) {
            throw new ApiException(400, "INVALID_INPUT", "Username must be at least 3 characters.");
        }
        if (password.length() < 6) {
            throw new ApiException(400, "INVALID_INPUT", "Password must be at least 6 characters.");
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new ApiException(400, "INVALID_INPUT", "First name and last name are required.");
        }
        if (!email.contains("@")) {
            throw new ApiException(400, "INVALID_INPUT", "A valid email is required.");
        }

        JSONArray users = loadUsers();
        if (findUserByUsername(users, username).isPresent()) {
            throw new ApiException(409, "USERNAME_TAKEN", "That username is already in use.");
        }

        JSONObject user = new JSONObject();
        String nowIso = Instant.now().toString();
        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);

        user.put("id", UUID.randomUUID().toString());
        user.put("username", username);
        user.put("email", email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("passwordSalt", salt);
        user.put("passwordHash", hash);
        user.put("courseProg", new JSONObject());
        user.put("language", new JSONObject());
        user.put("createdAt", nowIso);
        user.put("updatedAt", nowIso);
        user.put("lastActiveAt", nowIso);

        users.add(user);
        saveUsers(users);
        return sanitizeUser(user);
    }

    public static synchronized JSONObject login(JSONObject payload) throws IOException, ApiException {
        String username = asString(payload.get("username"));
        String password = asString(payload.get("password"));
        if (username.isBlank() || password.isBlank()) {
            throw new ApiException(400, "INVALID_INPUT", "Username and password are required.");
        }

        JSONArray users = loadUsers();
        Optional<JSONObject> existing = findUserByUsername(users, username);
        if (existing.isEmpty()) {
            throw new ApiException(401, "INVALID_CREDENTIALS", "The username or password is incorrect.");
        }

        JSONObject user = normalizeUser(existing.get());
        String salt = asString(user.get("passwordSalt"));
        String hash = asString(user.get("passwordHash"));
        if (!PasswordUtil.verifyPassword(password, salt, hash)) {
            throw new ApiException(401, "INVALID_CREDENTIALS", "The username or password is incorrect.");
        }

        touchUser(user);
        replaceUser(users, user);
        saveUsers(users);
        return sanitizeUser(user);
    }

    public static synchronized JSONObject getUserById(String userId) throws IOException {
        JSONArray users = loadUsers();
        for (Object item : users) {
            if (!(item instanceof JSONObject rawUser)) {
                continue;
            }
            JSONObject user = normalizeUser(rawUser);
            if (userId.equals(asString(user.get("id")))) {
                return sanitizeUser(user);
            }
        }
        return null;
    }

    public static synchronized JSONObject addLanguage(String userId, String language) throws IOException, ApiException {
        if (!Config.SUPPORTED_LANGUAGES.contains(language)) {
            throw new ApiException(400, "INVALID_LANGUAGE", "Unsupported language.");
        }

        JSONArray users = loadUsers();
        JSONObject user = requireUser(users, userId);
        JSONObject progress = toProgressObject(user.get("courseProg"));

        double current = asDouble(progress.get(language), Double.NaN);
        progress.put(language, Double.isFinite(current) && current >= Config.COURSE_LEVEL_MIN ? current : 1.0);
        user.put("courseProg", progress);
        user.put("language", cloneProgress(progress));
        touchUser(user);

        replaceUser(users, user);
        saveUsers(users);
        return sanitizeUser(user);
    }

    public static synchronized JSONObject completeExercise(String userId, String language, double score)
        throws IOException, ApiException {
        if (!Config.SUPPORTED_LANGUAGES.contains(language)) {
            throw new ApiException(400, "INVALID_LANGUAGE", "Unsupported language.");
        }
        if (!Double.isFinite(score) || score < 0 || score > 100) {
            throw new ApiException(400, "INVALID_SCORE", "Score must be between 0 and 100.");
        }

        JSONArray users = loadUsers();
        JSONObject user = requireUser(users, userId);
        JSONObject progress = toProgressObject(user.get("courseProg"));
        double currentProgress = asDouble(progress.get(language), 1.0);

        boolean passedExercise = score >= 60.0;
        double nextProgress = passedExercise
            ? Math.min(Config.COURSE_LEVEL_MAX, currentProgress + 1.0)
            : currentProgress;
        boolean leveledUp = nextProgress > currentProgress;

        progress.put(language, nextProgress);
        user.put("courseProg", progress);
        user.put("language", cloneProgress(progress));
        touchUser(user);

        replaceUser(users, user);
        saveUsers(users);

        JSONObject response = new JSONObject();
        response.put("passedExercise", passedExercise);
        response.put("leveledUp", leveledUp);
        response.put("previousProgress", currentProgress);
        response.put("nextProgress", nextProgress);
        response.put("user", sanitizeUser(user));
        return response;
    }

    private static JSONArray loadUsers() throws IOException {
        JSONArray rawUsers = JsonFileStore.readArray(Config.USERS_FILE);
        JSONArray normalized = new JSONArray();
        boolean changed = false;

        for (Object item : rawUsers) {
            if (!(item instanceof JSONObject user)) {
                continue;
            }
            JSONObject normalizedUser = normalizeUser(user);
            normalized.add(normalizedUser);
            if (normalizedUser != user) {
                changed = true;
            }
        }

        if (changed) {
            JsonFileStore.write(Config.USERS_FILE, normalized);
        }
        return normalized;
    }

    private static void saveUsers(JSONArray users) throws IOException {
        JsonFileStore.write(Config.USERS_FILE, users);
    }

    private static JSONObject normalizeUser(JSONObject rawUser) {
        JSONObject user = new JSONObject();
        String nowIso = Instant.now().toString();

        user.put("id", valueOr(rawUser.get("id"), UUID.randomUUID().toString()));
        user.put("username", asString(rawUser.get("username")));
        user.put("email", asString(rawUser.get("email")));
        user.put("firstName", asString(rawUser.get("firstName")));
        user.put("lastName", asString(rawUser.get("lastName")));
        user.put("courseProg", toProgressObject(rawUser.get("courseProg")));
        user.put("language", toProgressObject(rawUser.get("language")));
        user.put("createdAt", valueOr(rawUser.get("createdAt"), nowIso));
        user.put("updatedAt", valueOr(rawUser.get("updatedAt"), nowIso));
        user.put("lastActiveAt", valueOr(rawUser.get("lastActiveAt"), valueOr(rawUser.get("updatedAt"), nowIso)));

        String passwordHash = asString(rawUser.get("passwordHash"));
        String passwordSalt = asString(rawUser.get("passwordSalt"));
        if (passwordHash.isBlank() || passwordSalt.isBlank()) {
            String legacyPassword = asString(rawUser.get("password"));
            String salt = PasswordUtil.generateSalt();
            String hash = PasswordUtil.hashPassword(legacyPassword.isBlank() ? UUID.randomUUID().toString() : legacyPassword, salt);
            user.put("passwordSalt", salt);
            user.put("passwordHash", hash);
        } else {
            user.put("passwordSalt", passwordSalt);
            user.put("passwordHash", passwordHash);
        }

        return user;
    }

    private static JSONObject sanitizeUser(JSONObject user) {
        JSONObject sanitized = new JSONObject();
        sanitized.put("id", asString(user.get("id")));
        sanitized.put("username", asString(user.get("username")));
        sanitized.put("email", asString(user.get("email")));
        sanitized.put("firstName", asString(user.get("firstName")));
        sanitized.put("lastName", asString(user.get("lastName")));
        sanitized.put("courseProg", cloneProgress(toProgressObject(user.get("courseProg"))));
        sanitized.put("language", cloneProgress(toProgressObject(user.get("language"))));
        sanitized.put("createdAt", valueOr(user.get("createdAt"), ""));
        sanitized.put("updatedAt", valueOr(user.get("updatedAt"), ""));
        sanitized.put("lastActiveAt", valueOr(user.get("lastActiveAt"), ""));
        return sanitized;
    }

    private static Optional<JSONObject> findUserByUsername(JSONArray users, String username) {
        String normalizedUsername = username.trim().toLowerCase(Locale.ROOT);
        for (Object item : users) {
            if (!(item instanceof JSONObject user)) {
                continue;
            }
            String existingUsername = asString(user.get("username")).toLowerCase(Locale.ROOT);
            if (existingUsername.equals(normalizedUsername)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    private static JSONObject requireUser(JSONArray users, String userId) throws ApiException {
        for (Object item : users) {
            if (!(item instanceof JSONObject user)) {
                continue;
            }
            if (asString(user.get("id")).equals(userId)) {
                return user;
            }
        }
        throw new ApiException(404, "USER_NOT_FOUND", "User account was not found.");
    }

    private static void replaceUser(JSONArray users, JSONObject replacement) {
        String replacementId = asString(replacement.get("id"));
        for (int index = 0; index < users.size(); index++) {
            Object item = users.get(index);
            if (!(item instanceof JSONObject user)) {
                continue;
            }
            if (asString(user.get("id")).equals(replacementId)) {
                users.set(index, replacement);
                return;
            }
        }
    }

    private static void touchUser(JSONObject user) {
        String nowIso = Instant.now().toString();
        user.put("updatedAt", nowIso);
        user.put("lastActiveAt", nowIso);
    }

    private static JSONObject toProgressObject(Object rawProgress) {
        JSONObject progress = new JSONObject();
        if (!(rawProgress instanceof JSONObject source)) {
            return progress;
        }

        Map<String, Double> normalized = new HashMap<>();
        for (Object key : source.keySet()) {
            String language = asString(key).toUpperCase(Locale.ROOT);
            if (!Config.SUPPORTED_LANGUAGES.contains(language)) {
                continue;
            }
            double value = asDouble(source.get(key), Double.NaN);
            if (Double.isFinite(value) && value >= Config.COURSE_LEVEL_MIN) {
                normalized.put(language, value);
            }
        }

        for (Map.Entry<String, Double> entry : normalized.entrySet()) {
            progress.put(entry.getKey(), entry.getValue());
        }
        return progress;
    }

    private static JSONObject cloneProgress(JSONObject progress) {
        JSONObject clone = new JSONObject();
        clone.putAll(progress);
        return clone;
    }

    private static long parseTimestamp(String isoValue) {
        if (isoValue == null || isoValue.isBlank()) {
            return 0L;
        }
        try {
            return Instant.parse(isoValue).toEpochMilli();
        } catch (Exception ignored) {
            return 0L;
        }
    }

    private static String valueOr(Object value, String fallback) {
        String normalized = asString(value);
        return normalized.isBlank() ? fallback : normalized;
    }

    private static String asString(Object value) {
        return value == null ? "" : String.valueOf(value).trim();
    }

    private static double asDouble(Object value, double fallback) {
        if (value == null) {
            return fallback;
        }
        try {
            return Double.parseDouble(String.valueOf(value).trim());
        } catch (NumberFormatException ignored) {
            return fallback;
        }
    }
}

