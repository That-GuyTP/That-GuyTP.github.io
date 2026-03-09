import { Navigate, NavLink, Outlet, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import JavaProjectsPage from './pages/JavaProjectsPage';
import DocumentsPage from './pages/DocumentsPage';

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
