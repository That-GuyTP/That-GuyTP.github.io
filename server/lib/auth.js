import crypto from 'node:crypto';

function base64UrlEncode(value) {
  const asBuffer = Buffer.isBuffer(value) ? value : Buffer.from(value, 'utf8');
  return asBuffer
    .toString('base64')
    .replace(/\+/g, '-')
    .replace(/\//g, '_')
    .replace(/=+$/g, '');
}

function base64UrlDecode(value) {
  const normalized = value.replace(/-/g, '+').replace(/_/g, '/');
  const padded = normalized + '==='.slice((normalized.length + 3) % 4);
  return Buffer.from(padded, 'base64').toString('utf8');
}

function signTokenSection(value, secret) {
  return crypto.createHmac('sha256', secret).update(value).digest('base64url');
}

export function hashPassword(password, salt = crypto.randomBytes(16).toString('hex')) {
  const hash = crypto.pbkdf2Sync(password, salt, 200_000, 64, 'sha512').toString('hex');
  return { salt, hash };
}

export function verifyPassword(password, salt, expectedHash) {
  if (!password || !salt || !expectedHash) {
    return false;
  }

  const calculated = crypto.pbkdf2Sync(password, salt, 200_000, 64, 'sha512').toString('hex');
  const expectedBuffer = Buffer.from(expectedHash, 'hex');
  const calculatedBuffer = Buffer.from(calculated, 'hex');
  if (expectedBuffer.length !== calculatedBuffer.length) {
    return false;
  }
  return crypto.timingSafeEqual(expectedBuffer, calculatedBuffer);
}

export function createSignedToken(payload, secret, ttlSeconds) {
  const nowSeconds = Math.floor(Date.now() / 1000);
  const tokenPayload = {
    ...payload,
    iat: nowSeconds,
    exp: nowSeconds + ttlSeconds
  };

  const encodedHeader = base64UrlEncode(JSON.stringify({ alg: 'HS256', typ: 'JWT' }));
  const encodedPayload = base64UrlEncode(JSON.stringify(tokenPayload));
  const signingInput = `${encodedHeader}.${encodedPayload}`;
  const signature = signTokenSection(signingInput, secret);

  return `${signingInput}.${signature}`;
}

export function verifySignedToken(token, secret) {
  if (!token || typeof token !== 'string') {
    return null;
  }

  const parts = token.split('.');
  if (parts.length !== 3) {
    return null;
  }

  const [encodedHeader, encodedPayload, providedSignature] = parts;
  const signingInput = `${encodedHeader}.${encodedPayload}`;
  const expectedSignature = signTokenSection(signingInput, secret);
  const providedBuffer = Buffer.from(providedSignature);
  const expectedBuffer = Buffer.from(expectedSignature);

  if (providedBuffer.length !== expectedBuffer.length) {
    return null;
  }
  if (!crypto.timingSafeEqual(providedBuffer, expectedBuffer)) {
    return null;
  }

  try {
    const parsedPayload = JSON.parse(base64UrlDecode(encodedPayload));
    const nowSeconds = Math.floor(Date.now() / 1000);
    if (!parsedPayload.exp || nowSeconds >= parsedPayload.exp) {
      return null;
    }
    return parsedPayload;
  } catch {
    return null;
  }
}

