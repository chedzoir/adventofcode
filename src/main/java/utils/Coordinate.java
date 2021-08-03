package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Coordinate(Integer x, Integer y, Integer z) {


    static Integer addInts(Integer a, Integer b) {
        if (a != null && b != null) {
            return a + b;
        }
        return null;
    }

    static Integer difference(Integer a, Integer b) {
        if (a != null && b != null) {
            return Math.abs(a - b);
        }
        return null;
    }

    public static Coordinate add(Coordinate c1, Coordinate c2) {
        return new Coordinate(addInts(c1.x(), c2.x()),
                addInts(c1.y(), c2.y()),
                addInts(c1.z(), c2.z()));
    }

    public static Integer manhattenDistance(Coordinate c1, Coordinate c2) {
        var xDiff = difference(c1.x, c2.x);
        var yDiff = difference(c1.y, c2.y);
        return xDiff + yDiff;
    }

    public static Coordinate scalarMult(Coordinate c1, int val) {
        return new Coordinate(val * c1.x, val * c1.y, val * c1.z);
    }

    public static Coordinate rotate(Coordinate c1, int degree) {
        if (degree == 0) {
            return c1;
        }
        if (degree < 0) {
            return rotate(new Coordinate(-1 * c1.y, c1.x, 0), degree + 90);
        } else {
            return rotate(new Coordinate(c1.y, -1 * c1.x, 0), degree - 90);
        }
    }

    public List<Coordinate> hexNeighbours() {

        List<Coordinate> deltas = List.of(
                new Coordinate(-2, 0, 0),
                new Coordinate(2, 0, 0),
                new Coordinate(-1, 1, 0),
                new Coordinate(-1, -1, 0),
                new Coordinate(1, 1, 0),
                new Coordinate(1, -1, 0)
        );

        return deltas.stream().map(del -> Coordinate.add(this, del))
                .collect(Collectors.toList());
    }
}
