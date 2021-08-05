package adventofcode.utils;

public enum Directions {
    North(new Coordinate(0,1,0)),
    East(new Coordinate(1,0,0)),
    South(new Coordinate(0,-1,0)),
    West(new Coordinate(-1,0,0));

    private Coordinate directionValue;

    Directions(Coordinate coordinate) {
        this.directionValue = coordinate;
    }

    public static Directions rotate(Directions facing, int degrees) {
        var vals = new Directions[] { North, East, South, West};

        var currentIndex =0;
        for (int i=0;i<vals.length;i++ ){
            if (vals[i] == facing) {
                currentIndex = i;
            }
        }

        var newIndex = (currentIndex + degrees / 90) % 4;

        if (newIndex < 0) {
            newIndex += 4;
        }

        return vals[newIndex];
    }

    public Coordinate getDelta() {
        return this.directionValue;
    }
}