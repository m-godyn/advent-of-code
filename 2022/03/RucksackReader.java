package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class RucksackReader {

    static List<Rucksack> readRucksacks(File input) {
        if (input == null) {
            return List.of();
        }
        List<Rucksack> rucksacks = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                final String rsStr = scanner.nextLine();
                rucksacks.add(readRucksack(rsStr));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return List.of();
        }
        return rucksacks;
    }

    static List<Group> readGroups(File input) {
        if (input == null) {
            return List.of();
        }
        List<Group> groups = new ArrayList<>();
        List<String> rucksacks = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            int counter = 0;
            while (scanner.hasNextLine()) {
                counter++;
                final String rsStr = scanner.nextLine();
                rucksacks.add(rsStr);
                if (counter == 3) {
                    groups.add(new Group(rucksacks));
                    rucksacks = new ArrayList<>();
                    counter = 0;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return List.of();
        }
        return groups;
    }

    private static Rucksack readRucksack(final String input) {
        final int mid = input.length() / 2;
        return new Rucksack(input.substring(0, mid), input.substring(mid));
    }
}
