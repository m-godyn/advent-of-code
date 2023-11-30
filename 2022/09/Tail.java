package Day9;

import java.util.HashSet;
import java.util.Set;

class Tail {

    private Coordinate currentCoords;
    private final Set<Coordinate> allCoords;

    public Tail(Coordinate currentCoords) {
        this.allCoords = new HashSet<>();
        this.currentCoords = currentCoords;
        this.allCoords.add(this.currentCoords);
    }

    public void setCurrentCoords(Coordinate currentCoords) {
        this.currentCoords = currentCoords;
        this.allCoords.add(this.currentCoords);
    }

    public Coordinate getCurrentCoords() {
        return currentCoords;
    }

    public Set<Coordinate> getAllCoords() {
        return allCoords;
    }
}
