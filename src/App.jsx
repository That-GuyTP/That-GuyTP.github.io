import { useEffect, useState } from 'react';
import { Navigate, NavLink, Outlet, Route, Routes, useLocation } from 'react-router-dom';
import HomePage from './pages/HomePage';
import ProjectsPage from './pages/ProjectsPage';
import DocumentsPage from './pages/DocumentsPage';
import GamesCoolProgramsPage from './pages/GamesCoolProgramsPage';
import SortSorterPage from './pages/SortSorterPage';
import DistanceVelocityCalculatorPage from './pages/DistanceVelocityCalculatorPage';
import TriangleMakerPage from './pages/TriangleMakerPage';
import RockPaperScissorsPage from './pages/RockPaperScissorsPage';
import MorningRoutineAdventurePage from './pages/MorningRoutineAdventurePage';
import EquationCheckerPage from './pages/EquationCheckerPage';
import CoinRowRobotPage from './pages/CoinRowRobotPage';
import LoveLearningLangsPage from './pages/LoveLearningLangsPage';

function SiteLayout() {
  const [mobileNavOpen, setMobileNavOpen] = useState(false);
  const location = useLocation();

  useEffect(() => {
    setMobileNavOpen(false);
  }, [location.pathname]);

  return (
    <div className="site-shell">
      <header className="site-header">
        <div className="brand-block">
          <p className="brand-kicker">Code Portfolio</p>
          <h1>Thomas Peterson</h1>
        </div>
        <nav className="site-nav" aria-label="Main navigation">
          <button
            type="button"
            className={`nav-toggle ${mobileNavOpen ? 'open' : ''}`}
            aria-expanded={mobileNavOpen}
            aria-controls="site-main-nav"
            aria-label={mobileNavOpen ? 'Close navigation menu' : 'Open navigation menu'}
            onClick={() => setMobileNavOpen((previous) => !previous)}
          >
            <span className="nav-toggle-bar" aria-hidden="true" />
            <span className="nav-toggle-bar" aria-hidden="true" />
            <span className="nav-toggle-bar" aria-hidden="true" />
          </button>
          <ul id="site-main-nav" className={`nav-list ${mobileNavOpen ? 'open' : ''}`}>
            <li>
              <NavLink to="/" end onClick={() => setMobileNavOpen(false)}>
                Home
              </NavLink>
            </li>
            <li>
              <NavLink to="/projects" onClick={() => setMobileNavOpen(false)}>
                Projects
              </NavLink>
            </li>
            <li>
              <NavLink to="/games-cool-programs" onClick={() => setMobileNavOpen(false)}>
                Games &amp; Cool Programs
              </NavLink>
            </li>
            <li>
              <NavLink to="/documents" onClick={() => setMobileNavOpen(false)}>
                Documents
              </NavLink>
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
        <Route path="projects" element={<ProjectsPage />} />
        <Route path="projects/lovelearninglangs" element={<LoveLearningLangsPage />} />
        <Route path="java-projects" element={<Navigate replace to="/projects" />} />
        <Route path="games-cool-programs" element={<GamesCoolProgramsPage />} />
          <Route path="games-cool-programs/coin-row-robot" element={<CoinRowRobotPage />} />
          <Route path="games-cool-programs/equation-checker" element={<EquationCheckerPage />} />
          <Route path="games-cool-programs/sortsorter" element={<SortSorterPage />} />
          <Route path="games-cool-programs/distance-velocity-calculator" element={<DistanceVelocityCalculatorPage />} />
          <Route path="games-cool-programs/triangle-maker" element={<TriangleMakerPage />} />
          <Route path="games-cool-programs/rock-paper-scissors" element={<RockPaperScissorsPage />} />
          <Route path="games-cool-programs/morning-routine-adventure" element={<MorningRoutineAdventurePage />} />
        <Route path="documents" element={<DocumentsPage />} />
        <Route path="*" element={<Navigate replace to="/" />} />
      </Route>
    </Routes>
  );
}
