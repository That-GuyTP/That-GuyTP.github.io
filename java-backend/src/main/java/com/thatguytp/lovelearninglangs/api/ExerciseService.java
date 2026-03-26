package com.thatguytp.lovelearninglangs.api;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class ExerciseService {
    private static final long EXERCISE_TTL_MS = 30L * 60L * 1000L;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Map<String, ExerciseSession> EXERCISE_SESSIONS = new ConcurrentHashMap<>();

    private ExerciseService() {
    }

    public static synchronized JSONObject startExercise(String userId, String language, int count)
        throws IOException, ApiException {
        if (!Config.SUPPORTED_LANGUAGES.contains(language)) {
            throw new ApiException(400, "INVALID_LANGUAGE", "Unsupported language.");
        }
        int questionCount = count <= 0 ? 10 : Math.min(20, count);

        JSONObject user = UserService.getUserById(userId);
        if (user == null) {
            throw new ApiException(404, "USER_NOT_FOUND", "Your account could not be found.");
        }

        JSONObject courseProgress = user.get("courseProg") instanceof JSONObject progress
            ? progress
            : new JSONObject();
        int level = Math.max(
            Config.COURSE_LEVEL_MIN,
            (int) Math.floor(asDouble(courseProgress.get(language), Config.COURSE_LEVEL_MIN))
        );

        ContentService.ContentData contentData = ContentService.loadContentData();
        List<Candidate> wordCandidates = buildWordCandidates(contentData.words(), language, level);
        List<Candidate> phraseCandidates = buildPhraseCandidates(contentData.phrases(), language, level);

        List<Candidate> allCandidates = new ArrayList<>();
        allCandidates.addAll(wordCandidates);
        allCandidates.addAll(phraseCandidates);

        if (allCandidates.size() < 4 || phraseCandidates.isEmpty()) {
            throw new ApiException(400, "NOT_ENOUGH_DATA", "Not enough language data to generate exercises.");
        }

        List<QuestionEnvelope> generated = generateQuestions(questionCount, allCandidates, phraseCandidates);

        String exerciseId = UUID.randomUUID().toString();
        Map<String, String> answers = new HashMap<>();
        JSONArray questionPayload = new JSONArray();
        for (QuestionEnvelope envelope : generated) {
            answers.put(envelope.id, envelope.answer);
            questionPayload.add(envelope.toPublicJson());
        }

        cleanupExpiredSessions();
        EXERCISE_SESSIONS.put(exerciseId, new ExerciseSession(userId, language, answers, Instant.now().toEpochMilli()));

        JSONObject payload = new JSONObject();
        payload.put("exerciseId", exerciseId);
        payload.put("language", language);
        payload.put("level", level);
        payload.put("questions", questionPayload);
        return payload;
    }

    public static synchronized JSONObject submitExercise(String userId, String exerciseId, JSONObject answers)
        throws IOException, ApiException {
        if (exerciseId == null || exerciseId.isBlank()) {
            throw new ApiException(400, "INVALID_INPUT", "exerciseId is required.");
        }

        cleanupExpiredSessions();
        ExerciseSession session = EXERCISE_SESSIONS.remove(exerciseId);
        if (session == null || !session.userId.equals(userId)) {
            throw new ApiException(404, "EXERCISE_NOT_FOUND", "Exercise session was not found or has expired.");
        }

        Map<String, String> submittedAnswers = new HashMap<>();
        if (answers != null) {
            for (Object key : answers.keySet()) {
                submittedAnswers.put(String.valueOf(key), asString(answers.get(key)));
            }
        }

        int correct = 0;
        int total = session.answers.size();
        for (Map.Entry<String, String> expected : session.answers.entrySet()) {
            String submitted = submittedAnswers.getOrDefault(expected.getKey(), "");
            if (normalizeAnswer(submitted).equals(normalizeAnswer(expected.getValue()))) {
                correct += 1;
            }
        }
        double score = total == 0 ? 0.0 : (correct * 100.0 / total);

        JSONObject completion = UserService.completeExercise(userId, session.language, score);
        completion.put("exerciseId", exerciseId);
        completion.put("correct", correct);
        completion.put("total", total);
        completion.put("score", score);
        return completion;
    }

    private static List<Candidate> buildWordCandidates(List<ContentService.WordRecord> words, String language, int level) {
        List<Candidate> scoped = new ArrayList<>();
        for (ContentService.WordRecord word : words) {
            if ((int) Math.floor(word.id()) != level) {
                continue;
            }
            String translation = asString(word.translations().get(language));
            if (!translation.isBlank()) {
                scoped.add(new Candidate(word.word(), translation));
            }
        }

        if (scoped.size() >= 4) {
            return scoped;
        }

        List<Candidate> fallback = new ArrayList<>();
        for (ContentService.WordRecord word : words) {
            String translation = asString(word.translations().get(language));
            if (!translation.isBlank()) {
                fallback.add(new Candidate(word.word(), translation));
            }
        }
        return fallback;
    }

    private static List<Candidate> buildPhraseCandidates(List<ContentService.PhraseRecord> phrases, String language, int level) {
        List<Candidate> scoped = new ArrayList<>();
        for (ContentService.PhraseRecord phrase : phrases) {
            if ((int) Math.floor(phrase.id()) != level) {
                continue;
            }
            String translation = phrase.translations().getOrDefault(language, "");
            if (!translation.isBlank()) {
                scoped.add(new Candidate(phrase.phrase(), translation));
            }
        }

        if (!scoped.isEmpty()) {
            return scoped;
        }

        List<Candidate> fallback = new ArrayList<>();
        for (ContentService.PhraseRecord phrase : phrases) {
            String translation = phrase.translations().getOrDefault(language, "");
            if (!translation.isBlank()) {
                fallback.add(new Candidate(phrase.phrase(), translation));
            }
        }
        return fallback;
    }

    private static List<QuestionEnvelope> generateQuestions(int count, List<Candidate> allCandidates, List<Candidate> phraseCandidates) {
        List<QuestionEnvelope> questions = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            int seed = index % 3;
            if (seed == 0) {
                questions.add(buildMultipleChoice(index + 1, allCandidates));
            } else if (seed == 1) {
                questions.add(buildTrueFalse(index + 1, allCandidates));
            } else {
                questions.add(buildFillBlank(index + 1, phraseCandidates));
            }
        }
        Collections.shuffle(questions, RANDOM);
        return questions;
    }

    private static QuestionEnvelope buildMultipleChoice(int number, List<Candidate> allCandidates) {
        Candidate target = randomItem(allCandidates);
        Set<String> options = new LinkedHashSet<>();
        options.add(target.translation);

        List<String> decoys = new ArrayList<>();
        for (Candidate candidate : allCandidates) {
            if (!candidate.translation.equals(target.translation)) {
                decoys.add(candidate.translation);
            }
        }
        Collections.shuffle(decoys, RANDOM);
        for (String decoy : decoys) {
            options.add(decoy);
            if (options.size() >= 4) {
                break;
            }
        }

        List<String> choiceList = new ArrayList<>(options);
        while (choiceList.size() < 4) {
            choiceList.add(target.translation);
        }
        Collections.shuffle(choiceList, RANDOM);

        return new QuestionEnvelope(
            "q-" + number,
            "multiple_choice",
            "What is the correct translation for \"" + target.source + "\"?",
            choiceList,
            target.translation
        );
    }

    private static QuestionEnvelope buildTrueFalse(int number, List<Candidate> allCandidates) {
        Candidate target = randomItem(allCandidates);
        boolean shouldBeTrue = RANDOM.nextBoolean();

        String displayedTranslation = target.translation;
        if (!shouldBeTrue) {
            List<String> alternatives = new ArrayList<>();
            for (Candidate candidate : allCandidates) {
                if (!candidate.translation.equals(target.translation)) {
                    alternatives.add(candidate.translation);
                }
            }
            if (!alternatives.isEmpty()) {
                displayedTranslation = randomItem(alternatives);
            } else {
                shouldBeTrue = true;
            }
        }

        List<String> choices = List.of("True", "False");
        return new QuestionEnvelope(
            "q-" + number,
            "true_false",
            "True or False: \"" + displayedTranslation + "\" is the translation of \"" + target.source + "\".",
            choices,
            shouldBeTrue ? "True" : "False"
        );
    }

    private static QuestionEnvelope buildFillBlank(int number, List<Candidate> phraseCandidates) {
        Candidate target = randomItem(phraseCandidates);
        String[] words = target.translation.split("\\s+");

        if (words.length < 2) {
            return new QuestionEnvelope(
                "q-" + number,
                "fill_blank",
                "Type the translation for \"" + target.source + "\".",
                null,
                target.translation
            );
        }

        int blankIndex = RANDOM.nextInt(words.length);
        String answer = words[blankIndex];
        String[] promptWords = words.clone();
        promptWords[blankIndex] = "___";

        return new QuestionEnvelope(
            "q-" + number,
            "fill_blank",
            "Fill in the blank: " + String.join(" ", promptWords),
            null,
            answer
        );
    }

    private static String normalizeAnswer(String value) {
        return value
            .toLowerCase(Locale.ROOT)
            .replaceAll("[.,!?;:()\\[\\]{}\"']", "")
            .trim();
    }

    private static void cleanupExpiredSessions() {
        long now = System.currentTimeMillis();
        EXERCISE_SESSIONS.entrySet().removeIf(entry -> now - entry.getValue().createdAtMs > EXERCISE_TTL_MS);
    }

    private static <T> T randomItem(List<T> items) {
        return items.get(RANDOM.nextInt(items.size()));
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

    private record Candidate(String source, String translation) {
    }

    private static final class QuestionEnvelope {
        private final String id;
        private final String type;
        private final String prompt;
        private final List<String> choices;
        private final String answer;

        private QuestionEnvelope(String id, String type, String prompt, List<String> choices, String answer) {
            this.id = id;
            this.type = type;
            this.prompt = prompt;
            this.choices = choices;
            this.answer = answer;
        }

        private JSONObject toPublicJson() {
            JSONObject question = new JSONObject();
            question.put("id", id);
            question.put("type", type);
            question.put("prompt", prompt);
            if (choices != null) {
                JSONArray jsonChoices = new JSONArray();
                jsonChoices.addAll(choices);
                question.put("choices", jsonChoices);
            }
            return question;
        }
    }

    private record ExerciseSession(String userId, String language, Map<String, String> answers, long createdAtMs) {
    }
}

