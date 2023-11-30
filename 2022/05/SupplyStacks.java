package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class SupplyStacks {

    private static final int NUM_OF_STACKS = 9;

    public static void main(String[] args) {
        readTopSupplies();
    }

    static void readTopSupplies() {
        Map<Integer, LinkedList<Character>> stacks = moveAccordingToInstructions();
        stacks.forEach((key, value) -> System.out.print(value.peekFirst()));
    }

    static Map<Integer, LinkedList<Character>> moveAccordingToInstructions() {
        Supplies supplies = readInput();
        for (Instruction instruction : supplies.instructions()) {
            int quantity = instruction.quantity();
            while (quantity > 0) {
                Character supply = supplies.stacks().get(instruction.from()).get(quantity - 1);
                supplies.stacks().get(instruction.from()).remove(quantity - 1);
                supplies.stacks().get(instruction.to()).addFirst(supply);
                quantity--;
            }
        }
        return supplies.stacks();
    }

    static Supplies readInput() {
        boolean isInstructions = false;
        List<Instruction> instructions = new LinkedList<>();
        Map<Integer, LinkedList<Character>> stacks = new TreeMap<>();
        for (int i = 1; i <= NUM_OF_STACKS; i++) {
            stacks.put(i, new LinkedList<>());
        }
        try (Scanner scanner = new Scanner(new File("src/Day5/input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!isInstructions && "".equals(line)) {
                    isInstructions = true;
                    continue;
                }
                if (isInstructions) {
                    String[] splitLine = line.split(" ");
                    int quantity = Integer.parseInt(splitLine[1]);
                    int from = Integer.parseInt(splitLine[3]);
                    int to = Integer.parseInt(splitLine[5]);
                    instructions.add(new Instruction(quantity, from, to));
                } else {
                    try {
                        int stack = 1;
                        for (int i = 1; i < NUM_OF_STACKS * 4; i += 4) {
                            char element = line.charAt(i);
                            if (element != ' ' && !line.contains("1   2")) {
                                stacks.get(stack).add(line.charAt(i));
                            }
                            stack++;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new Supplies(List.of(), Map.of());
        }
        return new Supplies(instructions, stacks);
    }
}
