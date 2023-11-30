package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * This solution is after both parts of puzzle.
 */
class RockPaperScissors {

    static final Map<Character, Integer> resultPoints;
    static final Map<String, Integer> choicePoints;
    static final Map<Character, String> opponentChoices;
    static final Map<Character, Character> neededResults;

    static {
        resultPoints = Map.of('W', 6, 'D', 3, 'L', 0);
        choicePoints = Map.of("rock", 1, "paper", 2, "scissors", 3);
        opponentChoices = Map.of('A', "rock", 'B', "paper", 'C', "scissors");
        neededResults = Map.of('X', 'L', 'Y', 'D', 'Z', 'W');
    }

    public static void main(String[] args) {
        calculateScore("src/Day2/input.txt");
    }

    static void calculateScore(final String filePathStrategyGuide) {
        List<String> strategyGuide = readStrategyGuide(filePathStrategyGuide);
        final int totalScore = strategyGuide.stream()
                .mapToInt(RockPaperScissors::calculateResult)
                .sum();
        System.out.println("Your result in tournament is: " + totalScore);
    }

    static int calculateResult(final String match) {
        final String opponentChoice = opponentChoices.get(match.charAt(0));
        final char neededResult = neededResults.get(match.charAt(match.length() - 1));
        return resultPoints.get(neededResult) + calculateMatchResult(opponentChoice, neededResult);
    }

    static int calculateMatchResult(final String opponentChoice, final char neededResult) {
        return switch (opponentChoice) {
            case "rock" -> switch (neededResult) {
                case 'W' -> choicePoints.get("paper");
                case 'D' -> choicePoints.get("rock");
                case 'L' -> choicePoints.get("scissors");
                default -> throw new IllegalArgumentException("Unexpected value: " + opponentChoice);
            };
            case "paper" -> switch (neededResult) {
                case 'W' -> choicePoints.get("scissors");
                case 'D' -> choicePoints.get("paper");
                case 'L' -> choicePoints.get("rock");
                default -> throw new IllegalArgumentException("Unexpected value: " + opponentChoice);
            };
            case "scissors" -> switch (neededResult) {
                case 'W' -> choicePoints.get("rock");
                case 'D' -> choicePoints.get("scissors");
                case 'L' -> choicePoints.get("paper");
                default -> throw new IllegalArgumentException("Unexpected value: " + opponentChoice);
            };
            default -> throw new IllegalArgumentException("Unexpected value: " + neededResult);
        };
    }

    static List<String> readStrategyGuide(String filePath) {
        if (filePath == null) {
            return List.of();
        }
        Path path = Paths.get(".", filePath);
        if (Files.notExists(path)) {
            System.err.println("File does not exist!");
            return List.of();
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return List.of();
        }
    }
}
