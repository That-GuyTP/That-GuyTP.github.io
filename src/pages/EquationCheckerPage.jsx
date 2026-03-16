import { useMemo, useState } from 'react';

const EPSILON = 1e-9;

function formatNumber(value) {
  if (!Number.isFinite(value)) {
    return 'N/A';
  }

  return value.toFixed(6).replace(/\.?0+$/, '');
}

function createToken(raw, index) {
  if (/^\d*\.?\d+$/.test(raw) || /^\d+\.$/.test(raw)) {
    return {
      type: 'number',
      value: Number(raw),
      raw,
      index
    };
  }

  return {
    type: 'symbol',
    value: raw,
    raw,
    index
  };
}

function tokenize(expression) {
  const tokens = [];
  let index = 0;

  while (index < expression.length) {
    const char = expression[index];

    if (/\s/.test(char)) {
      index += 1;
      continue;
    }

    if ('()+-*/'.includes(char)) {
      tokens.push(createToken(char, index));
      index += 1;
      continue;
    }

    if (/\d|\./.test(char)) {
      const start = index;
      let dotCount = 0;

      while (index < expression.length && /[\d.]/.test(expression[index])) {
        if (expression[index] === '.') {
          dotCount += 1;
        }

        if (dotCount > 1) {
          throw new Error(`Invalid number near position ${start + 1}.`);
        }

        index += 1;
      }

      const rawNumber = expression.slice(start, index);

      if (rawNumber === '.') {
        throw new Error(`Invalid number near position ${start + 1}.`);
      }

      const token = createToken(rawNumber, start);

      if (!Number.isFinite(token.value)) {
        throw new Error(`Invalid number near position ${start + 1}.`);
      }

      tokens.push(token);
      continue;
    }

    throw new Error(`Unexpected character "${char}" at position ${index + 1}.`);
  }

  return tokens;
}

function evaluateExpression(expression) {
  const tokens = tokenize(expression);
  let position = 0;

  function peek() {
    return tokens[position] ?? null;
  }

  function consume() {
    const token = tokens[position] ?? null;
    position += 1;
    return token;
  }

  function parsePrimary() {
    const token = peek();

    if (!token) {
      throw new Error('Expression ended early.');
    }

    if (token.type === 'number') {
      consume();
      return token.value;
    }

    if (token.value === '(') {
      consume();
      const innerValue = parseAddSubtract();
      const closing = consume();

      if (!closing || closing.value !== ')') {
        throw new Error('Missing closing parenthesis.');
      }

      return innerValue;
    }

    throw new Error(`Unexpected token "${token.raw}" at position ${token.index + 1}.`);
  }

  function parseUnary() {
    const token = peek();

    if (token && token.type === 'symbol' && (token.value === '+' || token.value === '-')) {
      consume();
      const value = parseUnary();
      return token.value === '-' ? -value : value;
    }

    return parsePrimary();
  }

  function parseMultiplyDivide() {
    let value = parseUnary();

    while (true) {
      const token = peek();

      if (!token || token.type !== 'symbol' || (token.value !== '*' && token.value !== '/')) {
        break;
      }

      consume();
      const right = parseUnary();

      if (token.value === '*') {
        value *= right;
      } else {
        if (Math.abs(right) <= EPSILON) {
          throw new Error('Division by zero is not allowed.');
        }

        value /= right;
      }
    }

    return value;
  }

  function parseAddSubtract() {
    let value = parseMultiplyDivide();

    while (true) {
      const token = peek();

      if (!token || token.type !== 'symbol' || (token.value !== '+' && token.value !== '-')) {
        break;
      }

      consume();
      const right = parseMultiplyDivide();
      value = token.value === '+' ? value + right : value - right;
    }

    return value;
  }

  const value = parseAddSubtract();

  if (position < tokens.length) {
    const unexpectedToken = tokens[position];
    throw new Error(
      `Unexpected token "${unexpectedToken.raw}" at position ${unexpectedToken.index + 1}.`
    );
  }

  return value;
}

