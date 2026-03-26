import path from 'node:path';
import { fileURLToPath } from 'node:url';

const serverDir = path.dirname(fileURLToPath(import.meta.url));
const dataDir = path.join(serverDir, 'data');

export const SERVER_PORT = Number(process.env.LLL_API_PORT ?? 8787);
export const TOKEN_SECRET = process.env.LLL_TOKEN_SECRET ?? 'change-this-token-secret';
export const TOKEN_TTL_SECONDS = Number(process.env.LLL_TOKEN_TTL_SECONDS ?? 60 * 60 * 24 * 7);
export const USER_RETENTION_MS = Number(process.env.LLL_USER_RETENTION_MS ?? 7 * 24 * 60 * 60 * 1000);
export const SAVE_LIMIT_MAX = Number(process.env.LLL_SAVE_LIMIT_MAX ?? 50);
export const SAVE_LIMIT_WINDOW_MS = Number(process.env.LLL_SAVE_LIMIT_WINDOW_MS ?? 60 * 60 * 1000);
export const COURSE_LEVEL_MIN = 1;
export const COURSE_LEVEL_MAX = 3;

const configuredOrigins = (process.env.LLL_ALLOWED_ORIGINS ?? '*')
  .split(',')
  .map((origin) => origin.trim())
  .filter(Boolean);

export const ALLOWED_ORIGINS = configuredOrigins.length > 0 ? configuredOrigins : ['*'];
export const SUPPORTED_LANGUAGES = ['ENGLISH', 'SPANISH', 'GERMAN'];

export const WORDS_FILE = path.join(dataDir, 'Words.json');
export const PHRASES_FILE = path.join(dataDir, 'Phrases.json');
export const USERS_FILE = path.join(dataDir, 'Users.json');
export const IP_SAVE_HISTORY_FILE = path.join(dataDir, 'IpSaveHistory.json');

