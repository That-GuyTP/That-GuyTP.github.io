import { mkdir, readFile, rename, writeFile } from 'node:fs/promises';
import path from 'node:path';

const writeQueueByFile = new Map();

async function ensureParentDirectory(filePath) {
  await mkdir(path.dirname(filePath), { recursive: true });
}

export async function readJsonFile(filePath, fallbackValue) {
  try {
    const raw = await readFile(filePath, 'utf8');
    if (!raw.trim()) {
      return fallbackValue;
    }
    return JSON.parse(raw.replace(/^\uFEFF/, ''));
  } catch (error) {
    if (error.code === 'ENOENT') {
      return fallbackValue;
    }
    throw error;
  }
}

export async function writeJsonFile(filePath, value) {
  const previousWrite = writeQueueByFile.get(filePath) ?? Promise.resolve();
  const nextWrite = previousWrite
    .catch(() => undefined)
    .then(async () => {
      await ensureParentDirectory(filePath);
      const tempFilePath = `${filePath}.tmp-${process.pid}-${Date.now()}`;
      const payload = `${JSON.stringify(value, null, 2)}\n`;
      await writeFile(tempFilePath, payload, 'utf8');
      await rename(tempFilePath, filePath);
    });

  writeQueueByFile.set(filePath, nextWrite);
  try {
    await nextWrite;
  } finally {
    if (writeQueueByFile.get(filePath) === nextWrite) {
      writeQueueByFile.delete(filePath);
    }
  }
}
