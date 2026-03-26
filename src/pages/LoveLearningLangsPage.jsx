import { useEffect, useMemo, useState } from 'react';
import lllLogo from '../images/lovelearninglangs/LLL_Logo.png';
import backIcon from '../images/lovelearninglangs/back_image.png';
import profileIcon from '../images/lovelearninglangs/profileImage.png';
import englishFlag from '../images/lovelearninglangs/language_flags/english.png';
import spanishFlag from '../images/lovelearninglangs/language_flags/spanish.png';
import germanFlag from '../images/lovelearninglangs/language_flags/germany.png';
import defaultFlag from '../images/lovelearninglangs/language_flags/default.png';

const TOKEN_STORAGE_KEY = 'lovelearninglangs_java_token_v1';
const API_BASE_URL = (import.meta.env.VITE_LLL_API_BASE_URL || '/api').replace(/\/$/, '');

const LANGUAGE_META = {
  ENGLISH: { label: 'English', icon: englishFlag },
  SPANISH: { label: 'Spanish', icon: spanishFlag },
  GERMAN: { label: 'German', icon: germanFlag }
};

async function apiRequest(path, { method = 'GET', body, token } = {}) {
  const headers = {
    Accept: 'application/json'
  };
  if (body) {
    headers['Content-Type'] = 'application/json';
  }
  if (token) {
    headers.Authorization = `Bearer ${token}`;
  }

  const response = await fetch(`${API_BASE_URL}${path}`, {
    method,
    headers,
    body: body ? JSON.stringify(body) : undefined
  });

  const payload = await response.json().catch(() => ({}));
  if (!response.ok) {
    const error = new Error(payload.message || payload.error || 'Request failed.');
    error.status = response.status;
    error.payload = payload;
    throw error;
  }

  return payload;
}

function initialExerciseState() {
  return {
    exerciseId: '',
    started: false,
    finished: false,
    questions: [],
    answers: {},
    currentIndex: 0,
    result: null,
    isSavingResult: false
  };
}

function getLanguageProgress(user, language) {
  if (!user?.courseProg) {
    return 1;
  }
  const progress = Number(user.courseProg[language]);
  if (!Number.isFinite(progress) || progress < 1) {
    return 1;
  }
  return progress;
}

function getLanguageMeta(language) {
  return LANGUAGE_META[language] ?? { label: language, icon: defaultFlag };
}

function getQuestionTypeLabel(question) {
  if (!question) {
    return '';
  }
  if (question.type === 'true_false') {
    return 'True or False';
  }
  if (question.type === 'fill_blank') {
    return 'Fill In the Blank';
  }
  return 'Multiple Choice';
}

function getQuestionPrompt(question) {
  if (!question?.prompt) {
    return '';
  }
  if (question.type === 'true_false') {
    return question.prompt.replace(/^true or false:\s*/i, '').trim();
  }
  if (question.type === 'fill_blank') {
    return question.prompt.replace(/^fill in the blank:\s*/i, '').trim();
  }
  return question.prompt;
}

