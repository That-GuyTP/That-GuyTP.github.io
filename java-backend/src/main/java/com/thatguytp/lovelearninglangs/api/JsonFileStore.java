package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class JsonFileStore {
    private static final JSONParser JSON_PARSER = new JSONParser();

    private JsonFileStore() {
    }

    public static synchronized JSONArray readArray(Path path) throws IOException {
        Object parsed = read(path);
        if (parsed instanceof JSONArray array) {
            return array;
        }
        return new JSONArray();
    }

    public static synchronized JSONObject readObject(Path path) throws IOException {
        Object parsed = read(path);
        if (parsed instanceof JSONObject object) {
            return object;
        }
        return new JSONObject();
    }

    public static synchronized void write(Path path, Object payload) throws IOException {
        Objects.requireNonNull(path, "path");
        Files.createDirectories(path.getParent());
        Path tempPath = path.resolveSibling(path.getFileName() + ".tmp");
        String json = payload == null ? "null" : payload.toString();
        Files.writeString(tempPath, json + System.lineSeparator(), StandardCharsets.UTF_8);
        Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
    }

    private static Object read(Path path) throws IOException {
        if (!Files.exists(path)) {
            return null;
        }
        String raw = Files.readString(path, StandardCharsets.UTF_8);
        if (raw.isBlank()) {
            return null;
        }
        String cleaned = stripBom(raw);
        try {
            return JSON_PARSER.parse(cleaned);
        } catch (ParseException e) {
            throw new IOException("Unable to parse JSON file: " + path, e);
        }
    }

    private static String stripBom(String value) {
        if (value.startsWith("\uFEFF")) {
            return value.substring(1);
        }
        return value;
    }
}

