#include <cmath>
#include <exception>
#include <iomanip>
#include <iostream>
#include <stdexcept>
#include <string>

namespace {

constexpr double kEpsilon = 1e-9;

class ExpressionParser {
 public:
  explicit ExpressionParser(std::string expression)
      : expression_(std::move(expression)), position_(0) {}

  double Parse() {
    const double value = ParseAddSubtract();
    SkipWhitespace();

    if (position_ != expression_.size()) {
      throw std::runtime_error("Unexpected token at position " +
                               std::to_string(position_ + 1) + ".");
    }

    return value;
  }

 private:
  void SkipWhitespace() {
    while (position_ < expression_.size() &&
           std::isspace(static_cast<unsigned char>(expression_[position_]))) {
      ++position_;
    }
  }

  char Peek() {
    SkipWhitespace();
    return position_ < expression_.size() ? expression_[position_] : '\0';
  }

  bool Match(char expected) {
    if (Peek() == expected) {
      ++position_;
      return true;
    }
    return false;
  }

  double ParseAddSubtract() {
    double value = ParseMultiplyDivide();

    while (true) {
      if (Match('+')) {
        value += ParseMultiplyDivide();
      } else if (Match('-')) {
        value -= ParseMultiplyDivide();
      } else {
        break;
      }
    }

    return value;
  }

  double ParseMultiplyDivide() {
    double value = ParseUnary();

    while (true) {
      if (Match('*')) {
        value *= ParseUnary();
      } else if (Match('/')) {
        const double right = ParseUnary();
        if (std::fabs(right) <= kEpsilon) {
          throw std::runtime_error("Division by zero is not allowed.");
        }
        value /= right;
      } else {
        break;
      }
    }

    return value;
  }

  double ParseUnary() {
    if (Match('+')) {
      return ParseUnary();
    }
    if (Match('-')) {
      return -ParseUnary();
    }
    return ParsePrimary();
  }

  double ParsePrimary() {
    if (Match('(')) {
      const double value = ParseAddSubtract();
      if (!Match(')')) {
        throw std::runtime_error("Missing closing parenthesis.");
      }
      return value;
    }

    return ParseNumber();
  }

  double ParseNumber() {
    SkipWhitespace();
    const std::size_t start = position_;
    bool seenDigit = false;
    bool seenDot = false;

    while (position_ < expression_.size()) {
      const char current = expression_[position_];

      if (std::isdigit(static_cast<unsigned char>(current))) {
        seenDigit = true;
        ++position_;
      } else if (current == '.') {
        if (seenDot) {
          break;
        }
        seenDot = true;
        ++position_;
      } else {
        break;
      }
    }

    if (!seenDigit) {
      throw std::runtime_error("Expected a number at position " +
                               std::to_string(start + 1) + ".");
    }

    const std::string number_text = expression_.substr(start, position_ - start);

    try {
      return std::stod(number_text);
    } catch (const std::exception&) {
      throw std::runtime_error("Invalid number near position " +
                               std::to_string(start + 1) + ".");
    }
  }

  std::string expression_;
  std::size_t position_;
};

struct EquationResult {
  bool is_true;
  double left_value;
  double right_value;
};

EquationResult CheckEquation(const std::string& equation) {
  const std::size_t equals_position = equation.find('=');

  if (equals_position == std::string::npos ||
      equation.find('=', equals_position + 1) != std::string::npos) {
    throw std::runtime_error("Equation must contain exactly one '=' sign.");
  }

  const std::string left_expression = equation.substr(0, equals_position);
  const std::string right_expression = equation.substr(equals_position + 1);

  if (left_expression.empty() || right_expression.empty()) {
    throw std::runtime_error("Both sides of the equation must contain an expression.");
  }

  ExpressionParser left_parser(left_expression);
  ExpressionParser right_parser(right_expression);

  const double left_value = left_parser.Parse();
  const double right_value = right_parser.Parse();
  const bool is_true = std::fabs(left_value - right_value) <= kEpsilon;

  return {is_true, left_value, right_value};
}

}  // namespace

int main() {
  std::cout << "Equation Checker (supports +, -, *, /, parentheses)\n";
  std::cout << "Type an equation like 3*(2+4)=18\n\n";

  std::string equation;
  std::getline(std::cin, equation);

  try {
    const EquationResult result = CheckEquation(equation);
    std::cout << std::fixed << std::setprecision(6);
    std::cout << "Left value : " << result.left_value << '\n';
    std::cout << "Right value: " << result.right_value << '\n';
    std::cout << (result.is_true ? "Equation is true.\n" : "Equation is false.\n");
  } catch (const std::exception& error) {
    std::cerr << "Error: " << error.what() << '\n';
    return 1;
  }

  return 0;
}
