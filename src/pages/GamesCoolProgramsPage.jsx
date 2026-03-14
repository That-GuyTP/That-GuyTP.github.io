import { Link } from 'react-router-dom';

const gamesAndPrograms = [
  {
    title: 'SortSorter',
    to: '/games-cool-programs/sortsorter',
    language: 'Java',
    description:
      'Playable module ported from your Java SortSorter backend files. Add words and sort them by "sort" match count.',
    tags: ['Playable Now', 'Original Source Linked', 'Algorithm']
  },
  {
    title: 'Single-Page Java App Migration',
    link: '',
    language: 'Java',
    description:
      'Move legacy Java app concepts behind a backend service so each one can be played in the browser.',
    tags: ['Java to Web', 'Backend + Frontend']
  },
  {
    title: 'Arcade Prototype',
    link: '',
    description:
      'Browser-playable mini-game collection for quick demos of controls, scoring, and state handling.',
    tags: ['Game Build', 'Deploying Soon']
  },
  {
    title: 'Algorithm Visualizer',
    link: '',
    description:
      'Interactive visual tool for exploring data structures and algorithm steps with live controls.',
    tags: ['Educational Tool', 'Planned']
  },
  {
    title: 'Utility Sandbox',
    link: '',
    description:
      'Collection of cool, practical mini-programs packaged into one interface for experimentation.',
    tags: ['Program Suite', 'In Progress']
  }
];

export default function GamesCoolProgramsPage() {
  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Games &amp; Cool Programs</p>
        <h2>Playable and interactive program ideas</h2>
        <p>
          This page is where were you can interact with applications I've created over the years. They are hosted on a backend, but are currently till being worked on.
        </p>
      </div>

      <div className="project-grid">
        {gamesAndPrograms.map((program) => (
          <article key={program.title} className="project-card">
            <div className="project-head">
              <h3>{program.title}</h3>
              {program.to ? (
                <Link to={program.to}>Play</Link>
              ) : program.link ? (
                <a href={program.link} target="_blank" rel="noreferrer">
                  Play
                </a>
              ) : (
                <span className="project-link-placeholder">Deploying Soon</span>
              )}
            </div>
            {program.language && (
              <p className="project-language">
                Language: <span>{program.language}</span>
              </p>
            )}
            <p>{program.description}</p>
            <div className="project-metrics">
              {program.tags.map((tag) => (
                <span key={tag}>{tag}</span>
              ))}
            </div>
          </article>
        ))}
      </div>
    </section>
  );
}
