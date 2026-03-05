import { useEffect, useMemo, useState } from 'react';

const DEFAULT_USER = 'That-GuyTP';
const SORT_OPTIONS = [
  { value: 'stars', label: 'Most stars' },
  { value: 'forks', label: 'Most forks' },
  { value: 'updated', label: 'Recently updated' }
];

function formatDate(timestamp) {
  return new Date(timestamp).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric'
  });
}

export default function JavaProjectsPage() {
  const [usernameInput, setUsernameInput] = useState(DEFAULT_USER);
  const [keywordInput, setKeywordInput] = useState('');
  const [sortInput, setSortInput] = useState('updated');
  const [filters, setFilters] = useState({
    username: DEFAULT_USER,
    keyword: '',
    sort: 'updated'
  });
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const queryLabel = useMemo(() => {
    const parts = [`user:${filters.username || DEFAULT_USER}`, 'language:java'];
    if (filters.keyword.trim()) {
      parts.push(filters.keyword.trim());
    }
    return parts.join(' ');
  }, [filters.keyword, filters.username]);

  useEffect(() => {
    const controller = new AbortController();

    async function loadProjects() {
      const chosenUser = filters.username.trim() || DEFAULT_USER;
      const searchTerms = [`user:${chosenUser}`, 'language:java'];
      if (filters.keyword.trim()) {
        searchTerms.push(filters.keyword.trim());
      }

      const endpoint =
        'https://api.github.com/search/repositories?' +
        new URLSearchParams({
          q: searchTerms.join(' '),
          sort: filters.sort,
          order: 'desc',
          per_page: '12'
        });

      setLoading(true);
      setError('');

      try {
        const response = await fetch(endpoint, {
          signal: controller.signal,
          headers: {
            Accept: 'application/vnd.github+json'
          }
        });

        if (!response.ok) {
          if (response.status === 403) {
            throw new Error('GitHub API rate limit reached. Try again shortly.');
          }
          throw new Error(`GitHub API returned status ${response.status}.`);
        }

        const data = await response.json();
        setProjects(Array.isArray(data.items) ? data.items : []);
      } catch (fetchError) {
        if (fetchError.name !== 'AbortError') {
          setProjects([]);
          setError(fetchError.message || 'Could not load Java projects.');
        }
      } finally {
        setLoading(false);
      }
    }

    loadProjects();

    return () => controller.abort();
  }, [filters]);

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Interactive API Demo</p>
        <h2>Live Java project explorer</h2>
        <p>
          This page calls the GitHub API to list Java repositories. Update the inputs to demo different
          users, filters, and sorting behavior.
        </p>
      </div>

      <form
        className="control-bar"
        onSubmit={(event) => {
          event.preventDefault();
          setFilters({
            username: usernameInput.trim() || DEFAULT_USER,
            keyword: keywordInput,
            sort: sortInput
          });
        }}
      >
        <label>
          GitHub Username
          <input
            type="text"
            value={usernameInput}
            onChange={(event) => setUsernameInput(event.target.value)}
            placeholder="That-GuyTP"
          />
        </label>
        <label>
          Keyword Filter
          <input
            type="text"
            value={keywordInput}
            onChange={(event) => setKeywordInput(event.target.value)}
            placeholder="spring, game, parser..."
          />
        </label>
        <label>
          Sort
          <select value={sortInput} onChange={(event) => setSortInput(event.target.value)}>
            {SORT_OPTIONS.map((option) => (
              <option key={option.value} value={option.value}>
                {option.label}
              </option>
            ))}
          </select>
        </label>
        <button type="submit" className="run-query-button">
          Run Query
        </button>
      </form>

      <p className="query-label">Current query: {queryLabel}</p>

      {loading && <p className="status-line">Loading Java repositories...</p>}
      {error && <p className="status-line error">{error}</p>}
      {!loading && !error && projects.length === 0 && (
        <p className="status-line">No matching Java projects were found.</p>
      )}

      <div className="project-grid">
        {projects.map((project) => (
          <article key={project.id} className="project-card">
            <div className="project-head">
              <h3>{project.name}</h3>
              <a href={project.html_url} target="_blank" rel="noreferrer">
                Open Repo
              </a>
            </div>
            <p>{project.description || 'No description provided.'}</p>
            <div className="project-metrics">
              <span>Stars: {project.stargazers_count}</span>
              <span>Forks: {project.forks_count}</span>
              <span>Updated: {formatDate(project.updated_at)}</span>
            </div>
          </article>
        ))}
      </div>
    </section>
  );
}
