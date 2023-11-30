package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class InputHandler {

    List<Elf> readElves(File input) {
        List<Elf> elves = new ArrayList<>();
        int calories = 0;
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ("".equals(line)) {
                    elves.add(new Elf(calories));
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        return elves;
    }
}
