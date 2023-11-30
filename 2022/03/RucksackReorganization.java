package Day3;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Day3.Priorities.PRIORITIES;

class RucksackReorganization {

    public static void main(String[] args) {
        calculatePrioritiesForRucksacks();
        calculatePrioritiesForGroups();
    }

    static void calculatePrioritiesForRucksacks() {
        int sumPriorities = 0;
        List<Rucksack> rucksacks = RucksackReader.readRucksacks(new File("src/Day3/rucksacks.txt"));
        for (Rucksack rucksack : rucksacks) {
            Set<Character> h1 = getSetWithChars(rucksack.firstCompartment());
            Set<Character> h2 = getSetWithChars(rucksack.secondCompartment());
            h1.retainAll(h2);
            Character[] itemType = h1.toArray(new Character[0]);
            sumPriorities += PRIORITIES.get(itemType[0]);
        }
        System.out.println("Sum of items priorities: " + sumPriorities);
    }

    static void calculatePrioritiesForGroups() {
        int sumPriorities = 0;
        List<Group> groups = RucksackReader.readGroups(new File("src/Day3/rucksacks.txt"));
        for (Group group : groups) {
            Set<Character> h1 = getSetWithChars(group.rucksacks().get(0));
            Set<Character> h2 = getSetWithChars(group.rucksacks().get(1));
            Set<Character> h3 = getSetWithChars(group.rucksacks().get(2));
            h2.retainAll(h3);
            h1.retainAll(h2);
            Character[] itemType = h1.toArray(new Character[0]);
            sumPriorities += PRIORITIES.get(itemType[0]);
        }
        System.out.println("Sum of group priorities: " + sumPriorities);
    }

    private static Set<Character> getSetWithChars(String string) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            set.add(string.charAt(i));
        }
        return set;
    }
}
