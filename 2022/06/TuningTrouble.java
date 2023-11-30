package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

class TuningTrouble {

    public static void main(String[] args) {
        findIndexOfMarker(4);
        findIndexOfMarker(14);
    }

    static void findIndexOfMarker(int distinctCharacters) {
        char[] signal = readInput();
        List<Character> potentialMarker = new LinkedList<>();
        boolean isMarkerFound;
        int counter = distinctCharacters;
        for (char c : signal) {
            if (potentialMarker.size() < distinctCharacters - 1) {
                potentialMarker.add(c);
            } else {
                potentialMarker.add(c);
                isMarkerFound = potentialMarker.stream().distinct().count() == distinctCharacters;
                if (isMarkerFound) {
                    break;
                } else {
                    potentialMarker.remove(0);
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    static char[] readInput() {
        Path path = Paths.get(".", "src/Day6/input.txt");
        if (Files.notExists(path)) {
            System.err.println("File does not exist!");
            return new char[0];
        }
        try {
            return Files.readString(path).toCharArray();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new char[0];
        }
    }
}
