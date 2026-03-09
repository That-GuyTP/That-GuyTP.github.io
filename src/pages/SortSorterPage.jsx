import { useMemo, useState } from 'react';

const MAX_SIZE = 100;
const SORT_TOKEN = 'sort';

function countSortMatches(value) {
  const lowerValue = value.toLowerCase();
  let matches = 0;

  for (let i = 0; i <= lowerValue.length - SORT_TOKEN.length; i += 1) {
    if (lowerValue.slice(i, i + SORT_TOKEN.length) === SORT_TOKEN) {
      matches += 1;
    }
  }

  return matches;
}

function merge(left, right) {
  const merged = [];
  let leftIndex = 0;
  let rightIndex = 0;

  while (leftIndex < left.length && rightIndex < right.length) {
    if (countSortMatches(left[leftIndex]) <= countSortMatches(right[rightIndex])) {
      merged.push(left[leftIndex]);
      leftIndex += 1;
    } else {
      merged.push(right[rightIndex]);
      rightIndex += 1;
    }
  }

  while (leftIndex < left.length) {
    merged.push(left[leftIndex]);
    leftIndex += 1;
  }

  while (rightIndex < right.length) {
    merged.push(right[rightIndex]);
    rightIndex += 1;
  }

  return merged;
}

function mergeSort(items) {
  if (items.length <= 1) {
    return items;
  }

  const middle = Math.floor(items.length / 2);
  const left = mergeSort(items.slice(0, middle));
  const right = mergeSort(items.slice(middle));
  return merge(left, right);
}

export default function SortSorterPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/sortsorter`;
  const sourceFiles = [
    {
      name: 'SortSorter.java',
      url: `${sourceBasePath}/SortSorter.java`
    },
    {
      name: 'SortSorterBE.java',
      url: `${sourceBasePath}/SortSorterBE.java`
    }
  ];

  const [wordInput, setWordInput] = useState('');
  const [words, setWords] = useState([]);
  const [sortedWords, setSortedWords] = useState([]);
  const [wasSorted, setWasSorted] = useState(false);
  const [message, setMessage] = useState('');

  const sortedWithCounts = useMemo(
    () => sortedWords.map((word) => ({ word, count: countSortMatches(word) })),
    [sortedWords]
  );

  function addWord() {
    const trimmedWord = wordInput.trim();

    if (!trimmedWord) {
      setMessage('Enter a word or phrase before adding.');
      return;
    }

    if (words.length >= MAX_SIZE) {
      setMessage(`Maximum size reached (${MAX_SIZE} words).`);
      return;
    }

    setWords((previous) => [...previous, trimmedWord]);
    setWordInput('');
    setMessage('');
  }

  function sortWords() {
    if (words.length === 0) {
      setSortedWords([]);
      setWasSorted(true);
      setMessage("Sorry, you didn't input any strings.");
      return;
    }

    setSortedWords(mergeSort([...words]));
    setWasSorted(true);
    setMessage('');
  }

  function resetAll() {
    setWordInput('');
    setWords([]);
    setSortedWords([]);
    setWasSorted(false);
    setMessage('');
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>SortSorter</h2>
        <p>
          Add words, then sort them by how many times the substring &quot;sort&quot; appears (case
          insensitive). This mirrors the behavior of your Java SortSorter backend logic.
        </p>
      </div>

      <article className="project-card sortsorter-source">
        <h3>Original Java Source</h3>
        <p>
          The playable module runs in JavaScript for the browser, but the original Java files are linked
          below for language display.
        </p>
        <div className="sortsorter-source-links">
          {sourceFiles.map((file) => (
            <a key={file.name} href={file.url} target="_blank" rel="noreferrer">
              {file.name}
            </a>
          ))}
        </div>
      </article>

      <div className="sortsorter-panel">
        <form
          className="sortsorter-input-row"
          onSubmit={(event) => {
            event.preventDefault();
            addWord();
          }}
        >
          <label htmlFor="sortsorter-word">
            Word or phrase
            <input
              id="sortsorter-word"
              type="text"
              value={wordInput}
              onChange={(event) => setWordInput(event.target.value)}
              placeholder='Examples: "sort", "assorted", "SortSorter"'
            />
          </label>

          <div className="sortsorter-actions">
            <button type="submit" className="sortsorter-secondary-button">
              Add
            </button>
            <button type="button" className="run-query-button" onClick={sortWords}>
              Sort
            </button>
            <button type="button" className="sortsorter-secondary-button" onClick={resetAll}>
              Reset
            </button>
          </div>
        </form>

        <p className="query-label">
          Current list size: {words.length}/{MAX_SIZE}
        </p>
        {message && <p className="status-line">{message}</p>}
      </div>

      <div className="sortsorter-columns">
        <article className="project-card sortsorter-list">
          <h3>Input Order</h3>
          {words.length > 0 ? (
            <ol>
              {words.map((word, index) => (
                <li key={`${word}-${index}`}>
                  <span>{word}</span>
                  <span className="sortsorter-count">sort count: {countSortMatches(word)}</span>
                </li>
              ))}
            </ol>
          ) : (
            <p>No words added yet.</p>
          )}
        </article>

        <article className="project-card sortsorter-list">
          <h3>Sorted by Sort Count</h3>
          {!wasSorted ? (
            <p>Press Sort to generate the sorted list.</p>
          ) : sortedWithCounts.length > 0 ? (
            <ol>
              {sortedWithCounts.map((entry, index) => (
                <li key={`${entry.word}-${index}`}>
                  <span>{entry.word}</span>
                  <span className="sortsorter-count">sort count: {entry.count}</span>
                </li>
              ))}
            </ol>
          ) : (
            <p>Sorry, you didn't input any strings.</p>
          )}
        </article>
      </div>
    </section>
  );
}
