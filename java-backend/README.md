# LoveLearningLangs Java API

## Run

From repo root:

```bash
npm run api
```

This compiles Java sources and starts:

- `com.thatguytp.lovelearninglangs.api.Main`
- default URL: `http://localhost:8787`

## API Summary

- `GET /api/health`
- `GET /api/content/bootstrap`
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`
- `POST /api/progress/add-language`
- `POST /api/progress/complete-exercise`
- `GET /api/progress/review`
- `POST /api/exercise/start`
- `POST /api/exercise/submit`

Exercise generation and grading happen on the backend in Java.
