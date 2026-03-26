package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class ApiServer {
    private static final JSONParser JSON_PARSER = new JSONParser();
    private final HttpServer server;

    public ApiServer() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(Config.API_PORT), 0);
        this.server.createContext("/api", this::handle);
        this.server.setExecutor(Executors.newCachedThreadPool());
    }

    public void start() {
        server.start();
        System.out.println("[lovelearninglangs-java-api] Listening on http://localhost:" + Config.API_PORT);
    }

    private void handle(HttpExchange exchange) throws IOException {
        setCorsHeaders(exchange);
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        try {
            if ("GET".equalsIgnoreCase(method) && "/api/health".equals(path)) {
                JSONObject payload = new JSONObject();
                payload.put("ok", true);
                payload.put("service", "lovelearninglangs-java-api");
                payload.put("timestamp", Instant.now().toString());
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("GET".equalsIgnoreCase(method) && "/api/content/bootstrap".equals(path)) {
                sendJson(exchange, 200, ContentService.loadBootstrapPayload(), null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/auth/register".equals(path)) {
                UserService.purgeExpiredUsers();
                if (!allowSave(exchange)) {
                    return;
                }
                JSONObject body = readJsonBody(exchange);
                JSONObject user = UserService.register(body);
                JSONObject payload = new JSONObject();
                payload.put("token", TokenUtil.createToken(asString(user.get("id"))));
                payload.put("user", user);
                sendJson(exchange, 201, payload, null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/auth/login".equals(path)) {
                UserService.purgeExpiredUsers();
                JSONObject body = readJsonBody(exchange);
                JSONObject user = UserService.login(body);
                JSONObject payload = new JSONObject();
                payload.put("token", TokenUtil.createToken(asString(user.get("id"))));
                payload.put("user", user);
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("GET".equalsIgnoreCase(method) && "/api/auth/me".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                JSONObject user = UserService.getUserById(userId);
                if (user == null) {
                    throw new ApiException(401, "UNAUTHORIZED", "Please sign in to continue.");
                }
                JSONObject payload = new JSONObject();
                payload.put("user", user);
                payload.put("retentionDays", Config.USER_RETENTION_MS / (24L * 60L * 60L * 1000L));
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/progress/add-language".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                if (!allowSave(exchange)) {
                    return;
                }
                JSONObject body = readJsonBody(exchange);
                String language = normalizeLanguage(body.get("language"));
                JSONObject payload = new JSONObject();
                payload.put("user", UserService.addLanguage(userId, language));
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/progress/complete-exercise".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                if (!allowSave(exchange)) {
                    return;
                }
                JSONObject body = readJsonBody(exchange);
                String language = normalizeLanguage(body.get("language"));
                double score = parseDouble(body.get("score"), Double.NaN);
                JSONObject payload = UserService.completeExercise(userId, language, score);
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("GET".equalsIgnoreCase(method) && "/api/progress/review".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                JSONObject user = UserService.getUserById(userId);
                if (user == null) {
                    throw new ApiException(401, "UNAUTHORIZED", "Please sign in to continue.");
                }
                Map<String, String> query = parseQueryString(exchange.getRequestURI().getRawQuery());
                String language = normalizeLanguage(query.get("language"));
                int level = extractUserLevel(user, language);
                JSONObject payload = new JSONObject();
                payload.put("language", language);
                payload.put("level", level);
                payload.put("reviewCards", ContentService.loadReviewCards(language, level));
                sendJson(exchange, 200, payload, null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/exercise/start".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                JSONObject body = readJsonBody(exchange);
                String language = normalizeLanguage(body.get("language"));
                int count = (int) Math.floor(parseDouble(body.get("count"), 10.0));
                sendJson(exchange, 200, ExerciseService.startExercise(userId, language, count), null);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/exercise/submit".equals(path)) {
                UserService.purgeExpiredUsers();
                String userId = requireAuthenticatedUser(exchange);
                if (!allowSave(exchange)) {
                    return;
                }
                JSONObject body = readJsonBody(exchange);
                String exerciseId = asString(body.get("exerciseId"));
                JSONObject answers = body.get("answers") instanceof JSONObject values ? values : new JSONObject();
                sendJson(exchange, 200, ExerciseService.submitExercise(userId, exerciseId, answers), null);
                return;
            }

            throw new ApiException(404, "NOT_FOUND", "Endpoint not found.");
        } catch (ApiException e) {
            JSONObject payload = new JSONObject();
            payload.put("error", e.getErrorCode());
            payload.put("message", e.getMessage());
            sendJson(exchange, e.getStatusCode(), payload, null);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject payload = new JSONObject();
            payload.put("error", "INTERNAL_ERROR");
            payload.put("message", "An unexpected error occurred.");
            sendJson(exchange, 500, payload, null);
        }
    }

    private int extractUserLevel(JSONObject user, String language) {
        if (user == null) {
            return Config.COURSE_LEVEL_MIN;
        }
        if (!(user.get("courseProg") instanceof JSONObject progress)) {
            return Config.COURSE_LEVEL_MIN;
        }
        double value = parseDouble(progress.get(language), Config.COURSE_LEVEL_MIN);
        return Math.max(Config.COURSE_LEVEL_MIN, (int) Math.floor(value));
    }

    private boolean allowSave(HttpExchange exchange) throws IOException {
        String ipAddress = clientIp(exchange);
        RateLimitService.SaveCheck check = RateLimitService.checkAndRecordSave(ipAddress);
        if (check.isAllowed()) {
            return true;
        }
        JSONObject payload = new JSONObject();
        payload.put("error", "SAVE_RATE_LIMITED");
        payload.put("message", "Too many save operations from this IP. Please try again soon.");
        payload.put("retryAfterSeconds", check.getRetryAfterSeconds());
        Map<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("Retry-After", String.valueOf(check.getRetryAfterSeconds()));
        sendJson(exchange, 429, payload, extraHeaders);
        return false;
    }

    private String requireAuthenticatedUser(HttpExchange exchange) throws ApiException, IOException {
        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ApiException(401, "UNAUTHORIZED", "Please sign in to continue.");
        }
        String token = authHeader.substring(7).trim();
        String userId = TokenUtil.verifyAndExtractUserId(token);
        if (userId == null) {
            throw new ApiException(401, "UNAUTHORIZED", "Please sign in to continue.");
        }
        if (UserService.getUserById(userId) == null) {
            throw new ApiException(401, "UNAUTHORIZED", "Please sign in to continue.");
        }
        return userId;
    }

    private JSONObject readJsonBody(HttpExchange exchange) throws IOException, ApiException {
        byte[] body = readRequestBytes(exchange, 1_000_000);
        if (body.length == 0) {
            return new JSONObject();
        }
        String raw = new String(body, StandardCharsets.UTF_8).trim();
        if (raw.isBlank()) {
            return new JSONObject();
        }
        try {
            Object parsed = JSON_PARSER.parse(raw);
            if (parsed instanceof JSONObject object) {
                return object;
            }
            throw new ApiException(400, "INVALID_JSON", "Request body must be a JSON object.");
        } catch (ParseException e) {
            throw new ApiException(400, "INVALID_JSON", "Request body must be valid JSON.");
        }
    }

    private byte[] readRequestBytes(HttpExchange exchange, int maxBytes) throws IOException, ApiException {
        try (InputStream input = exchange.getRequestBody()) {
            byte[] buffer = new byte[8192];
            int read;
            int total = 0;
            byte[] output = new byte[0];

            while ((read = input.read(buffer)) != -1) {
                total += read;
                if (total > maxBytes) {
                    throw new ApiException(413, "PAYLOAD_TOO_LARGE", "Request body is too large.");
                }
                byte[] next = new byte[output.length + read];
                System.arraycopy(output, 0, next, 0, output.length);
                System.arraycopy(buffer, 0, next, output.length, read);
                output = next;
            }
            return output;
        }
    }

    private void sendJson(HttpExchange exchange, int statusCode, JSONObject payload, Map<String, String> extraHeaders)
        throws IOException {
        byte[] bytes = (payload.toJSONString() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        if (extraHeaders != null) {
            for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
                exchange.getResponseHeaders().set(entry.getKey(), entry.getValue());
            }
        }
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
        exchange.close();
    }

    private void setCorsHeaders(HttpExchange exchange) {
        String requestOrigin = exchange.getRequestHeaders().getFirst("Origin");
        String allowOrigin = null;
        if (Config.ALLOWED_ORIGINS.contains("*")) {
            allowOrigin = requestOrigin == null || requestOrigin.isBlank() ? "*" : requestOrigin;
        } else if (requestOrigin != null && Config.ALLOWED_ORIGINS.contains(requestOrigin)) {
            allowOrigin = requestOrigin;
        }

        if (allowOrigin != null) {
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", allowOrigin);
        }
        exchange.getResponseHeaders().set("Vary", "Origin");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type,Authorization");
    }

    private Map<String, String> parseQueryString(String rawQuery) {
        Map<String, String> values = new HashMap<>();
        if (rawQuery == null || rawQuery.isBlank()) {
            return values;
        }
        for (String token : rawQuery.split("&")) {
            if (token.isBlank()) {
                continue;
            }
            String[] pair = token.split("=", 2);
            String key = decode(pair[0]);
            String value = pair.length > 1 ? decode(pair[1]) : "";
            values.put(key, value);
        }
        return values;
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private String normalizeLanguage(Object value) throws ApiException {
        String language = asString(value).toUpperCase(Locale.ROOT);
        if (!Config.SUPPORTED_LANGUAGES.contains(language)) {
            throw new ApiException(
                400,
                "INVALID_LANGUAGE",
                "Language must be one of: " + String.join(", ", Config.SUPPORTED_LANGUAGES) + "."
            );
        }
        return language;
    }

    private String clientIp(HttpExchange exchange) {
        String forwardedFor = exchange.getRequestHeaders().getFirst("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            String[] parts = forwardedFor.split(",");
            if (parts.length > 0) {
                return parts[0].trim();
            }
        }
        return exchange.getRemoteAddress().getAddress().getHostAddress();
    }

    private static String asString(Object value) {
        return value == null ? "" : String.valueOf(value).trim();
    }

    private static double parseDouble(Object value, double fallback) {
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

