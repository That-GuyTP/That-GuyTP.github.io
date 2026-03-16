import { useState } from 'react';

const WINNING_SCORE = 2;

function getPlayerRoll(choice) {
  const normalizedChoice = choice.trim().toLowerCase();

  if (normalizedChoice === 'rock') {
    return 1;
  }

  if (normalizedChoice === 'paper') {
    return 2;
  }

  if (normalizedChoice === 'scissor' || normalizedChoice === 'scissors') {
    return 3;
  }

  return 4;
}

function getComputerChoice(roll) {
  if (roll === 1) {
    return 'Rock';
  }

  if (roll === 2) {
    return 'Paper';
  }

  return 'Scissor';
}

export default function RockPaperScissorsPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/rock-paper-scissors`;
  const sourceFiles = [
    {
      name: 'Homework03.java',
      url: `${sourceBasePath}/Homework03.java`
    }
  ];

  const [playerName, setPlayerName] = useState('Player');
  const [playerChoice, setPlayerChoice] = useState('');
  const [playerScore, setPlayerScore] = useState(0);
  const [computerScore, setComputerScore] = useState(0);
  const [status, setStatus] = useState('First to 2 points wins. Enter a choice to begin.');
  const [history, setHistory] = useState([]);
  const [gameOver, setGameOver] = useState(false);

  function playRound(event) {
    event.preventDefault();

    if (gameOver) {
      setStatus('Game complete. Press Reset Match to play again.');
      return;
    }

    const playerRoll = getPlayerRoll(playerChoice);
    const computerRoll = Math.floor(Math.random() * 3) + 1;
    const computerChoice = getComputerChoice(computerRoll);
    const currentPlayerName = playerName.trim() || 'Player';

    let nextPlayerScore = playerScore;
    let nextComputerScore = computerScore;
    let roundMessage = '';

    if (playerRoll === 4) {
      nextComputerScore += 1;
      roundMessage =
        "Sorry, that wasn't a correct input. The computer is automatically awarded a point.";
    } else if (playerRoll === computerRoll) {
      roundMessage = `The computer chose ${computerChoice}. You chose ${playerChoice}. It's a draw.`;
    } else if (
      (computerRoll === 3 && playerRoll === 1) ||
      (computerRoll === 1 && playerRoll === 2) ||
      (computerRoll === 2 && playerRoll === 3)
    ) {
      nextPlayerScore += 1;
      roundMessage = `The computer chose ${computerChoice}. You chose ${playerChoice}. You won the round.`;
    } else {
      nextComputerScore += 1;
      roundMessage =
        `The computer chose ${computerChoice}. You chose ${playerChoice}. ` +
        'The computer won the round.';
    }

    const scoreMessage = `Score: ${currentPlayerName} ${nextPlayerScore} - ${nextComputerScore} Computer`;

    setPlayerScore(nextPlayerScore);
    setComputerScore(nextComputerScore);
    setHistory((previous) => [`${roundMessage} ${scoreMessage}`, ...previous].slice(0, 8));

    if (nextPlayerScore >= WINNING_SCORE || nextComputerScore >= WINNING_SCORE) {
      setGameOver(true);
      if (nextPlayerScore > nextComputerScore) {
        setStatus(`Congrats, ${currentPlayerName}. You won the match!`);
      } else {
        setStatus(`Sorry, ${currentPlayerName}. The computer won the match.`);
      }
      return;
    }

    setStatus(scoreMessage);
    setPlayerChoice('');
  }

  function resetMatch() {
    setPlayerScore(0);
    setComputerScore(0);
    setPlayerChoice('');
    setStatus('First to 2 points wins. Enter a choice to begin.');
    setHistory([]);
    setGameOver(false);
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Rock Paper Scissors</h2>
        <p>
          Browser version of your Java best-of-three Rock Paper Scissors game. Invalid choices still
          award the computer a point, matching the original rules.
        </p>
      </div>

      <article className="project-card">
        <h3>Original Java Source</h3>
        <div className="sortsorter-source-links">
          {sourceFiles.map((file) => (
            <a key={file.name} href={file.url} target="_blank" rel="noreferrer">
              {file.name}
            </a>
          ))}
        </div>
      </article>

      <article className="program-panel">
        <form className="program-form-grid" onSubmit={playRound}>
          <label htmlFor="rps-name">
            Your name
            <input
              id="rps-name"
              type="text"
              value={playerName}
              onChange={(event) => setPlayerName(event.target.value)}
              placeholder="Enter your name"
            />
          </label>

          <label htmlFor="rps-choice">
            Choice
            <input
              id="rps-choice"
              type="text"
              value={playerChoice}
              onChange={(event) => setPlayerChoice(event.target.value)}
              placeholder='Rock, Paper, or Scissor'
            />
          </label>

          <div className="choice-row" role="group" aria-label="Quick choices">
            <button
              type="button"
              className="sortsorter-secondary-button"
              onClick={() => setPlayerChoice('Rock')}
            >
              Rock
            </button>
            <button
              type="button"
              className="sortsorter-secondary-button"
              onClick={() => setPlayerChoice('Paper')}
            >
              Paper
            </button>
            <button
              type="button"
              className="sortsorter-secondary-button"
              onClick={() => setPlayerChoice('Scissor')}
            >
              Scissor
            </button>
          </div>

          <div className="program-actions">
            <button type="submit" className="run-query-button">
              Play Round
            </button>
            <button type="button" className="sortsorter-secondary-button" onClick={resetMatch}>
              Reset Match
            </button>
          </div>
        </form>

        <p className="status-line">{status}</p>
        <p className="query-label">
          Scoreboard: {playerName.trim() || 'Player'} {playerScore} - {computerScore} Computer
        </p>
      </article>

      <article className="project-card">
        <h3>Recent Rounds</h3>
        {history.length > 0 ? (
          <ol className="rps-history">
            {history.map((entry, index) => (
              <li key={`${entry}-${index}`}>{entry}</li>
            ))}
          </ol>
        ) : (
          <p>Play a round to start the match history.</p>
        )}
      </article>
    </section>
  );
}
