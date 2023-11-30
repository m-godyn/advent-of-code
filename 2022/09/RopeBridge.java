package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class RopeBridge {

    public static void main(String[] args) {
        countCoords4Tail();
    }

    static void countCoords4Tail() {
        var instructions = getSplitInstructions();
        var startCoords = new Coordinate(1, 1);
        Head head = new Head(startCoords);
        Tail tail = new Tail(startCoords);
        for (String[] instruction : instructions) {
            moveHeadAndTail(instruction, head, tail);
        }
        System.out.println(tail.getAllCoords().size());
    }

    static void moveHeadAndTail(String[] instructions, Head head, Tail tail) {
        switch (instructions[0]) {
            case "U" -> moveUp(Integer.parseInt(instructions[1]), head, tail);
            case "D" -> moveDown(Integer.parseInt(instructions[1]), head, tail);
            case "L" -> moveLeft(Integer.parseInt(instructions[1]), head, tail);
            case "R" -> moveRight(Integer.parseInt(instructions[1]), head, tail);
        }

    }

    static void moveUp(int quantity, Head head, Tail tail) {
        for (int i = 0; i < quantity; i++) {
            var headCurrentCoords = head.getCurrentCoords();
            head.setCurrentCoords(headCurrentCoords.y() + 1, headCurrentCoords.x());
            if (tailShouldMove(head, tail)) {
                tail.setCurrentCoords(headCurrentCoords);
            }
        }
    }

    static void moveDown(int quantity, Head head, Tail tail) {
        for (int i = 0; i < quantity; i++) {
            var headCurrentCoords = head.getCurrentCoords();
            head.setCurrentCoords(headCurrentCoords.y() - 1, headCurrentCoords.x());
            if (tailShouldMove(head, tail)) {
                tail.setCurrentCoords(headCurrentCoords);
            }
        }
    }

    static void moveLeft(int quantity, Head head, Tail tail) {
        for (int i = 0; i < quantity; i++) {
            var headCurrentCoords = head.getCurrentCoords();
            head.setCurrentCoords(headCurrentCoords.y(), headCurrentCoords.x() - 1);
            if (tailShouldMove(head, tail)) {
                tail.setCurrentCoords(headCurrentCoords);
            }
        }
    }

    static void moveRight(int quantity, Head head, Tail tail) {
        for (int i = 0; i < quantity; i++) {
            var headCurrentCoords = head.getCurrentCoords();
            head.setCurrentCoords(headCurrentCoords.y(), headCurrentCoords.x() + 1);
            if (tailShouldMove(head, tail)) {
                tail.setCurrentCoords(headCurrentCoords);
            }
        }
    }

    static boolean tailShouldMove(Head head, Tail tail) {
        var hCoords = head.getCurrentCoords();
        var tCoords = tail.getCurrentCoords();
        return Math.abs(tCoords.x() - hCoords.x()) > 1 || Math.abs(tCoords.y() - hCoords.y()) > 1;
    }


    static String[][] getSplitInstructions() {
        var input = readInput();
        String[][] instructions = new String[input.size()][2];
        for (int i = 0; i < input.size(); i++) {
            instructions[i] = input.get(i).split(" ");
        }
        return instructions;
    }

    static List<String> readInput() {
        var path = Paths.get(".", "src/Day9/input.txt");
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
