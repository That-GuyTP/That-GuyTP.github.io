import { useEffect, useMemo, useState } from 'react';

const GRID_ROWS = 8;
const GRID_COLUMNS = 8;
const TOTAL_COINS = 7;
const WALL_RATE = 0.26;

function buildGuaranteedPath(rows, columns) {
  const path = [{ row: 0, column: 0 }];
  let row = 0;
  let column = 0;

  while (row < rows - 1 || column < columns - 1) {
    const canMoveDown = row < rows - 1;
    const canMoveRight = column < columns - 1;

    if (canMoveDown && canMoveRight) {
      if (Math.random() < 0.5) {
        column += 1;
      } else {
        row += 1;
      }
    } else if (canMoveRight) {
      column += 1;
    } else {
      row += 1;
    }

    path.push({ row, column });
  }

  return path;
}

function generateMaze(rows = GRID_ROWS, columns = GRID_COLUMNS) {
  const guaranteedPath = buildGuaranteedPath(rows, columns);
  const guaranteedPathSet = new Set(
    guaranteedPath.map((cell) => `${cell.row}-${cell.column}`)
  );

  const maze = Array.from({ length: rows }, (_, row) =>
    Array.from({ length: columns }, (_, column) => {
      const isOnGuaranteedPath = guaranteedPathSet.has(`${row}-${column}`);
      const wall = isOnGuaranteedPath ? false : Math.random() < WALL_RATE;
      return { wall, coin: 0 };
    })
  );

  const openCells = [];

  for (let row = 0; row < rows; row += 1) {
    for (let column = 0; column < columns; column += 1) {
      if (!maze[row][column].wall) {
        openCells.push({ row, column });
      }
    }
  }

  for (let i = openCells.length - 1; i > 0; i -= 1) {
    const randomIndex = Math.floor(Math.random() * (i + 1));
    const temp = openCells[i];
    openCells[i] = openCells[randomIndex];
    openCells[randomIndex] = temp;
  }

  if (openCells.length < TOTAL_COINS) {
    throw new Error('Unable to place all required coins.');
  }

  for (let i = 0; i < TOTAL_COINS; i += 1) {
    const { row, column } = openCells[i];
    maze[row][column].coin = 1;
  }

  return maze;
}

function buildRobotPlan(maze) {
  const rows = maze.length;
  const columns = maze[0].length;
  const NEGATIVE_INF = Number.NEGATIVE_INFINITY;

  const dp = Array.from({ length: rows }, () => Array(columns).fill(NEGATIVE_INF));
  const parent = Array.from({ length: rows }, () => Array(columns).fill(null));

  if (maze[0][0].wall || maze[rows - 1][columns - 1].wall) {
    return {
      reachable: false,
      rows,
      columns,
      maze,
      dp,
      path: [],
      pathPrefixTotals: [],
      collectedCells: []
    };
  }

  for (let row = 0; row < rows; row += 1) {
    for (let column = 0; column < columns; column += 1) {
      const currentCell = maze[row][column];

      if (currentCell.wall) {
        continue;
      }

      if (row === 0 && column === 0) {
        dp[row][column] = currentCell.coin;
        continue;
      }

      let bestPrevious = NEGATIVE_INF;
      let bestParent = null;

      if (row > 0 && dp[row - 1][column] > bestPrevious) {
        bestPrevious = dp[row - 1][column];
        bestParent = { row: row - 1, column };
      }

      // Prefer left-side parents on ties so routes are deterministic.
      if (column > 0 && dp[row][column - 1] >= bestPrevious) {
        bestPrevious = dp[row][column - 1];
        bestParent = { row, column: column - 1 };
      }

      if (bestPrevious === NEGATIVE_INF) {
        continue;
      }

      dp[row][column] = bestPrevious + currentCell.coin;
      parent[row][column] = bestParent;
    }
  }

  const targetValue = dp[rows - 1][columns - 1];

  if (targetValue === NEGATIVE_INF) {
    return {
      reachable: false,
      rows,
      columns,
      maze,
      dp,
      path: [],
      pathPrefixTotals: [],
      collectedCells: []
    };
  }

  const path = [];
  let row = rows - 1;
  let column = columns - 1;

  while (row >= 0 && column >= 0) {
    path.unshift({ row, column });

    if (row === 0 && column === 0) {
      break;
    }

    const previous = parent[row][column];

    if (!previous) {
      break;
    }

    row = previous.row;
    column = previous.column;
  }

  let runningTotal = 0;
  const pathPrefixTotals = path.map((cell) => {
    runningTotal += maze[cell.row][cell.column].coin;
    return runningTotal;
  });

  const collectedCells = path.filter((cell) => maze[cell.row][cell.column].coin === 1);

  return {
    reachable: true,
    rows,
    columns,
    maze,
    dp,
    path,
    pathPrefixTotals,
    collectedCells
  };
}