function checkEquation(rawEquation) {
  const equation = rawEquation.trim();

  if (!equation) {
    return {
      state: 'error',
      message: 'Enter an equation before checking.',
      leftValue: null,
      rightValue: null
    };
  }

  const equalCount = (equation.match(/=/g) ?? []).length;

  if (equalCount !== 1) {
    return {
      state: 'error',
      message: 'Use exactly one "=" sign in the equation.',
      leftValue: null,
      rightValue: null
    };
  }

  const [leftExpression, rightExpression] = equation.split('=').map((part) => part.trim());

  if (!leftExpression || !rightExpression) {
    return {
      state: 'error',
      message: 'Both sides of the equation must contain an expression.',
      leftValue: null,
      rightValue: null
    };
  }

  try {
    const leftValue = evaluateExpression(leftExpression);
    const rightValue = evaluateExpression(rightExpression);
    const difference = Math.abs(leftValue - rightValue);
    const isTrue = difference <= EPSILON;

    return {
      state: isTrue ? 'success' : 'warning',
      message: isTrue ? 'Equation is true.' : 'Equation is false.',
      leftValue,
      rightValue,
      difference
    };
  } catch (error) {
    return {
      state: 'error',
      message: error instanceof Error ? error.message : 'Unable to evaluate equation.',
      leftValue: null,
      rightValue: null
    };
  }
}

export default function EquationCheckerPage() {
  const sourceBasePath = `${import.meta.env.BASE_URL}code/equation-checker`;
  const sourceFiles = [
    {
      name: 'EquationChecker.cpp',
      url: `${sourceBasePath}/EquationChecker.cpp`
    }
  ];

  const [equation, setEquation] = useState('');
  const [result, setResult] = useState(null);

  const statusClassName = useMemo(() => {
    if (!result) {
      return '';
    }

    if (result.state === 'error') {
      return 'error';
    }

    if (result.state === 'warning') {
      return 'warning';
    }

    return 'success';
  }, [result]);

  function onSubmit(event) {
    event.preventDefault();
    setResult(checkEquation(equation));
  }

  function onReset() {
    setEquation('');
    setResult(null);
  }

  return (
    <section className="page-panel">
      <div className="hero-card">
        <p className="eyebrow">Playable Module</p>
        <h2>Equation Checker</h2>
        <p>
          Enter an equation with arithmetic operators and parentheses. This interactive version checks
          whether both sides evaluate to the same value.
        </p>
      </div>

      <article className="project-card">
        <h3>Original C++ Source</h3>
        <p>
          The browser module mirrors the C++ logic so visitors can test it live while still viewing the
          source file.
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
        <form className="program-form-grid equation-checker-form" onSubmit={onSubmit}>
          <label htmlFor="equation-checker-input">
            Equation
            <input
              id="equation-checker-input"
              type="text"
              value={equation}
              onChange={(event) => setEquation(event.target.value)}
              placeholder="Examples: 3*(2+4)=18 or (8/2)+1=5"
            />
          </label>

          <div className="program-actions">
            <button type="submit" className="run-query-button">
              Check Equation
            </button>
            <button type="button" className="sortsorter-secondary-button" onClick={onReset}>
              Reset
            </button>
          </div>
        </form>

        <p className="query-label">Supported operators: +, -, *, / and parentheses.</p>

        {result && (
          <p className={`status-line ${statusClassName}`.trim()}>
            {result.message}
          </p>
        )}
      </article>

      <div className="program-results-grid">
        <article className="project-card">
          <h3>Left Side Value</h3>
          <p className="program-output">
            {result?.leftValue == null ? 'Run a check to view output.' : formatNumber(result.leftValue)}
          </p>
        </article>

        <article className="project-card">
          <h3>Right Side Value</h3>
          <p className="program-output">
            {result?.rightValue == null
              ? 'Run a check to view output.'
              : formatNumber(result.rightValue)}
          </p>
        </article>
      </div>

      <article className="project-card">
        <h3>Difference</h3>
        <p className="program-output">
          {result?.difference === undefined ? 'Run a check to view output.' : formatNumber(result.difference)}
        </p>
      </article>
    </section>
  );
}
