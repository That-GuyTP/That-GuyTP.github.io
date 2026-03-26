package com.thatguytp.lovelearninglangs.api;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class TokenUtil {
    private static final JSONParser JSON_PARSER = new JSONParser();

    private TokenUtil() {
    }

    public static String createToken(String userId) {
        long nowSeconds = Instant.now().getEpochSecond();
        long expSeconds = nowSeconds + Config.TOKEN_TTL_SECONDS;

        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        JSONObject payload = new JSONObject();
        payload.put("sub", userId);
        payload.put("iat", nowSeconds);
        payload.put("exp", expSeconds);

        String encodedHeader = base64Url(header.toJSONString());
        String encodedPayload = base64Url(payload.toJSONString());
        String input = encodedHeader + "." + encodedPayload;
        String signature = sign(input);
        return input + "." + signature;
    }

    public static String verifyAndExtractUserId(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return null;
        }

        String input = parts[0] + "." + parts[1];
        String expectedSignature = sign(input);
        if (!slowEquals(parts[2], expectedSignature)) {
            return null;
        }

        try {
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            Object parsed = JSON_PARSER.parse(payloadJson);
            if (!(parsed instanceof JSONObject payload)) {
                return null;
            }
            Number exp = (Number) payload.get("exp");
            String subject = asString(payload.get("sub"));
            long now = Instant.now().getEpochSecond();
            if (subject.isBlank() || exp == null || now >= exp.longValue()) {
                return null;
            }
            return subject;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String sign(String input) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(Config.TOKEN_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(key);
            byte[] signature = mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to sign token.", e);
        }
    }

    private static String base64Url(String value) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(value.getBytes(StandardCharsets.UTF_8));
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

    private static String asString(Object value) {
        return value == null ? "" : String.valueOf(value);
    }
}

