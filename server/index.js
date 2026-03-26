import { createServer } from 'node:http';
import {
  ALLOWED_ORIGINS,
  COURSE_LEVEL_MIN,
  SERVER_PORT,
  SUPPORTED_LANGUAGES,
  TOKEN_SECRET,
  TOKEN_TTL_SECONDS,
  USER_RETENTION_MS
} from './config.js';
import { createSignedToken, verifySignedToken } from './lib/auth.js';
import { loadBootstrapContent } from './lib/contentStore.js';
import { checkAndRecordSaveByIp } from './lib/saveLimiter.js';
import {
  addLanguageToUser,
  completeExerciseForUser,
  getUserById,
  loginUser,
  purgeExpiredUsers,
  registerUser
} from './lib/userStore.js';

const JSON_HEADERS = {
  'Content-Type': 'application/json; charset=utf-8',
  'Cache-Control': 'no-store'
};

function writeJson(res, statusCode, payload, extraHeaders = {}) {
  res.writeHead(statusCode, { ...JSON_HEADERS, ...extraHeaders });
  res.end(`${JSON.stringify(payload)}\n`);
}

function resolveCorsOrigin(req) {
  const requestOrigin = req.headers.origin;
  if (ALLOWED_ORIGINS.includes('*')) {
    return requestOrigin || '*';
  }
  if (requestOrigin && ALLOWED_ORIGINS.includes(requestOrigin)) {
    return requestOrigin;
  }
  return null;
}

function setCorsHeaders(req, res) {
  const corsOrigin = resolveCorsOrigin(req);
  if (corsOrigin) {
    res.setHeader('Access-Control-Allow-Origin', corsOrigin);
  }
  res.setHeader('Vary', 'Origin');
  res.setHeader('Access-Control-Allow-Methods', 'GET,POST,OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type,Authorization');
}

function getClientIp(req) {
  const forwardedFor = req.headers['x-forwarded-for'];
  if (typeof forwardedFor === 'string' && forwardedFor.trim()) {
    return forwardedFor.split(',')[0].trim();
  }
  const socketAddress = req.socket?.remoteAddress ?? 'unknown';
  return socketAddress.replace(/^::ffff:/, '');
}

async function parseJsonBody(req) {
  const chunks = [];
  let totalBytes = 0;

  for await (const chunk of req) {
    totalBytes += chunk.length;
    if (totalBytes > 1_000_000) {
      throw new Error('BODY_TOO_LARGE');
    }
    chunks.push(chunk);
  }

  const payload = Buffer.concat(chunks).toString('utf8').trim();
  if (!payload) {
    return {};
  }

  try {
    return JSON.parse(payload);
  } catch {
    throw new Error('INVALID_JSON');
  }
}

function getBearerToken(req) {
  const header = req.headers.authorization;
  if (!header || !header.startsWith('Bearer ')) {
    return null;
  }
  return header.slice(7).trim();
}

async function getAuthenticatedUser(req) {
  const token = getBearerToken(req);
  if (!token) {
    return null;
  }

  const payload = verifySignedToken(token, TOKEN_SECRET);
  if (!payload?.sub) {
    return null;
  }

  const user = await getUserById(payload.sub);
  if (!user) {
    return null;
  }

  return user;
}

function ensureLanguage(value) {
  const normalized = String(value ?? '').toUpperCase().trim();
  if (!SUPPORTED_LANGUAGES.includes(normalized)) {
    return null;
  }
  return normalized;
}

function isNonEmptyString(value) {
  return typeof value === 'string' && value.trim().length > 0;
}

function validateRegisterPayload(body) {
  if (!isNonEmptyString(body.username) || body.username.trim().length < 3) {
    return 'Username must be at least 3 characters.';
  }
  if (!isNonEmptyString(body.password) || body.password.length < 6) {
    return 'Password must be at least 6 characters.';
  }
  if (!isNonEmptyString(body.firstName)) {
    return 'First name is required.';
  }
  if (!isNonEmptyString(body.lastName)) {
    return 'Last name is required.';
  }
  if (!isNonEmptyString(body.email) || !body.email.includes('@')) {
    return 'A valid email is required.';
  }
  return null;
}

