import { PHRASES_FILE, SUPPORTED_LANGUAGES, WORDS_FILE } from '../config.js';
import { readJsonFile } from './fileStore.js';

function asNumber(value, fallback = 0) {
  const parsed = Number(value);
  return Number.isFinite(parsed) ? parsed : fallback;
}

function normalizeWord(rawWord) {
  return {
    id: String(rawWord.id ?? ''),
    uuid: String(rawWord.uuid ?? ''),
    word: String(rawWord.word ?? ''),
    partOfSpeech: String(rawWord.partOfSpeech ?? ''),
    gender: String(rawWord.gender ?? 'neutral'),
    language: typeof rawWord.language === 'object' && rawWord.language ? rawWord.language : {}
  };
}

function parsePhraseWordIds(phraseWords) {
  if (Array.isArray(phraseWords)) {
    return phraseWords.map((wordId) => String(wordId).trim()).filter(Boolean);
  }

  if (typeof phraseWords !== 'string') {
    return [];
  }

  const cleaned = phraseWords.trim().replace(/^\[/, '').replace(/\]$/, '');
  if (!cleaned) {
    return [];
  }

  return cleaned
    .split(',')
    .map((wordId) => wordId.trim())
    .filter(Boolean);
}

function buildPhraseTranslation(phraseWordIds, wordsByUuid, language) {
  const translatedWords = [];
  for (const wordId of phraseWordIds) {
    const word = wordsByUuid.get(wordId);
    if (!word) {
      continue;
    }
    const rawTranslation = word.language?.[language];
    if (!rawTranslation) {
      continue;
    }
    translatedWords.push(String(rawTranslation));
  }

  if (translatedWords.length === 0) {
    return '';
  }

  return translatedWords
    .map((value, index) => (index === 0 ? value : value.toLowerCase()))
    .join(' ');
}

function normalizePhrase(rawPhrase, wordsByUuid) {
  const wordIds = parsePhraseWordIds(rawPhrase.phraseWords);
  const translations = {};
  for (const language of SUPPORTED_LANGUAGES) {
    translations[language] = buildPhraseTranslation(wordIds, wordsByUuid, language);
  }

  return {
    id: asNumber(rawPhrase.id, 1),
    phrase: String(rawPhrase.phrase ?? ''),
    phraseWords: String(rawPhrase.phraseWords ?? ''),
    wordIds,
    translations
  };
}

export async function loadWords() {
  const words = await readJsonFile(WORDS_FILE, []);
  if (!Array.isArray(words)) {
    return [];
  }
  return words.map(normalizeWord).filter((word) => word.uuid && word.word);
}

export async function loadPhrases(words) {
  const wordsByUuid = new Map(words.map((word) => [word.uuid, word]));
  const phrases = await readJsonFile(PHRASES_FILE, []);
  if (!Array.isArray(phrases)) {
    return [];
  }
  return phrases
    .map((phrase) => normalizePhrase(phrase, wordsByUuid))
    .filter((phrase) => phrase.phrase && phrase.wordIds.length > 0);
}

export async function loadBootstrapContent() {
  const words = await loadWords();
  const phrases = await loadPhrases(words);
  return {
    languages: SUPPORTED_LANGUAGES,
    words,
    phrases
  };
}

