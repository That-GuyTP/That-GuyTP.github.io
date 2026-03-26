import crypto from 'node:crypto';
import {
  COURSE_LEVEL_MAX,
  COURSE_LEVEL_MIN,
  SUPPORTED_LANGUAGES,
  USERS_FILE,
  USER_RETENTION_MS
} from '../config.js';
import { hashPassword, verifyPassword } from './auth.js';
import { readJsonFile, writeJsonFile } from './fileStore.js';

function toProgressMap(rawProgress) {
  const progress = {};
  if (!rawProgress || typeof rawProgress !== 'object') {
    return progress;
  }

  for (const language of SUPPORTED_LANGUAGES) {
    const value = Number(rawProgress[language]);
    if (Number.isFinite(value) && value >= COURSE_LEVEL_MIN) {
      progress[language] = value;
    }
  }
  return progress;
}

function normalizeRecord(rawRecord) {
  const nowIso = new Date().toISOString();
  const base = {
    id: String(rawRecord.id ?? crypto.randomUUID()),
    username: String(rawRecord.username ?? '').trim(),
    email: String(rawRecord.email ?? '').trim(),
    firstName: String(rawRecord.firstName ?? '').trim(),
    lastName: String(rawRecord.lastName ?? '').trim(),
    courseProg: toProgressMap(rawRecord.courseProg),
    language: toProgressMap(rawRecord.language),
    createdAt: rawRecord.createdAt ? String(rawRecord.createdAt) : nowIso,
    updatedAt: rawRecord.updatedAt ? String(rawRecord.updatedAt) : nowIso,
    lastActiveAt: rawRecord.lastActiveAt
      ? String(rawRecord.lastActiveAt)
      : rawRecord.updatedAt
        ? String(rawRecord.updatedAt)
        : nowIso
  };

  if (rawRecord.passwordHash && rawRecord.passwordSalt) {
    return {
      ...base,
      passwordHash: String(rawRecord.passwordHash),
      passwordSalt: String(rawRecord.passwordSalt)
    };
  }

  const legacyPassword = String(rawRecord.password ?? '');
  const { hash, salt } = hashPassword(legacyPassword || crypto.randomUUID());
  return {
    ...base,
    passwordHash: hash,
    passwordSalt: salt
  };
}

function sanitizeUser(record) {
  return {
    id: record.id,
    username: record.username,
    email: record.email,
    firstName: record.firstName,
    lastName: record.lastName,
    courseProg: record.courseProg,
    language: record.language,
    createdAt: record.createdAt,
    updatedAt: record.updatedAt,
    lastActiveAt: record.lastActiveAt
  };
}

function findUserById(users, userId) {
  return users.find((user) => user.id === userId) ?? null;
}

function findUserByUsername(users, username) {
  const normalizedUsername = String(username ?? '').trim().toLowerCase();
  return users.find((user) => user.username.toLowerCase() === normalizedUsername) ?? null;
}

function touchedUser(user) {
  const nowIso = new Date().toISOString();
  return {
    ...user,
    updatedAt: nowIso,
    lastActiveAt: nowIso
  };
}

function withSyncedLanguageMap(user) {
  return {
    ...user,
    language: { ...user.courseProg }
  };
}

export async function loadUsers() {
  const rawUsers = await readJsonFile(USERS_FILE, []);
  if (!Array.isArray(rawUsers)) {
    return [];
  }
  return rawUsers
    .map(normalizeRecord)
    .filter((user) => user.username && user.passwordHash && user.passwordSalt);
}

export async function saveUsers(users) {
  await writeJsonFile(USERS_FILE, users);
}

export async function purgeExpiredUsers() {
  const users = await loadUsers();
  const cutoffTime = Date.now() - USER_RETENTION_MS;
  const keptUsers = users.filter((user) => {
    const lastActive = Date.parse(user.lastActiveAt || user.updatedAt || user.createdAt);
    return Number.isFinite(lastActive) && lastActive >= cutoffTime;
  });

  if (keptUsers.length !== users.length) {
    await saveUsers(keptUsers);
  }

  return {
    removedUsers: users.length - keptUsers.length
  };
}

