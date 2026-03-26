package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ContentService {
    private ContentService() {
    }

    public record WordRecord(double id, String uuid, String word, JSONObject translations) {
    }

    public record PhraseRecord(double id, String phrase, List<String> wordIds, Map<String, String> translations) {
    }

    public record ContentData(List<WordRecord> words, List<PhraseRecord> phrases) {
    }

    public static ContentData loadContentData() throws IOException {
        List<WordRecord> words = loadWords();
        List<PhraseRecord> phrases = loadPhrases(words);
        return new ContentData(words, phrases);
    }

    public static JSONObject loadBootstrapPayload() throws IOException {
        ContentData contentData = loadContentData();
        JSONObject payload = new JSONObject();

        JSONArray languages = new JSONArray();
        languages.addAll(Config.SUPPORTED_LANGUAGES);
        payload.put("languages", languages);

        JSONArray words = new JSONArray();
        for (WordRecord word : contentData.words()) {
            JSONObject item = new JSONObject();
            item.put("id", word.id());
            item.put("uuid", word.uuid());
            item.put("word", word.word());
            item.put("language", word.translations());
            words.add(item);
        }
        payload.put("words", words);

        JSONArray phrases = new JSONArray();
        for (PhraseRecord phrase : contentData.phrases()) {
            JSONObject item = new JSONObject();
            item.put("id", phrase.id());
            item.put("phrase", phrase.phrase());

            JSONArray wordIds = new JSONArray();
            wordIds.addAll(phrase.wordIds());
            item.put("wordIds", wordIds);

            JSONObject translations = new JSONObject();
            translations.putAll(phrase.translations());
            item.put("translations", translations);
            phrases.add(item);
        }
        payload.put("phrases", phrases);

        return payload;
    }

    public static JSONArray loadReviewCards(String language, int level) throws IOException {
        ContentData contentData = loadContentData();
        JSONArray cards = new JSONArray();

        for (PhraseRecord phrase : contentData.phrases()) {
            if ((int) Math.floor(phrase.id()) != level) {
                continue;
            }
            JSONObject card = new JSONObject();
            card.put("phrase", phrase.phrase());
            card.put("english", phrase.translations().getOrDefault("ENGLISH", phrase.phrase()));
            card.put("translation", phrase.translations().getOrDefault(language, ""));
            card.put("level", level);
            cards.add(card);
        }
        return cards;
    }

    private static List<WordRecord> loadWords() throws IOException {
        JSONArray wordsJson = JsonFileStore.readArray(Config.WORDS_FILE);
        List<WordRecord> words = new ArrayList<>();

        for (Object item : wordsJson) {
            if (!(item instanceof JSONObject rawWord)) {
                continue;
            }
            JSONObject translations = rawWord.get("language") instanceof JSONObject languageMap
                ? languageMap
                : new JSONObject();

            WordRecord wordRecord = new WordRecord(
                asDouble(rawWord.get("id"), 1.0),
                asString(rawWord.get("uuid")),
                asString(rawWord.get("word")),
                translations
            );
            if (!wordRecord.uuid().isBlank() && !wordRecord.word().isBlank()) {
                words.add(wordRecord);
            }
        }

        return words;
    }

    private static List<PhraseRecord> loadPhrases(List<WordRecord> words) throws IOException {
        Map<String, WordRecord> wordsByUuid = new HashMap<>();
        for (WordRecord word : words) {
            wordsByUuid.put(word.uuid(), word);
        }

        JSONArray phrasesJson = JsonFileStore.readArray(Config.PHRASES_FILE);
        List<PhraseRecord> phrases = new ArrayList<>();

        for (Object item : phrasesJson) {
            if (!(item instanceof JSONObject rawPhrase)) {
                continue;
            }

            List<String> wordIds = parseWordIds(rawPhrase.get("phraseWords"));
            if (wordIds.isEmpty()) {
                continue;
            }

            Map<String, String> translations = new LinkedHashMap<>();
            for (String language : Config.SUPPORTED_LANGUAGES) {
                translations.put(language, buildPhraseTranslation(wordIds, wordsByUuid, language));
            }

            PhraseRecord phraseRecord = new PhraseRecord(
                asDouble(rawPhrase.get("id"), 1.0),
                asString(rawPhrase.get("phrase")),
                wordIds,
                translations
            );
            if (!phraseRecord.phrase().isBlank()) {
                phrases.add(phraseRecord);
            }
        }

        return phrases;
    }

    private static List<String> parseWordIds(Object rawPhraseWords) {
        List<String> wordIds = new ArrayList<>();
        if (!(rawPhraseWords instanceof String phraseWords)) {
            return wordIds;
        }

        String cleaned = phraseWords.trim();
        if (cleaned.startsWith("[") && cleaned.endsWith("]") && cleaned.length() >= 2) {
            cleaned = cleaned.substring(1, cleaned.length() - 1);
        }
        if (cleaned.isBlank()) {
            return wordIds;
        }

        for (String token : cleaned.split(",")) {
            String wordId = token.trim();
            if (!wordId.isBlank()) {
                wordIds.add(wordId);
            }
        }

        return wordIds;
    }

    private static String buildPhraseTranslation(List<String> wordIds, Map<String, WordRecord> wordsByUuid, String language) {
        List<String> tokens = new ArrayList<>();
        for (String wordId : wordIds) {
            WordRecord word = wordsByUuid.get(wordId);
            if (word == null) {
                continue;
            }
            String translation = asString(word.translations().get(language));
            if (!translation.isBlank()) {
                tokens.add(translation);
            }
        }

        if (tokens.isEmpty()) {
            return "";
        }

        StringBuilder output = new StringBuilder();
        for (int index = 0; index < tokens.size(); index++) {
            String value = index == 0 ? tokens.get(index) : tokens.get(index).toLowerCase();
            output.append(value);
            if (index < tokens.size() - 1) {
                output.append(' ');
            }
        }
        return output.toString();
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