export default function CoinRowRobotPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/coin-row-robot`;
  const sourceFiles = [
    {
      name: 'CoinRowRobot.cpp',
      url: `${sourceBasePath}/CoinRowRobot.cpp`
    }
  ];

  const [status, setStatus] = useState(null);
  const [result, setResult] = useState(null);
  const [currentStep, setCurrentStep] = useState(0);
  const [isPlaying, setIsPlaying] = useState(false);

  const pathIndexMap = useMemo(() => {
    if (!result || !result.reachable) {
      return new Map();
    }

    const map = new Map();

    for (let index = 0; index < result.path.length; index += 1) {
      const cell = result.path[index];
      map.set(`${cell.row}-${cell.column}`, index);
    }

    return map;
  }, [result]);

  useEffect(() => {
    if (!result || !result.reachable || !isPlaying) {
      return undefined;
    }

    const finalStep = result.path.length - 1;
    const timer = window.setInterval(() => {
      setCurrentStep((previousStep) => {
        if (previousStep >= finalStep) {
          setIsPlaying(false);
          return previousStep;
        }

        return previousStep + 1;
      });
    }, 500);

    return () => {
      window.clearInterval(timer);
    };
  }, [isPlaying, result]);

  function generateMazeRun(autoplay) {
    let maze;

    try {
      maze = generateMaze();
    } catch (error) {
      setStatus({
        type: 'error',
        text: `Maze generation failed: ${error instanceof Error ? error.message : 'Unknown error.'}`
      });
      setResult(null);
      setCurrentStep(0);
      setIsPlaying(false);
      return;
    }

    const plan = buildRobotPlan(maze);

    if (!plan.reachable) {
      setStatus({
        type: 'error',
        text: 'Could not produce a valid route this round. Press generate again.'
      });
      setResult(null);
      setCurrentStep(0);
      setIsPlaying(false);
      return;
    }

    setResult(plan);
    setCurrentStep(0);
    setIsPlaying(autoplay && plan.path.length > 1);
    setStatus({
      type: 'success',
      text: `Generated an ${GRID_ROWS}x${GRID_COLUMNS} maze with ${TOTAL_COINS} total coins.`
    });
  }

  function onGenerateAndPlay(event) {
    event.preventDefault();
    generateMazeRun(true);
  }

  function togglePlayPause() {
    if (!result || !result.reachable) {
      return;
    }

    const lastStep = result.path.length - 1;

    if (currentStep >= lastStep) {
      setCurrentStep(0);
      setIsPlaying(true);
      return;
    }

    setIsPlaying((previousState) => !previousState);
  }

  function goBackward() {
    setIsPlaying(false);
    setCurrentStep((previousStep) => Math.max(0, previousStep - 1));
  }

  function goForward() {
    if (!result || !result.reachable) {
      return;
    }

    const lastStep = result.path.length - 1;
    setIsPlaying(false);
    setCurrentStep((previousStep) => Math.min(lastStep, previousStep + 1));
  }

  const hasPath = Boolean(result && result.reachable && result.path.length > 0);
  const lastStep = hasPath ? result.path.length - 1 : 0;
  const safeStep = hasPath ? Math.min(currentStep, lastStep) : 0;
  const currentCell = hasPath ? result.path[safeStep] : null;
  const currentCellCoin =
    hasPath && currentCell ? result.maze[currentCell.row][currentCell.column].coin : 0;
  const collectedSoFar = hasPath ? result.pathPrefixTotals[safeStep] : 0;
  const pathCoordinates = hasPath
    ? result.path.map((cell) => `(${cell.row},${cell.column})`).join(' -> ')
    : '';

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Coin Row Robot</h2>
        <p>
          Generate and play an auto-built maze. The robot finds the best right/down route from start to
          finish while collecting equal-value coins (`1` each).
        </p>
      </div>

      <article className="project-card">
        <h3>Original C++ Source</h3>
        <p>
          The browser version and C++ source both use maze generation plus dynamic programming path
          scoring where every coin contributes exactly one point.
        </p>
        <div className="sortsorter-source-links">
          {sourceFiles.map((file) => (
            <a key={file.name} href={file.url} target="_blank" rel="noreferrer">
              {file.name}
            </a>
          ))}
        </div>
      </article>

      <article className="program-panel">
        <form className="program-form-grid" onSubmit={onGenerateAndPlay}>
          <div className="program-actions">
            <button type="submit" className="run-query-button">
              Generate Maze and Play
            </button>
          </div>
        </form>

        <p className="query-label">
          Each run builds a fixed {GRID_ROWS}x{GRID_COLUMNS} maze with walls, empty spots (`0`), and
          exactly {TOTAL_COINS} coin spots (`1`).
        </p>
        {status && <p className={`status-line ${status.type}`}>{status.text}</p>}
      </article>

      {hasPath && (
        <>
          <article className="project-card">
            <h3>Robot Grid</h3>
            <div className="coin-grid-legend">
              <span className="legend-chip">Path</span>
              <span className="legend-chip visited">Visited</span>
              <span className="legend-chip current">Robot</span>
              <span className="legend-chip wall">Wall</span>
              <span className="legend-chip coin">Coin (1)</span>
              <span className="legend-chip collected">Collected Coin</span>
              <span className="legend-chip special">Start/Finish</span>
            </div>

            <div className="coin-grid-wrapper">
              <div
                className="coin-grid"
                style={{ gridTemplateColumns: `repeat(${result.columns}, 48px)` }}
              >
                {result.maze.map((rowValues, rowIndex) =>
                  rowValues.map((cell, columnIndex) => {
                    const key = `${rowIndex}-${columnIndex}`;
                    const pathIndex = pathIndexMap.get(key);
                    const isOnPath = pathIndex !== undefined;
                    const isVisited = isOnPath && pathIndex <= safeStep;
                    const isCurrent = isOnPath && pathIndex === safeStep;
                    const isStart = rowIndex === 0 && columnIndex === 0;
                    const isFinish = rowIndex === result.rows - 1 && columnIndex === result.columns - 1;
                    const hasCoin = !cell.wall && cell.coin === 1;
                    const isCollected = isVisited && hasCoin;

                    const classNames = ['coin-grid-cell'];

                    if (cell.wall) {
                      classNames.push('wall');
                    }

                    if (hasCoin) {
                      classNames.push('has-coin');
                    }

                    if (isOnPath) {
                      classNames.push('path');
                    }

                    if (isVisited) {
                      classNames.push('visited');
                    }

                    if (isCurrent) {
                      classNames.push('current');
                    }

                    if (isCollected) {
                      classNames.push('collected');
                    }

                    if (isStart) {
                      classNames.push('start');
                    }

                    if (isFinish) {
                      classNames.push('finish');
                    }

                    return (
                      <div key={key} className={classNames.join(' ')}>
                        <span className="cell-value">{cell.coin}</span>
                      </div>
                    );
                  })
                )}
              </div>
            </div>
          </article>

          <article className="program-panel">
            <h3>Playback Controls</h3>
            <div className="coin-controls">
              <button type="button" className="sortsorter-secondary-button" onClick={goBackward}>
                Step Back
              </button>
              <button type="button" className="run-query-button" onClick={togglePlayPause}>
                {isPlaying ? 'Pause' : safeStep >= lastStep ? 'Replay Path' : 'Play Path'}
              </button>
              <button type="button" className="sortsorter-secondary-button" onClick={goForward}>
                Step Forward
              </button>
            </div>

            <label htmlFor="coin-row-step" className="coin-step-label">
              Path step: {safeStep + 1}/{result.path.length}
              <input
                id="coin-row-step"
                type="range"
                min="0"
                max={String(lastStep)}
                step="1"
                value={safeStep}
                onChange={(event) => {
                  setIsPlaying(false);
                  setCurrentStep(Number(event.target.value));
                }}
              />
            </label>

            <p className="query-label">
              Current cell: ({currentCell?.row}, {currentCell?.column}) | Coin here: {currentCellCoin} |
              Total collected so far: {collectedSoFar}
            </p>
          </article>

          <article className="project-card">
            <h3>Path Coordinates</h3>
            <p className="program-output coin-path-output">{pathCoordinates}</p>
          </article>

          <article className="project-card">
            <h3>Collected Coin Cells</h3>
            <p className="program-output coin-path-output">
              {result.collectedCells.length > 0
                ? result.collectedCells.map((cell) => `(${cell.row},${cell.column})`).join(' | ')
                : 'No coin cells were collected on this run.'}
            </p>
          </article>
        </>
      )}
    </section>
  );
}