export async function registerUser({ username, password, email, firstName, lastName }) {
  const users = await loadUsers();
  if (findUserByUsername(users, username)) {
    return { ok: false, reason: 'USERNAME_TAKEN' };
  }

  const nowIso = new Date().toISOString();
  const { hash, salt } = hashPassword(password);
  const createdUser = withSyncedLanguageMap({
    id: crypto.randomUUID(),
    username: String(username).trim(),
    email: String(email).trim(),
    firstName: String(firstName).trim(),
    lastName: String(lastName).trim(),
    courseProg: {},
    language: {},
    passwordHash: hash,
    passwordSalt: salt,
    createdAt: nowIso,
    updatedAt: nowIso,
    lastActiveAt: nowIso
  });

  users.push(createdUser);
  await saveUsers(users);
  return { ok: true, user: sanitizeUser(createdUser) };
}

export async function loginUser({ username, password }) {
  const users = await loadUsers();
  const existingUser = findUserByUsername(users, username);
  if (!existingUser) {
    return { ok: false, reason: 'INVALID_CREDENTIALS' };
  }

  const validPassword = verifyPassword(password, existingUser.passwordSalt, existingUser.passwordHash);
  if (!validPassword) {
    return { ok: false, reason: 'INVALID_CREDENTIALS' };
  }

  return { ok: true, user: sanitizeUser(existingUser) };
}

export async function getUserById(userId) {
  const users = await loadUsers();
  const user = findUserById(users, userId);
  if (!user) {
    return null;
  }
  return sanitizeUser(user);
}

export async function addLanguageToUser(userId, language) {
  const users = await loadUsers();
  const userIndex = users.findIndex((user) => user.id === userId);
  if (userIndex < 0) {
    return { ok: false, reason: 'USER_NOT_FOUND' };
  }

  const existingUser = users[userIndex];
  if (!SUPPORTED_LANGUAGES.includes(language)) {
    return { ok: false, reason: 'INVALID_LANGUAGE' };
  }

  const currentValue = Number(existingUser.courseProg[language]);
  const nextProgress = Number.isFinite(currentValue) && currentValue > 0 ? currentValue : COURSE_LEVEL_MIN;
  const updatedUser = withSyncedLanguageMap(
    touchedUser({
      ...existingUser,
      courseProg: {
        ...existingUser.courseProg,
        [language]: nextProgress
      }
    })
  );

  users[userIndex] = updatedUser;
  await saveUsers(users);
  return { ok: true, user: sanitizeUser(updatedUser) };
}

export async function completeExerciseForUser(userId, language, score) {
  const users = await loadUsers();
  const userIndex = users.findIndex((user) => user.id === userId);
  if (userIndex < 0) {
    return { ok: false, reason: 'USER_NOT_FOUND' };
  }
  if (!SUPPORTED_LANGUAGES.includes(language)) {
    return { ok: false, reason: 'INVALID_LANGUAGE' };
  }

  const existingUser = users[userIndex];
  const currentProgress = Number(existingUser.courseProg[language]) || COURSE_LEVEL_MIN;
  const passedExercise = Number(score) >= 60;
  const nextProgress = passedExercise
    ? Math.min(COURSE_LEVEL_MAX, currentProgress + 1)
    : currentProgress;

  const updatedUser = withSyncedLanguageMap(
    touchedUser({
      ...existingUser,
      courseProg: {
        ...existingUser.courseProg,
        [language]: nextProgress
      }
    })
  );

  users[userIndex] = updatedUser;
  await saveUsers(users);

  return {
    ok: true,
    passedExercise,
    leveledUp: nextProgress > currentProgress,
    previousProgress: currentProgress,
    nextProgress,
    user: sanitizeUser(updatedUser)
  };
}

