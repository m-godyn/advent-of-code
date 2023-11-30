package Day3;

import java.util.Objects;

record Rucksack (String firstCompartment, String secondCompartment) {

    Rucksack {
        Objects.requireNonNull(firstCompartment);
        Objects.requireNonNull(secondCompartment);
    }
}
