# Thomas Peterson Portfolio Site (React + GitHub Pages)

This repository hosts the React portfolio plus a LoveLearningLangs web migration:

- Frontend: React/Vite site (GitHub Pages friendly)
- Backend API: Java server in `java-backend/` for LoveLearningLangs persistence and auth

## Pages Included

- `Home`: Intro and background information.
- `Projects`: Includes a live `LoveLearningLangs Web Edition` route at `#/projects/lovelearninglangs`.
- `Documents`: A page for resumes, coursework notes, and other supporting documents.

## Local Development

1. Install dependencies:
   ```bash
   npm install
   ```
2. Start the backend API (terminal 1):
   ```bash
   npm run api
   ```
3. Start the frontend dev server (terminal 2):
   ```bash
   npm run dev
   ```
4. Build production files:
   ```bash
   npm run build
   ```

The Vite dev server proxies `/api` to `http://localhost:8787`.

## LoveLearningLangs API

Backend entrypoint:

- Java main class: `com.thatguytp.lovelearninglangs.api.Main`
- Source root: `java-backend/src/main/java`

Data files:
- `server/data/Words.json`
- `server/data/Phrases.json`
- `server/data/Users.json`
- `server/data/IpSaveHistory.json`

Key behavior:
- Password hashing with PBKDF2
- Signed auth tokens
- Save throttling per IP (`LLL_SAVE_LIMIT_MAX` in `LLL_SAVE_LIMIT_WINDOW_MS`)
- Inactive user cleanup after 7 days (configurable)
- Exercise generation, grading, and progression logic executed in Java backend

Environment variables:
- `LLL_API_PORT` (default `8787`)
- `LLL_TOKEN_SECRET` (set this in production)
- `LLL_ALLOWED_ORIGINS` (comma-separated, default `*`)
- `LLL_USER_RETENTION_MS` (default 7 days)
- `LLL_SAVE_LIMIT_MAX` (default `50`)
- `LLL_SAVE_LIMIT_WINDOW_MS` (default `3600000`, 1 hour)

## Deployment (GitHub Pages)

This repo includes `.github/workflows/deploy.yml` to publish the React frontend to GitHub Pages.

Because GitHub Pages is static, the LoveLearningLangs backend must be deployed separately (Render/Railway/Fly/etc).

To enable it:
1. In GitHub, open this repository's **Settings**.
2. Go to **Pages**.
3. Under **Build and deployment**, set **Source** to **GitHub Actions**.
4. Push to `main`; the workflow will build and deploy automatically.

## Frontend-to-Backend Link

For GitHub Pages production builds, set this as a repository variable:

1. Open **Settings** in this repository.
2. Go to **Secrets and variables** -> **Actions** -> **Variables**.
3. Click **New repository variable**.
4. Name: `VITE_LLL_API_BASE_URL`
5. Value: `https://your-api-domain.example.com/api`

The deploy workflow reads this variable during `npm run build` and will fail if it is missing, so a broken frontend config is not published.

For local production-style testing, you can also create `.env.production` with the same key/value.

## Legacy Node API

The previous Node backend is still in `server/index.js` for reference.

- Run it manually with `npm run api:node`
- Default `npm run api` now runs the Java backend

## Documents Folder

Add extra files under `public/docs/` and list them in the Documents page to make them available on the site.
