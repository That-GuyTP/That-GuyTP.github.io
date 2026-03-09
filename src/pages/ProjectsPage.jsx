import knowingNarcolepsyThumbnail from '../images/KnowingNarcolepsyThumbnail.png';
import loveLearningLangsThumbnail from '../images/LoveLearningLangsThumbnail.png';
import taxPortThumbnail from '../images/TaxPortThumbnail.png';

const projects = [
  {
    title: 'LoveLearningLangs',
    link: 'https://github.com/That-GuyTP/TheCoolCoders-LoveLearningLangs-Frontend',
    thumbnail: loveLearningLangsThumbnail,
    description:
      'LoveLearningLangs is a team-based, language learning app. "The goal of this project is to create an application that can be used by individuals to learn languages. Users will be able to create an account, store progress, choose between langauges, work through a courselist, & complete exercises." It was our final project for CSCE 247 and was a blast to develop with my groupmates.',
    tags: ['Application', 'Team Build']
  },
  {
    title: 'Knowing Narcolepsy',
    link: 'https://that-guytp.github.io/Knowing-Narcolepsy/',
    thumbnail: knowingNarcolepsyThumbnail,
    description:
      'Knowing Narcolepsy was a react based webpage that I build for my Web Applications class. The goal of this class was to provide me a strong introduction into HTML/CSS coding as well as teach me the modern approaches to web based development using React. The webpage is build using with react frontend, react backend, and MongoDB Atlas for the database.',
    tags: ['Interactive Experience', 'Live', 'Webpage']
  },
  {
    title: 'TaxPort',
    link: 'https://garnetgrid-1.onrender.com/',
    thumbnail: taxPortThumbnail,
    description:
      'TaxPort is a tax document based software that is created with the goal to make the process of organising documents between a CPA and their clients streamlined. It is currently still in Beta but will be fully complete by the end of the semester.',
    tags: ['Capstone', 'In Progress']
  }
];

function ProjectThumbnail({ project }) {
  const fallback = (
    <div className="module-thumb-fallback" aria-hidden="true">
      <span>{project.title}</span>
    </div>
  );

  const imageOrFallback = project.thumbnail ? (
    <img src={project.thumbnail} alt={`${project.title} thumbnail`} />
  ) : (
    fallback
  );

  if (project.link) {
    return (
      <a
        className="module-thumb-link"
        href={project.link}
        target="_blank"
        rel="noreferrer"
        aria-label={`Open ${project.title}`}
      >
        {imageOrFallback}
      </a>
    );
  }

  return <div className="module-thumb-link">{imageOrFallback}</div>;
}

export default function ProjectsPage() {
  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Projects</p>
        <h2>Portfolio builds beyond repos</h2>
        <p>
          This page highlights major projects and deliverables, including web builds, educational modules,
          and your capstone work.
        </p>
      </div>

      <div className="module-panel">
        <p className="eyebrow">Featured Projects</p>
        <div className="module-grid">
          {projects.map((project) => (
            <article key={project.title} className="module-card">
              <ProjectThumbnail project={project} />
              <div className="module-body">
                <h3>
                  {project.link ? (
                    <a href={project.link} target="_blank" rel="noreferrer">
                      {project.title}
                    </a>
                  ) : (
                    project.title
                  )}
                </h3>
                <p>{project.description}</p>
                {!project.link && <p className="module-link-placeholder">Link coming soon.</p>}
                <div className="project-metrics">
                  {project.tags.map((tag) => (
                    <span key={tag}>{tag}</span>
                  ))}
                </div>
              </div>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
