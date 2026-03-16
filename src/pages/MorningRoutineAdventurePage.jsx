import { useState } from 'react';

const ENDINGS = {
  1: 'Sleepyhead',
  2: 'Stomachache',
  3: 'Partially Ready',
  4: 'Crumbling Food Pyramid',
  5: 'Happy Body, Sad Stomach',
  6: 'A Rushed Start',
  7: 'Elephant in the Room',
  8: 'Bad Choices Lead to Bad Outcomes',
  9: 'Perfect Day'
};

function getEndingLabel(code) {
  if (!ENDINGS[code]) {
    return '';
  }

  return `${ENDINGS[code]} (${code}/9)`;
}

export default function MorningRoutineAdventurePage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/morning-routine-adventure`;
  const sourceFiles = [
    {
      name: 'Homework02.java',
      url: `${sourceBasePath}/Homework02.java`
    }
  ];

  const [scene, setScene] = useState('start');
  const [foodChoice, setFoodChoice] = useState(null);
  const [endingCode, setEndingCode] = useState(null);
  const [storyLog, setStoryLog] = useState([
    'You wake up in your bed, ready to start the day. What do you do first?'
  ]);

  function appendLog(message) {
    setStoryLog((previous) => [...previous, message]);
  }

  function finishWithEnding(code, message) {
    if (message) {
      appendLog(message);
    }
    appendLog(`Ending: ${getEndingLabel(code)}`);
    setEndingCode(code);
    setScene('ended');
  }

  function handleFridgeChoice(choice, origin) {
    if (choice === 'apple') {
      appendLog('You chose an apple and feel satisfied with your healthy breakfast.');
      setFoodChoice(1);
      if (origin === 'shower') {
        finishWithEnding(9, 'After breakfast, you head out and have a very productive day at school.');
      } else {
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    if (choice === 'yogurt') {
      appendLog('You chose yogurt and feel satisfied with your healthy breakfast.');
      setFoodChoice(1);
      if (origin === 'shower') {
        finishWithEnding(9, 'After breakfast, you head out and have a very productive day at school.');
      } else {
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    if (choice === 'cake') {
      appendLog('You chose cake for breakfast. You are happy, but your stomach might not be.');
      setFoodChoice(2);
      if (origin === 'shower') {
        finishWithEnding(
          5,
          'You feel refreshed from the shower but crash halfway through the day because of breakfast.'
        );
      } else {
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    const randomChoice = Math.floor(Math.random() * 4);

    if (randomChoice === 0) {
      appendLog('Random pick: Apple. Good call.');
      setFoodChoice(1);
      if (origin === 'shower') {
        finishWithEnding(9, 'After breakfast, you head out and have a very productive day at school.');
      } else {
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    if (randomChoice === 1) {
      appendLog('Random pick: Yogurt. Good call.');
      setFoodChoice(1);
      if (origin === 'shower') {
        finishWithEnding(9, 'After breakfast, you head out and have a very productive day at school.');
      } else {
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    if (randomChoice === 2) {
      appendLog('Random pick: Cake. Tasty, but maybe not the best fuel.');
      if (origin === 'shower') {
        finishWithEnding(4, 'You are happy with the food choice, but your body does not agree.');
      } else {
        setFoodChoice(2);
        appendLog('After breakfast, do you shower first or head straight to school?');
        setScene('afterBreakfast');
      }
      return;
    }

    finishWithEnding(2, 'Random pick: Expired sandwich. You are not making it to class today.');
  }

  function choose(option) {
    if (scene === 'start') {
      if (option === 'sleep') {
        finishWithEnding(1, 'You went back to sleep and slept past class.');
        return;
      }

      if (option === 'class') {
        finishWithEnding(6, "You rushed to class without preparing. That choice did not go well.");
        return;
      }

      if (option === 'shower') {
        appendLog('You get up, shower, and get dressed.');
        appendLog('Now choose breakfast or go straight to school.');
        setScene('afterShower');
        return;
      }

      if (option === 'breakfast') {
        appendLog('You walk to the fridge. Pick apple, yogurt, cake, or random.');
        setScene('fridgeFromStart');
      }
      return;
    }

    if (scene === 'afterShower') {
      if (option === 'breakfast') {
        appendLog('You walk to the fridge. Pick apple, yogurt, cake, or random.');
        setScene('fridgeFromShower');
      } else if (option === 'school') {
        finishWithEnding(
          3,
          'You head to school refreshed from the shower, but hunger hurts your performance.'
        );
      }
      return;
    }

    if (scene === 'fridgeFromShower') {
      handleFridgeChoice(option, 'shower');
      return;
    }

    if (scene === 'fridgeFromStart') {
      handleFridgeChoice(option, 'breakfast');
      return;
    }

    if (scene === 'afterBreakfast') {
      if (option === 'shower') {
        if (foodChoice === 1) {
          finishWithEnding(
            9,
            'You shower, get dressed, and show up feeling great with healthy energy.'
          );
        } else {
          finishWithEnding(
            5,
            'You feel refreshed from the shower, but your unhealthy breakfast causes a crash.'
          );
        }
      } else if (option === 'school') {
        if (foodChoice === 1) {
          finishWithEnding(7, 'You skipped the shower, and your classmates noticed.');
        } else {
          finishWithEnding(8, 'Unhealthy breakfast and no shower. Not your best combo.');
        }
      }
    }
  }

  function resetAdventure() {
    setScene('start');
    setFoodChoice(null);
    setEndingCode(null);
    setStoryLog(['You wake up in your bed, ready to start the day. What do you do first?']);
  }

  function getPrompt() {
    if (scene === 'start') {
      return 'Choose your first move.';
    }

    if (scene === 'afterShower') {
      return 'After showering, do you pick breakfast or school?';
    }

    if (scene === 'fridgeFromShower' || scene === 'fridgeFromStart') {
      return 'Fridge options: Apple, Yogurt, Cake, or Random.';
    }

    if (scene === 'afterBreakfast') {
      return 'After breakfast, do you shower first or go to school?';
    }

    return `Adventure complete: ${getEndingLabel(endingCode)}`;
  }

  function getChoices() {
    if (scene === 'start') {
      return [
        { value: 'sleep', label: 'Sleep' },
        { value: 'shower', label: 'Shower' },
        { value: 'breakfast', label: 'Breakfast' },
        { value: 'class', label: 'Class' }
      ];
    }

    if (scene === 'afterShower') {
      return [
        { value: 'breakfast', label: 'Breakfast' },
        { value: 'school', label: 'School' }
      ];
    }

    if (scene === 'fridgeFromShower' || scene === 'fridgeFromStart') {
      return [
        { value: 'apple', label: 'Apple' },
        { value: 'yogurt', label: 'Yogurt' },
        { value: 'cake', label: 'Cake' },
        { value: 'random', label: 'Random' }
      ];
    }

    if (scene === 'afterBreakfast') {
      return [
        { value: 'shower', label: 'Shower' },
        { value: 'school', label: 'School' }
      ];
    }

    return [];
  }

  const availableChoices = getChoices();

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Morning Routine Adventure</h2>
        <p>
          Interactive version of your choose-your-own-adventure Java application. Explore different paths
          and unlock all 9 endings.
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
        <p className="story-prompt">{getPrompt()}</p>
        <div className="story-choice-grid">
          {availableChoices.map((choice) => (
            <button
              key={choice.value}
              type="button"
              className="sortsorter-secondary-button"
              onClick={() => choose(choice.value)}
            >
              {choice.label}
            </button>
          ))}
          <button type="button" className="run-query-button" onClick={resetAdventure}>
            Restart Adventure
          </button>
        </div>
        {endingCode && <p className="query-label">Current ending: {getEndingLabel(endingCode)}</p>}
      </article>

      <article className="project-card">
        <h3>Story Log</h3>
        <ol className="story-log">
          {storyLog.map((entry, index) => (
            <li key={`${entry}-${index}`}>{entry}</li>
          ))}
        </ol>
      </article>
    </section>
  );
}
