const gamesAndPrograms = [
  {
    title: 'Single-Page Java App Migration',
    link: '',
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
          Use this section for game-focused demos and cool programs you deploy from backend-supported
          projects.
        </p>
      </div>

      <div className="project-grid">
        {gamesAndPrograms.map((program) => (
          <article key={program.title} className="project-card">
            <div className="project-head">
              <h3>{program.title}</h3>
              {program.link ? (
                <a href={program.link} target="_blank" rel="noreferrer">
                  Play
                </a>
              ) : (
                <span className="project-link-placeholder">Deploying Soon</span>
              )}
            </div>
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
