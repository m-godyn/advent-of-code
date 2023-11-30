package Day1;

class Elf implements Comparable<Elf> {

    private final int calories;

    public Elf(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public int compareTo(Elf e) {
        return this.calories - e.getCalories();
    }
}
