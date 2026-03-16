import { useState } from 'react';
import cvFile from '../images/personal_documents/Thomas Peterson - CV.pdf';
import transcriptFile from '../images/personal_documents/Thomas Peterson - Unofficial Transcript.pdf';

const documents = [
  {
    id: 'cv',
    title: 'Curriculum Vitae',
    description: 'Professional background, projects, and skills summary.',
    fileUrl: cvFile,
    fileName: 'Thomas Peterson - CV.pdf'
  },
  {
    id: 'transcript',
    title: 'Unofficial Transcript',
    description: 'Academic record and completed coursework. For the official transcript, please email me at ThomasAPeterson.Business@gmail.com',
    fileUrl: transcriptFile,
    fileName: 'Thomas Peterson - Unofficial Transcript.pdf'
  }
];

export default function DocumentsPage() {
  const [openPreviews, setOpenPreviews] = useState({});

  const togglePreview = (id) => {
    setOpenPreviews((previous) => ({
      ...previous,
      [id]: !previous[id]
    }));
  };

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Documents</p>
        <h2>CV and transcript</h2>
        <p>Download each file directly, or expand the arrow to preview the PDF on this page.</p>
      </div>

      <div className="documents-grid">
        {documents.map((doc) => {
          const isPreviewOpen = Boolean(openPreviews[doc.id]);
          const previewId = `document-preview-${doc.id}`;

          return (
            <article key={doc.id} className="info-card document-card">
              <div className="document-head">
                <div>
                  <h3>{doc.title}</h3>
                  <p>{doc.description}</p>
                </div>

                <div className="document-actions">
                  <a className="document-download" href={doc.fileUrl} download={doc.fileName}>
                    Download
                  </a>
                  <button
                    type="button"
                    className="document-toggle"
                    onClick={() => togglePreview(doc.id)}
                    aria-expanded={isPreviewOpen}
                    aria-controls={previewId}
                    aria-label={`${isPreviewOpen ? 'Hide' : 'Show'} ${doc.title} preview`}
                  >
                    <span className={`document-arrow ${isPreviewOpen ? 'open' : ''}`} aria-hidden="true">
                      v
                    </span>
                  </button>
                </div>
              </div>

              {isPreviewOpen && (
                <div id={previewId} className="document-preview">
                  <iframe src={doc.fileUrl} title={`${doc.title} preview`} loading="lazy" />
                </div>
              )}
            </article>
          );
        })}
      </div>
    </section>
  );
}
