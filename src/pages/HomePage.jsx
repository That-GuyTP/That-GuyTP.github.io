import knowingNarcolepsyThumbnail from '../images/KnowingNarcolepsyThumbnail.png';

const focusAreas = [
  {
    title: 'Software Development',
    description:
      'Building practical applications with clean structure, readable code, and reliable behavior.'
  },
  {
    title: 'Computer Information Systems',
    description:
      'Applying systems thinking to connect software, data, and infrastructure into useful solutions.'
  },
  {
    title: 'Project Execution',
    description:
      'Turning ideas into working deliverables through iterative testing, feedback, and deployment.'
  }
];

const modules = [
  {
    title: 'Knowing Narcolepsy',
    link: 'https://that-guytp.github.io/Knowing-Narcolepsy/',
    thumbnail: knowingNarcolepsyThumbnail,
    description: 'Add a short description for this module here.'
  }
];

export default function HomePage() {
  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Main Page</p>
        <h2>Welcome to my coding work hub.</h2>
        <p>
          I am Thomas Peterson, a CIS major at USC. This site introduces who I am, highlights Java
          project demos through a live API integration, and organizes supporting documents in one place.
        </p>
      </div>

      <div className="content-grid">
        {focusAreas.map((item) => (
          <article key={item.title} className="info-card">
            <h3>{item.title}</h3>
            <p>{item.description}</p>
          </article>
        ))}
      </div>

      <div className="module-panel">
        <p className="eyebrow">Featured Module</p>
        <div className="module-grid">
          {modules.map((module) => (
            <article key={module.title} className="module-card">
              <a
                className="module-thumb-link"
                href={module.link}
                target="_blank"
                rel="noreferrer"
                aria-label={`Open ${module.title}`}
              >
                <img src={module.thumbnail} alt={`${module.title} thumbnail`} />
              </a>
              <div className="module-body">
                <h3>
                  <a href={module.link} target="_blank" rel="noreferrer">
                    {module.title}
                  </a>
                </h3>
                <p>{module.description}</p>
              </div>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
