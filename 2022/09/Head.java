package Day9;

class Head {

    private Coordinate currentCoords;

    public Head(Coordinate currentCoords) {
        this.currentCoords = currentCoords;
    }

    public Coordinate getCurrentCoords() {
        return currentCoords;
    }

    public void setCurrentCoords(int y, int x) {
        this.currentCoords = new Coordinate(y, x);
    }
}
