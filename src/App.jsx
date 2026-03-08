import { Navigate, NavLink, Outlet, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import JavaProjectsPage from './pages/JavaProjectsPage';
import DocumentsPage from './pages/DocumentsPage';

const MAINTENANCE_MODE = true;

function MaintenanceNotice() {
  return (
    <main className="maintenance-wrap">
      <section className="maintenance-card" role="status" aria-live="polite">
        <h1>Website Temporarily Down</h1>
        <p>
          Sorry, my webpage is currently down due to an issue with Render.com&apos;s
          &quot;free&quot; account tier.
        </p>
        <p>
          I am currently configuring a workaround and plan to have this page back up by
          Monday, March 9, 2026 at 2:00 PM EST.
        </p>
        <p>Thank you for your understanding.</p>
      </section>
    </main>
  );
}

function SiteLayout() {
  return (
    <div className="site-shell">
      <header className="site-header">
        <div className="brand-block">
          <p className="brand-kicker">Code Portfolio</p>
          <h1>Thomas Peterson</h1>
        </div>
        <nav aria-label="Main navigation">
          <ul className="nav-list">
            <li>
              <NavLink to="/" end>
                Home
              </NavLink>
            </li>
            <li>
              <NavLink to="/java-projects">Java Projects</NavLink>
            </li>
            <li>
              <NavLink to="/documents">Documents</NavLink>
            </li>
          </ul>
        </nav>
      </header>

      <main className="page-content">
        <Outlet />
      </main>

      <footer className="site-footer">
        <p>Built with React and deployed through GitHub Pages.</p>
      </footer>
    </div>
  );
}

export default function App() {
  if (MAINTENANCE_MODE) {
    return <MaintenanceNotice />;
  }

  return (
    <Routes>
      <Route path="/" element={<SiteLayout />}>
        <Route index element={<HomePage />} />
        <Route path="java-projects" element={<JavaProjectsPage />} />
        <Route path="documents" element={<DocumentsPage />} />
        <Route path="*" element={<Navigate replace to="/" />} />
      </Route>
    </Routes>
  );
}
