import { useState } from 'react';

const GRAVITY = 9.8;

function formatNumber(value) {
  if (!Number.isFinite(value)) {
    return '';
  }

  return value.toFixed(3).replace(/\.?0+$/, '');
}

export default function DistanceVelocityCalculatorPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/distance-velocity-calculator`;
  const sourceFiles = [
    {
      name: 'Lab02.java',
      url: `${sourceBasePath}/Lab02.java`
    }
  ];

  const [initialVelocity, setInitialVelocity] = useState('');
  const [timeSeconds, setTimeSeconds] = useState('');
  const [result, setResult] = useState(null);
  const [message, setMessage] = useState('');

  function calculate(event) {
    event.preventDefault();

    const u = Number(initialVelocity);
    const time = Number(timeSeconds);

    if (!Number.isFinite(u) || !Number.isFinite(time)) {
      setMessage('Enter numeric values for both initial velocity and time.');
      setResult(null);
      return;
    }

    if (time < 0) {
      setMessage('Time must be 0 or greater.');
      setResult(null);
      return;
    }

    const finalVelocity = u + GRAVITY * time;
    const distance = u * time + 0.5 * GRAVITY * time * time;

    setResult({ finalVelocity, distance });
    setMessage('');
  }

  function reset() {
    setInitialVelocity('');
    setTimeSeconds('');
    setResult(null);
    setMessage('');
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Distance &amp; Velocity Calculator</h2>
        <p>
          Browser version of your Java calculator from Lab02. Enter initial velocity and elapsed time to
          compute final velocity and distance traveled.
        </p>
      </div>

      <article className="project-card">
        <h3>Original Java Source</h3>
        <div className="sortsorter-source-links">
          {sourceFiles.map((file) => (
            <a key={file.name} href={file.url} target="_blank" rel="noreferrer">
              {file.name}
            </a>
          ))}
        </div>
      </article>

      <article className="program-panel">
        <form className="program-form-grid" onSubmit={calculate}>
          <label htmlFor="distance-initial-velocity">
            Initial velocity (m/s)
            <input
              id="distance-initial-velocity"
              type="number"
              step="any"
              value={initialVelocity}
              onChange={(event) => setInitialVelocity(event.target.value)}
              placeholder="Example: 12.5"
            />
          </label>

          <label htmlFor="distance-time">
            Time (seconds)
            <input
              id="distance-time"
              type="number"
              step="any"
              min="0"
              value={timeSeconds}
              onChange={(event) => setTimeSeconds(event.target.value)}
              placeholder="Example: 3"
            />
          </label>

          <div className="program-actions">
            <button type="submit" className="run-query-button">
              Calculate
            </button>
            <button type="button" className="sortsorter-secondary-button" onClick={reset}>
              Reset
            </button>
          </div>
        </form>

        {message && <p className="status-line">{message}</p>}
      </article>

      <div className="program-results-grid">
        <article className="project-card">
          <h3>Final Velocity</h3>
          <p className="program-output">
            {result ? `${formatNumber(result.finalVelocity)} m/s` : 'Run a calculation to view output.'}
          </p>
        </article>

        <article className="project-card">
          <h3>Distance Traveled</h3>
          <p className="program-output">
            {result ? `${formatNumber(result.distance)} meters` : 'Run a calculation to view output.'}
          </p>
        </article>
      </div>
    </section>
  );
}
