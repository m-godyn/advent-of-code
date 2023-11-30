package Day1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class CalorieCounter {

    Elf getElfWithMostCalories(List<Elf> elves) {
        if (elves == null) {
            return null;
        }
        if (elves.isEmpty()) {
            System.err.println("Empty elves list");
            return null;
        }
        return Collections.max(elves);
    }

    int getCalorieAmountFromTop3Elves(List<Elf> elves) {
        return elves.stream()
                .sorted(Comparator.comparing(Elf::getCalories).reversed())
                .limit(3)
                .mapToInt(Elf::getCalories)
                .sum();
    }
}
