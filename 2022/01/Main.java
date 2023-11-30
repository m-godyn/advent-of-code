package Day1;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File input = new File("src/Day1/input.txt");
        CalorieCounter calorieCounter = new CalorieCounter();
        InputHandler inputHandler = new InputHandler();

        List<Elf> elves = inputHandler.readElves(input);
        Elf elfWithMostCalories = calorieCounter.getElfWithMostCalories(elves);
        System.out.println("Elf with most calories: " + elfWithMostCalories.getCalories());
        System.out.println("Calories from 3 elves with most calories: " + calorieCounter.getCalorieAmountFromTop3Elves(elves));
    }
}
