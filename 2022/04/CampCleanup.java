package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class CampCleanup {

    public static void main(String[] args) {
        countDuplicates();
    }

    static void countDuplicates() {
        int numOfDuplicates = 0;
        int numOfOverlapped = 0;
        List<String> pairs = readInput();
        for (String pair : pairs) {
            String[] compared = pair.split(",");
            int[] firstPair = Arrays.stream(compared[0].split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] secondPair = Arrays.stream(compared[1].split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (isOneOfPairsContainedByOther(firstPair, secondPair)) {
                numOfDuplicates++;
                numOfOverlapped++;
            } else if (isOneOfPairsOverlappedByOther(firstPair, secondPair)) {
                numOfOverlapped++;
            }
        }
        System.out.println("Number of contained zones by other: " + numOfDuplicates);
        System.out.println("Number of overlapped zones by other: " + numOfOverlapped);
    }

    static boolean isOneOfPairsContainedByOther(int[] firstPair, int[] secondPair) {
        return firstPair[0] >= secondPair[0] && firstPair[1] <= secondPair[1] ||
                secondPair[0] >= firstPair[0] && secondPair[1] <= firstPair[1];
    }

    static boolean isOneOfPairsOverlappedByOther(int[] firstPair, int[] secondPair) {
        return firstPair[0] >= secondPair[0] && firstPair[0] <= secondPair[1] ||
                firstPair[1] >= secondPair[0] && firstPair[1] <= secondPair[1];
    }


    static List<String> readInput() {
        Path path = Paths.get(".", "src/Day4/input.txt");
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