export default function LoveLearningLangsPage({ embedded = false }) {
  const [bootstrap, setBootstrap] = useState(null);
  const [bootstrapLoading, setBootstrapLoading] = useState(true);
  const [bootstrapError, setBootstrapError] = useState('');

  const [token, setToken] = useState(() => window.localStorage.getItem(TOKEN_STORAGE_KEY) ?? '');
  const [sessionLoading, setSessionLoading] = useState(false);
  const [user, setUser] = useState(null);

  const [authScreen, setAuthScreen] = useState('startup');
  const [loginForm, setLoginForm] = useState({ username: '', password: '' });
  const [registerForm, setRegisterForm] = useState({
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: ''
  });

  const [selectedLanguage, setSelectedLanguage] = useState('SPANISH');
  const [status, setStatus] = useState({ tone: '', text: '' });
  const [authLoading, setAuthLoading] = useState(false);

  const [exercise, setExercise] = useState(initialExerciseState);
  const [reviewCards, setReviewCards] = useState([]);
  const [reviewLoading, setReviewLoading] = useState(false);
  const [learnIndex, setLearnIndex] = useState(0);
  const [appScreen, setAppScreen] = useState('home');

  const supportedLanguages = bootstrap?.languages ?? [];
  const userHasSelectedLanguage = useMemo(
    () => Boolean(user?.courseProg && Number(user.courseProg[selectedLanguage]) >= 1),
    [user, selectedLanguage]
  );
  const userLanguages = useMemo(
    () => supportedLanguages.filter((language) => Number(user?.courseProg?.[language]) >= 1),
    [supportedLanguages, user]
  );

  const selectedLanguageProgress = getLanguageProgress(user, selectedLanguage);
  const currentQuestion = exercise.questions[exercise.currentIndex] ?? null;
  const selectedLanguageMeta = getLanguageMeta(selectedLanguage);
  const welcomeName = user?.firstName?.trim() || user?.username || 'Friend';
  const learnCard = reviewCards[learnIndex] ?? null;

  useEffect(() => {
    window.localStorage.removeItem('lovelearninglangs_token');
  }, []);

  useEffect(() => {
    let active = true;
    async function loadBootstrap() {
      setBootstrapLoading(true);
      setBootstrapError('');
      try {
        const data = await apiRequest('/content/bootstrap');
        if (!active) {
          return;
        }
        setBootstrap(data);
        setSelectedLanguage((previous) => {
          if (previous && data.languages.includes(previous)) {
            return previous;
          }
          return data.languages[0] ?? 'SPANISH';
        });
      } catch (error) {
        if (!active) {
          return;
        }
        setBootstrapError(
          `${error.message} The API is currently unreachable. If this is production, set VITE_LLL_API_BASE_URL to your deployed backend URL.`
        );
      } finally {
        if (active) {
          setBootstrapLoading(false);
        }
      }
    }

    loadBootstrap();
    return () => {
      active = false;
    };
  }, []);

  useEffect(() => {
    let active = true;

    async function hydrateSession() {
      if (!token) {
        setUser(null);
        return;
      }

      setSessionLoading(true);
      try {
        const payload = await apiRequest('/auth/me', { token });
        if (!active) {
          return;
        }
        setUser(payload.user);
      } catch {
        if (!active) {
          return;
        }
        window.localStorage.removeItem(TOKEN_STORAGE_KEY);
        setToken('');
        setUser(null);
      } finally {
        if (active) {
          setSessionLoading(false);
        }
      }
    }

    hydrateSession();
    return () => {
      active = false;
    };
  }, [token]);

  useEffect(() => {
    if (!user || !token || !userHasSelectedLanguage) {
      setReviewCards([]);
      return;
    }

    let active = true;
    async function fetchReviewCards() {
      setReviewLoading(true);
      try {
        const payload = await apiRequest(
          `/progress/review?language=${encodeURIComponent(selectedLanguage)}`,
          { token }
        );
        if (!active) {
          return;
        }
        setReviewCards(payload.reviewCards ?? []);
      } catch (error) {
        if (active) {
          setStatus({
            tone: 'warning',
            text: `Could not load review cards: ${error.message}`
          });
        }
      } finally {
        if (active) {
          setReviewLoading(false);
        }
      }
    }

    fetchReviewCards();
    return () => {
      active = false;
    };
  }, [user, token, selectedLanguage, userHasSelectedLanguage]);

  useEffect(() => {
    setLearnIndex(0);
  }, [selectedLanguage, reviewCards.length]);

  function onAuthSuccess(payload) {
    window.localStorage.setItem(TOKEN_STORAGE_KEY, payload.token);
    setToken(payload.token);
    setUser(payload.user);
    setExercise(initialExerciseState());
    setAuthScreen('startup');
    setAppScreen('home');
    setStatus({
      tone: 'success',
      text: `Welcome ${payload.user.firstName || payload.user.username}.`
    });
  }

  async function handleRegister(event) {
    event.preventDefault();
    setAuthLoading(true);
    setStatus({ tone: '', text: '' });
    try {
      const payload = await apiRequest('/auth/register', {
        method: 'POST',
        body: registerForm
      });
      onAuthSuccess(payload);
      setRegisterForm({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: ''
      });
    } catch (error) {
      setStatus({ tone: 'error', text: error.message });
    } finally {
      setAuthLoading(false);
    }
  }

  async function handleLogin(event) {
    event.preventDefault();
    setAuthLoading(true);
    setStatus({ tone: '', text: '' });
    try {
      const payload = await apiRequest('/auth/login', {
        method: 'POST',
        body: loginForm
      });
      onAuthSuccess(payload);
      setLoginForm({ username: '', password: '' });
    } catch (error) {
      setStatus({ tone: 'error', text: error.message });
    } finally {
      setAuthLoading(false);
    }
  }

  function handleLogout() {
    window.localStorage.removeItem(TOKEN_STORAGE_KEY);
    setToken('');
    setUser(null);
    setExercise(initialExerciseState());
    setAuthScreen('startup');
    setAppScreen('home');
    setStatus({ tone: '', text: '' });
  }

  async function handleAddLanguage(languageOverride) {
    const language = languageOverride ?? selectedLanguage;
    setSelectedLanguage(language);

    if (!token || !user) {
      return;
    }

    if (Number(user.courseProg?.[language]) >= 1) {
      setAppScreen('level');
      setStatus({ tone: '', text: '' });
      return;
    }

    setStatus({ tone: '', text: '' });
    try {
      const payload = await apiRequest('/progress/add-language', {
        method: 'POST',
        token,
        body: { language }
      });
      setUser(payload.user);
      setAppScreen('level');
      setStatus({
        tone: 'success',
        text: `${getLanguageMeta(language).label} is active at level ${
          payload.user.courseProg[language]
        }.`
      });
      setExercise(initialExerciseState());
    } catch (error) {
      setStatus({ tone: 'error', text: error.message });
    }
  }

  async function startExercise() {
    if (!bootstrap || !userHasSelectedLanguage || !token) {
      setStatus({
        tone: 'warning',
        text: `Add ${selectedLanguageMeta.label} before starting an exercise.`
      });
      return;
    }

    try {
      const payload = await apiRequest('/exercise/start', {
        method: 'POST',
        token,
        body: {
          language: selectedLanguage,
          count: 10
        }
      });
      const questions = payload.questions ?? [];
      if (questions.length === 0) {
        setStatus({
          tone: 'error',
          text: 'Not enough language data is available to generate this exercise.'
        });
        return;
      }

      setExercise({
        exerciseId: payload.exerciseId,
        started: true,
        finished: false,
        questions,
        answers: {},
        currentIndex: 0,
        result: null,
        isSavingResult: false
      });
      setAppScreen('exercise');
      setStatus({ tone: '', text: '' });
    } catch (error) {
      setStatus({
        tone: 'error',
        text: error.message
      });
    }
  }

  function setAnswerForCurrentQuestion(value) {
    if (!currentQuestion) {
      return;
    }
    setExercise((previous) => ({
      ...previous,
      answers: {
        ...previous.answers,
        [currentQuestion.id]: value
      }
    }));
  }

  async function completeExercise(answersToSubmit = exercise.answers) {
    if (!token || !exercise.exerciseId) {
      return;
    }

    setExercise((previous) => ({
      ...previous,
      answers: answersToSubmit,
      finished: true,
      result: null,
      isSavingResult: true
    }));
    setAppScreen('results');

    try {
      const payload = await apiRequest('/exercise/submit', {
        method: 'POST',
        token,
        body: {
          exerciseId: exercise.exerciseId,
          answers: answersToSubmit
        }
      });

      setUser(payload.user);
      setExercise((previous) => ({
        ...previous,
        result: {
          correct: Number(payload.correct ?? 0),
          total: Number(payload.total ?? 0),
          score: Number(payload.score ?? 0)
        }
      }));
      setStatus({
        tone: payload.leveledUp ? 'success' : 'warning',
        text: payload.leveledUp
          ? `Great run. You advanced to level ${payload.nextProgress} in ${selectedLanguageMeta.label}.`
          : 'Exercise saved. Score 60%+ is required to level up.'
      });
    } catch (error) {
      setStatus({
        tone: 'error',
        text: `Exercise completed locally, but save failed: ${error.message}`
      });
    } finally {
      setExercise((previous) => ({
        ...previous,
        isSavingResult: false
      }));
    }
  }

  async function moveToNextQuestion(answerOverride) {
    if (!currentQuestion) {
      return;
    }

    const answersToSubmit =
      answerOverride === undefined
        ? exercise.answers
        : {
            ...exercise.answers,
            [currentQuestion.id]: answerOverride
          };
    const answer = answersToSubmit[currentQuestion.id];
    if (!String(answer ?? '').trim()) {
      setStatus({ tone: 'warning', text: 'Please answer before moving on.' });
      return;
    }

    if (exercise.currentIndex >= exercise.questions.length - 1) {
      await completeExercise(answersToSubmit);
      return;
    }

    setExercise((previous) => ({
      ...previous,
      answers: answersToSubmit,
      currentIndex: previous.currentIndex + 1
    }));
    setStatus({ tone: '', text: '' });
  }

  function resetExercise() {
    setExercise(initialExerciseState());
    setAppScreen('level');
    setStatus({ tone: '', text: '' });
  }

  function triggerSpeech(text) {
    if (!('speechSynthesis' in window)) {
      setStatus({
        tone: 'warning',
        text: 'Speech playback is unavailable in this browser.'
      });
      return;
    }
    const output = String(text ?? '').trim();
    if (!output) {
      setStatus({
        tone: 'warning',
        text: 'No phrase available for narration yet.'
      });
      return;
    }
    window.speechSynthesis.cancel();
    window.speechSynthesis.speak(new SpeechSynthesisUtterance(output));
  }

  function handleTestNarrator() {
    const fallbackPhrase = bootstrap?.phrases?.[0];
    const sample =
      learnCard?.translation ||
      fallbackPhrase?.translations?.[selectedLanguage] ||
      fallbackPhrase?.phrase ||
      'Hola';
    triggerSpeech(sample);
  }

  function playLearnCard() {
    triggerSpeech(learnCard?.translation || learnCard?.english || '');
  }

  function nextLearnCard() {
    if (reviewCards.length === 0) {
      return;
    }
    setLearnIndex((previous) => (previous + 1) % reviewCards.length);
  }

  function openLanguageCourse(language) {
    setSelectedLanguage(language);
    setAppScreen('level');
    setStatus({ tone: '', text: '' });
  }

  function startSelectedLevel(level) {
    if (selectedLanguageProgress + 0.001 < level) {
      setStatus({
        tone: 'warning',
        text: `Reach level ${level} in ${selectedLanguageMeta.label} first.`
      });
      return;
    }
    void startExercise();
  }

  function openProfilePlaceholder() {
    setStatus({
      tone: 'warning',
      text: 'Profile settings are not available in the web demo yet.'
    });
  }

  function renderTopBar({ showProfile = false, onBack }) {
    return (
      <div className={`lll-top-bar ${showProfile ? 'with-profile' : ''}`}>
        <button type="button" className="lll-icon-button" onClick={onBack} aria-label="Go back">
          <img src={backIcon} alt="" aria-hidden="true" />
        </button>
        <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo" />
        {showProfile ? (
          <button
            type="button"
            className="lll-icon-button"
            onClick={openProfilePlaceholder}
            aria-label="Profile"
          >
            <img src={profileIcon} alt="" aria-hidden="true" />
          </button>
        ) : (
          <span className="lll-top-bar-spacer" aria-hidden="true" />
        )}
      </div>
    );
  }

  function renderStartup() {
    return (
      <div className="lll-screen lll-startup-screen">
        <div className="lll-logo-stage">
          <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo large" />
          <p className="lll-brand-title">Love Learning Langs</p>
        </div>
        <div className="lll-vertical-actions">
          <button type="button" className="lll-red-button" onClick={() => setAuthScreen('login')}>
            Login
          </button>
          <button type="button" className="lll-red-button" onClick={() => setAuthScreen('register')}>
            Register
          </button>
          <button
            type="button"
            className="lll-red-button"
            onClick={handleTestNarrator}
            disabled={bootstrapLoading || Boolean(bootstrapError)}
          >
            Test Narrator
          </button>
        </div>
      </div>
    );
  }

  function renderLogin() {
    return (
      <div className="lll-screen lll-auth-screen">
        <div className="lll-logo-stage compact">
          <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo" />
          <p className="lll-form-title">Login Info</p>
        </div>
        <form className="lll-form-stack" onSubmit={handleLogin}>
          <input
            id="lll-login-user"
            className="lll-text-input"
            placeholder="Username"
            value={loginForm.username}
            onChange={(event) =>
              setLoginForm((previous) => ({ ...previous, username: event.target.value }))
            }
            autoComplete="username"
          />
          <input
            id="lll-login-password"
            className="lll-text-input"
            placeholder="Password"
            type="password"
            value={loginForm.password}
            onChange={(event) =>
              setLoginForm((previous) => ({ ...previous, password: event.target.value }))
            }
            autoComplete="current-password"
          />
          <button type="submit" className="lll-red-button" disabled={authLoading || bootstrapLoading}>
            {authLoading ? 'Submitting...' : 'Submit'}
          </button>
        </form>
        <button type="button" className="lll-red-button" onClick={() => setAuthScreen('startup')}>
          Back
        </button>
      </div>
    );
  }

  function renderRegister() {
    return (
      <div className="lll-screen lll-auth-screen">
        <div className="lll-logo-stage compact">
          <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo" />
          <p className="lll-form-title">Sign Up</p>
        </div>
        <form className="lll-form-stack" onSubmit={handleRegister}>
          <input
            id="lll-register-user"
            className="lll-text-input"
            placeholder="Username"
            value={registerForm.username}
            onChange={(event) =>
              setRegisterForm((previous) => ({ ...previous, username: event.target.value }))
            }
            autoComplete="username"
          />
          <input
            id="lll-register-password"
            className="lll-text-input"
            placeholder="Password"
            type="password"
            value={registerForm.password}
            onChange={(event) =>
              setRegisterForm((previous) => ({ ...previous, password: event.target.value }))
            }
            autoComplete="new-password"
          />
          <input
            id="lll-register-first"
            className="lll-text-input"
            placeholder="First Name"
            value={registerForm.firstName}
            onChange={(event) =>
              setRegisterForm((previous) => ({ ...previous, firstName: event.target.value }))
            }
            autoComplete="given-name"
          />
          <input
            id="lll-register-last"
            className="lll-text-input"
            placeholder="Last Name"
            value={registerForm.lastName}
            onChange={(event) =>
              setRegisterForm((previous) => ({ ...previous, lastName: event.target.value }))
            }
            autoComplete="family-name"
          />
          <input
            id="lll-register-email"
            className="lll-text-input"
            placeholder="Email"
            value={registerForm.email}
            onChange={(event) =>
              setRegisterForm((previous) => ({ ...previous, email: event.target.value }))
            }
            autoComplete="email"
          />
          <button type="submit" className="lll-red-button" disabled={authLoading || bootstrapLoading}>
            {authLoading ? 'Submitting...' : 'Submit'}
          </button>
        </form>
        <button type="button" className="lll-red-button" onClick={() => setAuthScreen('startup')}>
          Back
        </button>
      </div>
    );
  }

  function renderHome() {
    return (
      <div className="lll-screen">
        {renderTopBar({ showProfile: true, onBack: handleLogout })}
        <div className="lll-home-block">
          <h2 className="lll-home-title">
            Lets get Started,
            <br />
            {welcomeName}
          </h2>
          <div className="lll-home-actions">
            <button type="button" className="lll-red-button lll-main-action" onClick={() => setAppScreen('language')}>
              Add Language
            </button>
            {userLanguages.map((language) => (
              <button
                key={language}
                type="button"
                className="lll-red-button lll-main-action lll-home-language-button"
                onClick={() => openLanguageCourse(language)}
              >
                {getLanguageMeta(language).label}
              </button>
            ))}
            {userLanguages.length === 0 && <p className="lll-home-empty">No languages added yet.</p>}
          </div>
        </div>
      </div>
    );
  }

  function renderLanguageSelection() {
    return (
      <div className="lll-screen lll-language-screen">
        {renderTopBar({ showProfile: true, onBack: () => setAppScreen('home') })}
        <h2 className="lll-screen-title">Add Language</h2>
        <div className="lll-language-stack">
          {supportedLanguages.map((language) => {
            const meta = getLanguageMeta(language);
            const isCurrent = selectedLanguage === language;
            return (
              <button
                key={language}
                type="button"
                className={`lll-red-button lll-language-button ${isCurrent ? 'active' : ''}`}
                onClick={() => void handleAddLanguage(language)}
              >
                <img src={meta.icon} alt="" aria-hidden="true" />
                <span>{meta.label.toUpperCase()}</span>
              </button>
            );
          })}
        </div>
      </div>
    );
  }

  function renderLevelSelect() {
    const progressValue = Math.max(1, Math.floor(selectedLanguageProgress));
    return (
      <div className="lll-screen">
        {renderTopBar({ onBack: () => setAppScreen('home') })}
        <div className="lll-level-stack">
          <button type="button" className="lll-red-button lll-level-pill" onClick={() => setAppScreen('learn')}>
            Learn
          </button>
          {[1, 2, 3].map((level) => (
            <button
              key={level}
              type="button"
              className={`lll-red-button lll-level-pill ${progressValue >= level ? '' : 'locked'}`}
              onClick={() => startSelectedLevel(level)}
            >
              Level {level}
            </button>
          ))}
        </div>
      </div>
    );
  }

  function renderLearn() {
    return (
      <div className="lll-screen">
        {renderTopBar({ onBack: () => setAppScreen('level') })}
        <div className="lll-learn-stack">
          <p className="lll-learn-text">
            {reviewLoading && 'Loading review phrase...'}
            {!reviewLoading && !learnCard && 'No review phrase found for this level yet.'}
            {!reviewLoading &&
              learnCard &&
              `"${learnCard.english}" is pronounced, "${learnCard.translation}"`}
          </p>
          <button
            type="button"
            className="lll-red-button"
            onClick={playLearnCard}
            disabled={!learnCard || reviewLoading}
          >
            Play Sound
          </button>
          <button
            type="button"
            className="lll-red-button"
            onClick={nextLearnCard}
            disabled={reviewCards.length === 0}
          >
            Next
          </button>
        </div>
      </div>
    );
  }

  function renderExercise() {
    return (
      <div className="lll-screen">
        {renderTopBar({ onBack: resetExercise })}
        <div className="lll-question-stack">
          <p className="lll-level-label">Level {selectedLanguageProgress.toFixed(1)}</p>
          <div className="lll-question-divider" />
          <p className="lll-question-type">{getQuestionTypeLabel(currentQuestion)}</p>
          <p className="lll-question-prompt">
            Q{exercise.currentIndex + 1}: {getQuestionPrompt(currentQuestion)}
          </p>
          {(currentQuestion?.type === 'multiple_choice' || currentQuestion?.type === 'true_false') && (
            <div className="lll-answer-stack">
              {(currentQuestion.choices ?? []).map((choice) => {
                const selected = exercise.answers[currentQuestion.id] === choice;
                return (
                  <button
                    type="button"
                    key={choice}
                    className={`lll-red-button ${selected ? 'selected' : ''}`}
                    onClick={() => void moveToNextQuestion(choice)}
                  >
                    {choice}
                  </button>
                );
              })}
            </div>
          )}
          {currentQuestion?.type === 'fill_blank' && (
            <div className="lll-answer-stack">
              <input
                id="lll-fill-answer"
                className="lll-text-input"
                value={exercise.answers[currentQuestion.id] ?? ''}
                onChange={(event) => setAnswerForCurrentQuestion(event.target.value)}
                placeholder="Type your answer"
              />
            </div>
          )}
          {currentQuestion?.type === 'fill_blank' && (
            <button type="button" className="lll-red-button" onClick={() => void moveToNextQuestion()}>
              {exercise.currentIndex === exercise.questions.length - 1 ? 'Finish' : 'Next'}
            </button>
          )}
        </div>
      </div>
    );
  }

  function renderResults() {
    return (
      <div className="lll-screen">
        {renderTopBar({ onBack: resetExercise })}
        <div className="lll-results-stack">
          <p className="lll-question-type">Exercise Complete</p>
          {exercise.result ? (
            <p className="lll-results-text">
              Score: {exercise.result.correct}/{exercise.result.total} ({exercise.result.score.toFixed(1)}%)
            </p>
          ) : (
            <p className="lll-results-text">Calculating score...</p>
          )}
          <p className="lll-results-text">
            {exercise.isSavingResult ? 'Saving progress...' : `${selectedLanguageMeta.label} progress saved.`}
          </p>
          <button type="button" className="lll-red-button" onClick={() => void startExercise()}>
            Start Again
          </button>
          <button type="button" className="lll-red-button" onClick={resetExercise}>
            Back
          </button>
        </div>
      </div>
    );
  }

  function renderScreen() {
    if (bootstrapLoading && !bootstrap) {
      return (
        <div className="lll-screen lll-loading-screen">
          <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo large" />
          <p className="lll-form-title">Loading...</p>
        </div>
      );
    }

    if (bootstrapError) {
      return (
        <div className="lll-screen lll-loading-screen">
          <img src={lllLogo} alt="LoveLearningLangs logo" className="lll-main-logo large" />
          <p className="lll-form-title">Backend Connection Error</p>
          <p className="lll-error-copy">{bootstrapError}</p>
        </div>
      );
    }

    if (!user) {
      if (authScreen === 'login') {
        return renderLogin();
      }
      if (authScreen === 'register') {
        return renderRegister();
      }
      return renderStartup();
    }

    if (appScreen === 'language') {
      return renderLanguageSelection();
    }
    if (appScreen === 'level') {
      return renderLevelSelect();
    }
    if (appScreen === 'learn') {
      return renderLearn();
    }
    if (appScreen === 'exercise' && exercise.started && currentQuestion && !exercise.finished) {
      return renderExercise();
    }
    if (appScreen === 'results' && exercise.started) {
      return renderResults();
    }
    return renderHome();
  }

  return (
    <section className={`page-panel lll-theme-shell ${embedded ? 'lll-embedded-shell' : ''}`}>
      <div className="lll-app-stage">
        <div className="lll-mobile-frame">
          {renderScreen()}
          {status.text && <p className={`lll-message ${status.tone}`}>{status.text}</p>}
          {sessionLoading && <p className="lll-message">Restoring session...</p>}
        </div>
      </div>
    </section>
  );
}
