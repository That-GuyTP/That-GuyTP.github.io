import { IP_SAVE_HISTORY_FILE, SAVE_LIMIT_MAX, SAVE_LIMIT_WINDOW_MS } from '../config.js';
import { readJsonFile, writeJsonFile } from './fileStore.js';

function normalizeHistory(history) {
  if (!history || typeof history !== 'object') {
    return {};
  }

  const normalized = {};
  for (const [ipAddress, timestamps] of Object.entries(history)) {
    if (!Array.isArray(timestamps)) {
      continue;
    }
    const parsedTimestamps = timestamps
      .map((value) => Number(value))
      .filter((value) => Number.isFinite(value));
    if (parsedTimestamps.length > 0) {
      normalized[ipAddress] = parsedTimestamps;
    }
  }
  return normalized;
}

function getCutoff(nowMs) {
  return nowMs - SAVE_LIMIT_WINDOW_MS;
}

export async function checkAndRecordSaveByIp(ipAddress) {
  const nowMs = Date.now();
  const cutoff = getCutoff(nowMs);
  const history = normalizeHistory(await readJsonFile(IP_SAVE_HISTORY_FILE, {}));
  const trimmedHistory = {};

  for (const [knownIp, knownTimestamps] of Object.entries(history)) {
    const inWindow = knownTimestamps.filter((value) => value >= cutoff);
    if (inWindow.length > 0) {
      trimmedHistory[knownIp] = inWindow;
    }
  }

  const currentEntries = trimmedHistory[ipAddress] ?? [];
  if (currentEntries.length >= SAVE_LIMIT_MAX) {
    const oldestAllowedEntry = currentEntries[0];
    const retryAfterMs = Math.max(1_000, oldestAllowedEntry + SAVE_LIMIT_WINDOW_MS - nowMs);
    return {
      allowed: false,
      remaining: 0,
      retryAfterMs
    };
  }

  trimmedHistory[ipAddress] = [...currentEntries, nowMs];
  await writeJsonFile(IP_SAVE_HISTORY_FILE, trimmedHistory);

  return {
    allowed: true,
    remaining: Math.max(0, SAVE_LIMIT_MAX - trimmedHistory[ipAddress].length),
    retryAfterMs: 0
  };
}

