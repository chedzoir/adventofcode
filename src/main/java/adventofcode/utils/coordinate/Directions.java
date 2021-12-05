package adventofcode.utils.coordinate;

public enum Directions {
    North(new Coordinate2D(0, 1)),
    East(new Coordinate2D(1, 0)),
    South(new Coordinate2D(0, -1)),
    West(new Coordinate2D(-1, 0)),
    Up(new Coordinate2D(0, 1)),
    Right(new Coordinate2D(1, 0)),
    Down(new Coordinate2D(0, -1)),
    Left(new Coordinate2D(-1, 0));

    private final Coordinate2D directionValue;

    Directions(Coordinate2D coordinate) {
        this.directionValue = coordinate;
    }

    public static Directions rotate(Directions facing, int degrees) {
        var vals = new Directions[]{North, East, South, West};

        var currentIndex = 0;
        for (int i = 0; i < vals.length; i++) {
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

    public Coordinate2D getDelta() {
        return this.directionValue;
    }

    public static Directions of(String directionVal) {
        return switch (directionVal) {
            case "U" -> Up;
            case "D" -> Down;
            case "L" -> Left;
            case "R" -> Right;
            default -> null;
        };
    }
}