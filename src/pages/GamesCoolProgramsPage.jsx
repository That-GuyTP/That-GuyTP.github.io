import { useEffect, useMemo, useState } from 'react';
import SortSorterPage from './SortSorterPage';
import DistanceVelocityCalculatorPage from './DistanceVelocityCalculatorPage';
import TriangleMakerPage from './TriangleMakerPage';
import RockPaperScissorsPage from './RockPaperScissorsPage';
import MorningRoutineAdventurePage from './MorningRoutineAdventurePage';
import EquationCheckerPage from './EquationCheckerPage';

const gamesAndPrograms = [
  {
    id: 'equation-checker',
    title: 'Equation Checker',
    language: 'C++',
    description:
      'Check whether both sides of an equation are equal, including arithmetic and parentheses handling.',
    tags: ['Playable Now', 'C++', 'Math Parser'],
    component: EquationCheckerPage
  },
  {
    id: 'sortsorter',
    title: 'SortSorter',
    language: 'Java',
    description:
      'Add words and sort them by "sort" match count.',
    tags: ['Playable Now', 'Original Source Linked', 'Algorithm'],
    component: SortSorterPage
  },
  {
    id: 'distance-velocity-calculator',
    title: 'Distance & Velocity Calculator',
    language: 'Java',
    description:
      'Calculate final velocity and distance traveled from an initial velocity and elapsed time.',
    tags: ['Playable Now', 'Physics Utility', 'Formula Calculator'],
    component: DistanceVelocityCalculatorPage
  },
  {
    id: 'triangle-maker',
    title: 'Triangle Maker',
    language: 'Java',
    description:
      'Generate an up-and-down star triangle by entering a height, mirroring your Java loop logic.',
    tags: ['Playable Now', 'Pattern Builder', 'Loop Practice'],
    component: TriangleMakerPage
  },
  {
    id: 'rock-paper-scissors',
    title: 'Rock Paper Scissors',
    language: 'Java',
    description:
      'Best-of-three Rock Paper Scissors with match history and the same scoring behavior as your Java version.',
    tags: ['Playable Now', 'Game', 'Randomized'],
    component: RockPaperScissorsPage
  },
  {
    id: 'morning-routine-adventure',
    title: 'Morning Routine Adventure',
    language: 'Java',
    description:
      'Choose-your-own-adventure recreation with branching decisions and multiple endings to discover.',
    tags: ['Playable Now', 'Story Branches', 'Interactive'],
    component: MorningRoutineAdventurePage
  }
];

export default function GamesCoolProgramsPage() {
  const [activeProgramId, setActiveProgramId] = useState(null);

  const activeProgram = useMemo(
    () => gamesAndPrograms.find((program) => program.id === activeProgramId) ?? null,
    [activeProgramId]
  );

  const ActiveProgramComponent = activeProgram?.component ?? null;

  useEffect(() => {
    if (!activeProgram) {
      return undefined;
    }

    const previousOverflow = document.body.style.overflow;

    function handleKeyDown(event) {
      if (event.key === 'Escape') {
        setActiveProgramId(null);
      }
    }

    document.body.style.overflow = 'hidden';
    window.addEventListener('keydown', handleKeyDown);

    return () => {
      document.body.style.overflow = previousOverflow;
      window.removeEventListener('keydown', handleKeyDown);
    };
  }, [activeProgram]);

  function openProgram(programId) {
    setActiveProgramId(programId);
  }

  function closeProgram() {
    setActiveProgramId(null);
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Games &amp; Cool Programs</p>
        <h2>Playable and interactive program ideas</h2>
        <p>
          Click any game title to launch it in a popup!
        </p>
      </div>

      <div className="project-grid">
        {gamesAndPrograms.map((program) => (
          <article key={program.id} className="project-card">
            <div className="project-head">
              <h3>
                <button
                  type="button"
                  className="program-launch-link"
                  onClick={() => openProgram(program.id)}
                >
                  {program.title}
                </button>
              </h3>
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

      {activeProgram && ActiveProgramComponent && (
        <div className="program-modal-overlay" role="presentation">
          <div
            className="program-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby={`game-title-${activeProgram.id}`}
          >
            <button
              type="button"
              className="program-modal-close"
              aria-label="Close game popup"
              onClick={closeProgram}
            >
              X
            </button>
            <p className="eyebrow">Now Playing</p>
            <h3 id={`game-title-${activeProgram.id}`} className="program-modal-title">
              {activeProgram.title}
            </h3>
            <div className="program-modal-content">
              <ActiveProgramComponent />
            </div>
          </div>
        </div>
      )}
    </section>
  );
}
