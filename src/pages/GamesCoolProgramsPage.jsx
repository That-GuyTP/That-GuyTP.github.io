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
    title: 'Distance & Velocity Calculator',
    to: '/games-cool-programs/distance-velocity-calculator',
    language: 'Java',
    description:
      'Calculate final velocity and distance traveled from an initial velocity and elapsed time.',
    tags: ['Playable Now', 'Physics Utility', 'Formula Calculator']
  },
  {
    title: 'Triangle Maker',
    to: '/games-cool-programs/triangle-maker',
    language: 'Java',
    description:
      'Generate an up-and-down star triangle by entering a height, mirroring your Java loop logic.',
    tags: ['Playable Now', 'Pattern Builder', 'Loop Practice']
  },
  {
    title: 'Rock Paper Scissors',
    to: '/games-cool-programs/rock-paper-scissors',
    language: 'Java',
    description:
      'Best-of-three Rock Paper Scissors with match history and the same scoring behavior as your Java version.',
    tags: ['Playable Now', 'Game', 'Randomized']
  },
  {
    title: 'Morning Routine Adventure',
    to: '/games-cool-programs/morning-routine-adventure',
    language: 'Java',
    description:
      'Choose-your-own-adventure recreation with branching decisions and multiple endings to discover.',
    tags: ['Playable Now', 'Story Branches', 'Interactive']
  }
];

export default function GamesCoolProgramsPage() {
  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Games &amp; Cool Programs</p>
        <h2>Playable and interactive program ideas</h2>
        <p>
          This page includes interactive Java program ports from your backend app collection so each one
          can be played directly in the browser.
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
