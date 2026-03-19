#include <algorithm>
#include <iostream>
#include <limits>
#include <random>
#include <stdexcept>
#include <string>
#include <vector>

constexpr int MAZE_ROWS = 8;
constexpr int MAZE_COLUMNS = 8;
constexpr double WALL_RATE = 0.26;
constexpr int TOTAL_COINS = 7;

struct Cell {
  bool wall = false;
  int coin = 0;  // Equal-value coins: each collectible coin is worth 1.
};

struct Position {
  int row = 0;
  int column = 0;
};

struct RobotPlan {
  bool reachable = false;
  std::vector<std::vector<long long>> dp;
  std::vector<Position> path;
};

std::vector<Position> buildGuaranteedPath(int rows, int columns, std::mt19937 &rng) {
  std::vector<Position> path;
  path.push_back({0, 0});

  int row = 0;
  int column = 0;
  std::bernoulli_distribution moveRight(0.5);

  while (row < rows - 1 || column < columns - 1) {
    const bool canMoveDown = row < rows - 1;
    const bool canMoveRight = column < columns - 1;

    if (canMoveDown && canMoveRight) {
      if (moveRight(rng)) {
        column++;
      } else {
        row++;
      }
    } else if (canMoveRight) {
      column++;
    } else {
      row++;
    }

    path.push_back({row, column});
  }

  return path;
}

std::vector<std::vector<Cell>> generateMaze(int rows, int columns, std::mt19937 &rng) {
  std::vector<std::vector<Cell>> maze(rows, std::vector<Cell>(columns));
  const std::vector<Position> guaranteedPath = buildGuaranteedPath(rows, columns, rng);
  std::vector<std::vector<bool>> onGuaranteedPath(rows, std::vector<bool>(columns, false));

  for (const Position &step : guaranteedPath) {
    onGuaranteedPath[step.row][step.column] = true;
  }

  std::bernoulli_distribution wallChance(WALL_RATE);

  for (int row = 0; row < rows; row++) {
    for (int column = 0; column < columns; column++) {
      const bool keepOpen = onGuaranteedPath[row][column];
      const bool wall = keepOpen ? false : wallChance(rng);
      maze[row][column] = {wall, 0};
    }
  }

  std::vector<Position> openCells;
  openCells.reserve(rows * columns);

  for (int row = 0; row < rows; row++) {
    for (int column = 0; column < columns; column++) {
      if (!maze[row][column].wall) {
        openCells.push_back({row, column});
      }
    }
  }

  std::shuffle(openCells.begin(), openCells.end(), rng);
  if (static_cast<int>(openCells.size()) < TOTAL_COINS) {
    throw std::runtime_error("Unable to place the required number of coins.");
  }

  for (int i = 0; i < TOTAL_COINS; i++) {
    const Position &cell = openCells[i];
    maze[cell.row][cell.column].coin = 1;
  }

  return maze;
}

RobotPlan buildRobotPlan(const std::vector<std::vector<Cell>> &maze) {
  const int rows = static_cast<int>(maze.size());
  const int columns = static_cast<int>(maze[0].size());
  const long long NEGATIVE_INF = std::numeric_limits<long long>::lowest() / 4;

  RobotPlan plan;
  plan.dp.assign(rows, std::vector<long long>(columns, NEGATIVE_INF));
  std::vector<std::vector<Position>> parent(rows, std::vector<Position>(columns, {-1, -1}));

  for (int row = 0; row < rows; row++) {
    for (int column = 0; column < columns; column++) {
      if (maze[row][column].wall) {
        continue;
      }

      if (row == 0 && column == 0) {
        plan.dp[row][column] = maze[row][column].coin;
        continue;
      }

      long long bestPrevious = NEGATIVE_INF;
      Position bestParent{-1, -1};

      if (row > 0 && plan.dp[row - 1][column] > bestPrevious) {
        bestPrevious = plan.dp[row - 1][column];
        bestParent = {row - 1, column};
      }

      // Prefer left parent on ties so output is deterministic.
      if (column > 0 && plan.dp[row][column - 1] >= bestPrevious) {
        bestPrevious = plan.dp[row][column - 1];
        bestParent = {row, column - 1};
      }

      if (bestPrevious == NEGATIVE_INF) {
        continue;
      }

      plan.dp[row][column] = bestPrevious + maze[row][column].coin;
      parent[row][column] = bestParent;
    }
  }

  if (plan.dp[rows - 1][columns - 1] == NEGATIVE_INF) {
    plan.reachable = false;
    return plan;
  }

  plan.reachable = true;

  int row = rows - 1;
  int column = columns - 1;

  while (row >= 0 && column >= 0) {
    plan.path.insert(plan.path.begin(), {row, column});

    if (row == 0 && column == 0) {
      break;
    }

    const Position previous = parent[row][column];
    row = previous.row;
    column = previous.column;
  }

  return plan;
}

