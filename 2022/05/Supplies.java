package Day5;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

record Supplies(List<Instruction> instructions, Map<Integer, LinkedList<Character>> stacks) {
    Supplies {
        Objects.requireNonNull(instructions);
        Objects.requireNonNull(stacks);
    }
}
