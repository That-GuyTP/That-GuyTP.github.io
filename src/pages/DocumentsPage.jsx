const documents = [
  {
    title: 'Resume Notes',
    description: 'Editable text placeholder for resume highlights and updates.',
    fileName: 'resume-notes.txt'
  },
  {
    title: 'Coursework Summary',
    description: 'Quick summary of courses, outcomes, and applied technologies.',
    fileName: 'coursework-summary.md'
  },
  {
    title: 'Project Intake Template',
    description: 'Template for collecting requirements before starting a new build.',
    fileName: 'project-intake-template.md'
  }
];

export default function DocumentsPage() {
  const baseUrl = import.meta.env.BASE_URL;

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Documents</p>
        <h2>Supporting files and references</h2>
        <p>
          Use this page to host resumes, project writeups, PDF notes, and any additional documentation you
          want to share.
        </p>
      </div>

      <div className="content-grid">
        {documents.map((doc) => (
          <article key={doc.fileName} className="info-card">
            <h3>{doc.title}</h3>
            <p>{doc.description}</p>
            <a href={`${baseUrl}docs/${doc.fileName}`} target="_blank" rel="noreferrer">
              Open Document
            </a>
          </article>
        ))}
      </div>
    </section>
  );
}
