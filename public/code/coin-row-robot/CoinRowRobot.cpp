#include <algorithm>
#include <iostream>
#include <limits>
#include <random>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

constexpr int MIN_DIMENSION = 3;
constexpr int MAX_DIMENSION = 14;
constexpr double WALL_RATE = 0.26;
constexpr int MAX_COINS = 6;

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
  long long maxCoins = 0;
  int totalCoins = 0;
  std::vector<std::vector<long long>> dp;
  std::vector<Position> path;
};

int readDimension(const std::string &label) {
  while (true) {
    std::cout << label << " (" << MIN_DIMENSION << "-" << MAX_DIMENSION << "): ";
    std::string line;

    if (!std::getline(std::cin, line)) {
      throw std::runtime_error("Input stream closed.");
    }

    std::istringstream parser(line);
    int value = 0;
    char leftover = '\0';

    if (parser >> value && !(parser >> leftover) && value >= MIN_DIMENSION &&
        value <= MAX_DIMENSION) {
      return value;
    }

    std::cout << "Please enter a whole number between " << MIN_DIMENSION << " and "
              << MAX_DIMENSION << ".\n";
  }
}

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
  const int coinCount = std::min(MAX_COINS, static_cast<int>(openCells.size()));

  for (int i = 0; i < coinCount; i++) {
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

  for (const auto &row : maze) {
    for (const Cell &cell : row) {
      plan.totalCoins += cell.coin;
    }
  }

  if (plan.dp[rows - 1][columns - 1] == NEGATIVE_INF) {
    plan.reachable = false;
    return plan;
  }

  plan.reachable = true;
  plan.maxCoins = plan.dp[rows - 1][columns - 1];

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
  std::cout << "Coins are equal value (1 each).\n";
  std::cout << "At most " << MAX_COINS << " coins are scattered each run.\n";
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

      const int rows = readDimension("Rows");
      const int columns = readDimension("Columns");
      const auto maze = generateMaze(rows, columns, rng);
      const RobotPlan plan = buildRobotPlan(maze);

      printMaze(maze);

      if (!plan.reachable) {
        std::cout << "No path was found from start to finish. Try generating again.\n\n";
        continue;
      }

      std::cout << "Total Coins In Maze: " << plan.totalCoins << "\n";
      std::cout << "Maximum Coins Collectable: " << plan.maxCoins << "\n";
      printPath(plan.path);
      printCollectedCoinCells(maze, plan.path);
      printPathOverlay(maze, plan.path);
      std::cout << "\n";
    } catch (const std::exception &error) {
      std::cout << "Error: " << error.what() << "\n\n";
    }
  }
}