async function enforceSaveLimitForRequest(req, res) {
  const ipAddress = getClientIp(req);
  const saveCheck = await checkAndRecordSaveByIp(ipAddress);
  if (saveCheck.allowed) {
    return true;
  }

  const retrySeconds = Math.ceil(saveCheck.retryAfterMs / 1000);
  writeJson(
    res,
    429,
    {
      error: 'SAVE_RATE_LIMITED',
      message: 'Too many save operations from this IP. Please try again soon.',
      retryAfterSeconds: retrySeconds
    },
    { 'Retry-After': String(retrySeconds) }
  );
  return false;
}

async function handleRequest(req, res) {
  setCorsHeaders(req, res);
  if (req.method === 'OPTIONS') {
    res.writeHead(204);
    res.end();
    return;
  }

  const requestUrl = new URL(req.url ?? '/', `http://${req.headers.host ?? 'localhost'}`);
  const { pathname } = requestUrl;
  const method = req.method ?? 'GET';

  if (method === 'GET' && pathname === '/api/health') {
    writeJson(res, 200, {
      ok: true,
      service: 'lovelearninglangs-api',
      timestamp: new Date().toISOString()
    });
    return;
  }

  if (method === 'GET' && pathname === '/api/content/bootstrap') {
    const content = await loadBootstrapContent();
    writeJson(res, 200, content);
    return;
  }

  if (method === 'POST' && pathname === '/api/auth/register') {
    const body = await parseJsonBody(req);
    const validationError = validateRegisterPayload(body);
    if (validationError) {
      writeJson(res, 400, { error: 'INVALID_INPUT', message: validationError });
      return;
    }

    await purgeExpiredUsers();
    if (!(await enforceSaveLimitForRequest(req, res))) {
      return;
    }

    const creation = await registerUser({
      username: body.username,
      password: body.password,
      firstName: body.firstName,
      lastName: body.lastName,
      email: body.email
    });

    if (!creation.ok) {
      writeJson(res, 409, {
        error: 'USERNAME_TAKEN',
        message: 'That username is already in use.'
      });
      return;
    }

    const token = createSignedToken({ sub: creation.user.id }, TOKEN_SECRET, TOKEN_TTL_SECONDS);
    writeJson(res, 201, {
      token,
      user: creation.user
    });
    return;
  }

  if (method === 'POST' && pathname === '/api/auth/login') {
    await purgeExpiredUsers();
    const body = await parseJsonBody(req);
    if (!isNonEmptyString(body.username) || !isNonEmptyString(body.password)) {
      writeJson(res, 400, {
        error: 'INVALID_INPUT',
        message: 'Username and password are required.'
      });
      return;
    }

    const login = await loginUser({
      username: body.username,
      password: body.password
    });

    if (!login.ok) {
      writeJson(res, 401, {
        error: 'INVALID_CREDENTIALS',
        message: 'The username or password is incorrect.'
      });
      return;
    }

    const token = createSignedToken({ sub: login.user.id }, TOKEN_SECRET, TOKEN_TTL_SECONDS);
    writeJson(res, 200, { token, user: login.user });
    return;
  }

  if (method === 'GET' && pathname === '/api/auth/me') {
    await purgeExpiredUsers();
    const user = await getAuthenticatedUser(req);
    if (!user) {
      writeJson(res, 401, {
        error: 'UNAUTHORIZED',
        message: 'Please sign in to continue.'
      });
      return;
    }
    writeJson(res, 200, {
      user,
      retentionDays: Math.floor(USER_RETENTION_MS / (24 * 60 * 60 * 1000))
    });
    return;
  }

  if (method === 'POST' && pathname === '/api/progress/add-language') {
    await purgeExpiredUsers();
    const user = await getAuthenticatedUser(req);
    if (!user) {
      writeJson(res, 401, {
        error: 'UNAUTHORIZED',
        message: 'Please sign in to continue.'
      });
      return;
    }
    if (!(await enforceSaveLimitForRequest(req, res))) {
      return;
    }

    const body = await parseJsonBody(req);
    const language = ensureLanguage(body.language);
    if (!language) {
      writeJson(res, 400, {
        error: 'INVALID_LANGUAGE',
        message: `Language must be one of: ${SUPPORTED_LANGUAGES.join(', ')}.`
      });
      return;
    }

    const update = await addLanguageToUser(user.id, language);
    if (!update.ok) {
      writeJson(res, 404, {
        error: 'USER_NOT_FOUND',
        message: 'Your account could not be found.'
      });
      return;
    }
    writeJson(res, 200, { user: update.user });
    return;
  }

  if (method === 'POST' && pathname === '/api/progress/complete-exercise') {
    await purgeExpiredUsers();
    const user = await getAuthenticatedUser(req);
    if (!user) {
      writeJson(res, 401, {
        error: 'UNAUTHORIZED',
        message: 'Please sign in to continue.'
      });
      return;
    }
    if (!(await enforceSaveLimitForRequest(req, res))) {
      return;
    }

    const body = await parseJsonBody(req);
    const language = ensureLanguage(body.language);
    const score = Number(body.score);
    if (!language) {
      writeJson(res, 400, {
        error: 'INVALID_LANGUAGE',
        message: `Language must be one of: ${SUPPORTED_LANGUAGES.join(', ')}.`
      });
      return;
    }
    if (!Number.isFinite(score) || score < 0 || score > 100) {
      writeJson(res, 400, {
        error: 'INVALID_SCORE',
        message: 'Score must be a number between 0 and 100.'
      });
      return;
    }

    const result = await completeExerciseForUser(user.id, language, score);
    if (!result.ok) {
      writeJson(res, 404, {
        error: 'USER_NOT_FOUND',
        message: 'Your account could not be found.'
      });
      return;
    }

    writeJson(res, 200, {
      passedExercise: result.passedExercise,
      leveledUp: result.leveledUp,
      previousProgress: result.previousProgress,
      nextProgress: result.nextProgress,
      user: result.user
    });
    return;
  }

  if (method === 'GET' && pathname === '/api/progress/review') {
    await purgeExpiredUsers();
    const user = await getAuthenticatedUser(req);
    if (!user) {
      writeJson(res, 401, {
        error: 'UNAUTHORIZED',
        message: 'Please sign in to continue.'
      });
      return;
    }

    const language = ensureLanguage(requestUrl.searchParams.get('language'));
    if (!language) {
      writeJson(res, 400, {
        error: 'INVALID_LANGUAGE',
        message: `Language must be one of: ${SUPPORTED_LANGUAGES.join(', ')}.`
      });
      return;
    }

    const level = Math.max(
      COURSE_LEVEL_MIN,
      Math.floor(Number(user.courseProg?.[language]) || COURSE_LEVEL_MIN)
    );

    const content = await loadBootstrapContent();
    const reviewCards = content.phrases
      .filter((phrase) => Math.floor(Number(phrase.id)) === level)
      .map((phrase) => ({
        phrase: phrase.phrase,
        english: phrase.translations.ENGLISH || phrase.phrase,
        translation: phrase.translations[language] || '',
        level
      }));

    writeJson(res, 200, {
      language,
      level,
      reviewCards
    });
    return;
  }

  writeJson(res, 404, {
    error: 'NOT_FOUND',
    message: 'Endpoint not found.'
  });
}

if (TOKEN_SECRET === 'change-this-token-secret') {
  console.warn(
    '[lovelearninglangs-api] Using default token secret. Set LLL_TOKEN_SECRET before deploying.'
  );
}

await purgeExpiredUsers();

const server = createServer((req, res) => {
  handleRequest(req, res).catch((error) => {
    if (error.message === 'INVALID_JSON') {
      writeJson(res, 400, {
        error: 'INVALID_JSON',
        message: 'Request body must be valid JSON.'
      });
      return;
    }

    if (error.message === 'BODY_TOO_LARGE') {
      writeJson(res, 413, {
        error: 'PAYLOAD_TOO_LARGE',
        message: 'Request body is too large.'
      });
      return;
    }

    console.error('[lovelearninglangs-api] Unexpected error', error);
    writeJson(res, 500, {
      error: 'INTERNAL_ERROR',
      message: 'An unexpected error occurred.'
    });
  });
});

server.listen(SERVER_PORT, () => {
  console.log(`[lovelearninglangs-api] Listening on http://localhost:${SERVER_PORT}`);
});