void printMaze(const std::vector<std::vector<Cell>> &maze) {
  std::cout << "Maze Grid (#=wall, 1=coin, 0=empty):\n";

  for (const auto &row : maze) {
    for (size_t column = 0; column < row.size(); column++) {
      if (column > 0) {
        std::cout << " ";
      }

      if (row[column].wall) {
        std::cout << "#";
      } else {
        std::cout << row[column].coin;
      }
    }

    std::cout << "\n";
  }
}

void printPath(const std::vector<Position> &path) {
  std::cout << "Path Coordinates: ";

  for (size_t index = 0; index < path.size(); index++) {
    if (index > 0) {
      std::cout << " -> ";
    }

    std::cout << "(" << path[index].row << "," << path[index].column << ")";
  }

  std::cout << "\n";
}

void printCollectedCoinCells(const std::vector<std::vector<Cell>> &maze,
                             const std::vector<Position> &path) {
  std::cout << "Collected Coin Cells: ";
  bool printedAny = false;

  for (const Position &step : path) {
    if (maze[step.row][step.column].coin == 1) {
      if (printedAny) {
        std::cout << " | ";
      }

      std::cout << "(" << step.row << "," << step.column << ")";
      printedAny = true;
    }
  }

  if (!printedAny) {
    std::cout << "(none)";
  }

  std::cout << "\n";
}

void printPathOverlay(const std::vector<std::vector<Cell>> &maze,
                      const std::vector<Position> &path) {
  const int rows = static_cast<int>(maze.size());
  const int columns = static_cast<int>(maze[0].size());
  std::vector<std::vector<bool>> onPath(rows, std::vector<bool>(columns, false));

  for (const Position &step : path) {
    onPath[step.row][step.column] = true;
  }

  std::cout << "Path Overlay (S=start, F=finish, *=path):\n";

  for (int row = 0; row < rows; row++) {
    for (int column = 0; column < columns; column++) {
      if (maze[row][column].wall) {
        std::cout << "##";
      } else {
        char marker = '.';

        if (row == 0 && column == 0) {
          marker = 'S';
        } else if (row == rows - 1 && column == columns - 1) {
          marker = 'F';
        } else if (onPath[row][column]) {
          marker = '*';
        }

        std::cout << marker << maze[row][column].coin;
      }

      if (column < columns - 1) {
        std::cout << " ";
      }
    }

    std::cout << "\n";
  }
}

int main() {
  std::random_device device;
  std::mt19937 rng(device());

  std::cout << "Coin Row Robot (Auto-Generated Maze)\n";
  std::cout << "Maze size is fixed at " << MAZE_ROWS << "x" << MAZE_COLUMNS << ".\n";
  std::cout << "Each maze contains exactly " << TOTAL_COINS << " equal-value coins (1 each).\n";
  std::cout << "Robot moves right or down only.\n\n";

  while (true) {
    try {
      std::cout << "Type q to quit, or press Enter to generate a maze: ";
      std::string command;

      if (!std::getline(std::cin, command)) {
        return 0;
      }

      if (command == "q" || command == "Q") {
        std::cout << "Goodbye.\n";
        return 0;
      }

      const auto maze = generateMaze(MAZE_ROWS, MAZE_COLUMNS, rng);
      const RobotPlan plan = buildRobotPlan(maze);

      printMaze(maze);

      if (!plan.reachable) {
        std::cout << "No path was found from start to finish. Try generating again.\n\n";
        continue;
      }

      printPath(plan.path);
      printCollectedCoinCells(maze, plan.path);
      printPathOverlay(maze, plan.path);
      std::cout << "\n";
    } catch (const std::exception &error) {
      std::cout << "Error: " << error.what() << "\n\n";
    }
  }
}
