# Thomas Peterson Portfolio Site (React + GitHub Pages)

This repository hosts a React frontend for your personal coding portfolio site.

## Pages Included

- `Home`: Intro and background information.
- `Java Projects`: Interactive page that calls the GitHub API to showcase Java repositories.
- `Documents`: A page for resumes, coursework notes, and other supporting documents.

## Local Development

1. Install dependencies:
   ```bash
   npm install
   ```
2. Start the dev server:
   ```bash
   npm run dev
   ```
3. Build production files:
   ```bash
   npm run build
   ```

## Deployment (GitHub Pages)

This repo includes `.github/workflows/deploy.yml` to publish the built site to GitHub Pages using GitHub Actions.

To enable it:
1. In GitHub, open this repository's **Settings**.
2. Go to **Pages**.
3. Under **Build and deployment**, set **Source** to **GitHub Actions**.
4. Push to `main`; the workflow will build and deploy automatically.

## Documents Folder

Add extra files under `public/docs/` and list them in the Documents page to make them available on the site.
