# Thomas Peterson Portfolio Site (React + GitHub Pages)

This repository hosts the React portfolio plus a LoveLearningLangs web migration:

- Frontend: React/Vite site (GitHub Pages friendly)
- Backend API: separate repository at `That-GuyTP.github.io-Backend-`

## Pages Included

- `Home`: Intro and background information.
- `Projects`: Includes a live `LoveLearningLangs Web Edition` route at `#/projects/lovelearninglangs`.
- `Documents`: A page for resumes, coursework notes, and other supporting documents.

## Local Development

1. Install dependencies:
   ```bash
   npm install
   ```
2. (Optional) Start backend from the dedicated backend repo:
   ```bash
   npm run api
   ```
3. Start the frontend dev server:
   ```bash
   npm run dev
   ```
4. Build production files:
   ```bash
   npm run build
   ```

For local API integration, run the backend from the separate backend repository and set:

```bash
VITE_LLL_API_BASE_URL=http://localhost:8080/api
```

## LoveLearningLangs API

The LoveLearningLangs backend code is owned by the backend repository:

- Path: `C:\Users\hdriv\Documents\Coding\GitHub\That-GuyTP.github.io-Backend-`
- Deployed host should expose `GET /api/health` and `GET /api/content/bootstrap`

## Deployment (GitHub Pages)

This repo includes `.github/workflows/deploy.yml` to publish the React frontend to GitHub Pages.

Because GitHub Pages is static, the backend must be deployed separately (Render/Railway/Fly/etc) from the backend repository.

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

Before setting the frontend variable, verify the backend host returns:

```bash
https://your-api-domain.example.com/api/health
```

Expected response includes:

```json
{"ok":true}
```

If `/health` works but `/api/health` returns `404`, the wrong backend service is deployed for LoveLearningLangs.

## Documents Folder

Add extra files under `public/docs/` and list them in the Documents page to make them available on the site.
