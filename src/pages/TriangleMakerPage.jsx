import { useState } from 'react';

export default function TriangleMakerPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/triangle-maker`;
  const sourceFiles = [
    {
      name: 'Lab06.java',
      url: `${sourceBasePath}/Lab06.java`
    }
  ];

  const [heightInput, setHeightInput] = useState('');
  const [rows, setRows] = useState([]);
  const [message, setMessage] = useState('');

  function buildTriangle(event) {
    event.preventDefault();

    const parsedHeight = Number.parseInt(heightInput, 10);

    if (!Number.isInteger(parsedHeight)) {
      setMessage('Enter a whole number for the triangle height.');
      setRows([]);
      return;
    }

    if (parsedHeight < 2) {
      setMessage('Choose a number bigger than 1 to build a triangle.');
      setRows([]);
      return;
    }

    const outputRows = [];

    for (let i = 1; i <= parsedHeight; i += 1) {
      outputRows.push('*'.repeat(i));
    }

    for (let i = parsedHeight - 1; i >= 1; i -= 1) {
      outputRows.push('*'.repeat(i));
    }

    setRows(outputRows);
    setMessage('');
  }

  function reset() {
    setHeightInput('');
    setRows([]);
    setMessage('');
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Triangle Maker</h2>
        <p>
          Interactive browser port of your Java triangle printer. Enter a height and generate the full
          up-and-down star pattern.
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
        <form className="program-form-grid" onSubmit={buildTriangle}>
          <label htmlFor="triangle-height">
            Triangle height
            <input
              id="triangle-height"
              type="number"
              min="2"
              step="1"
              value={heightInput}
              onChange={(event) => setHeightInput(event.target.value)}
              placeholder="Example: 5"
            />
          </label>

          <div className="program-actions">
            <button type="submit" className="run-query-button">
              Build
            </button>
            <button type="button" className="sortsorter-secondary-button" onClick={reset}>
              Reset
            </button>
          </div>
        </form>

        {message && <p className="status-line">{message}</p>}
      </article>

      <article className="project-card">
        <h3>Triangle Output</h3>
        <pre className="program-output triangle-output">
          {rows.length > 0 ? rows.join('\n') : 'Build a triangle to see the output.'}
        </pre>
      </article>
    </section>
  );
}
