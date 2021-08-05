package adventofcode.year20;

import adventofcode.utils.Coordinate;
import adventofcode.utils.DayRunner;
import adventofcode.utils.Directions;

import java.util.List;

public class Day12 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {

        var facing = Directions.East;
        var location = new Coordinate(0, 0, 0);

        for (var a : input) {
            var inst = a.charAt(0);
            var val = Integer.parseInt(a.substring(1));

            switch (inst) {
                case 'N' -> location = Coordinate.add(location, Coordinate.scalarMult(Directions.North.getDelta(), val));
                case 'S' -> location = Coordinate.add(location, Coordinate.scalarMult(Directions.South.getDelta(), val));
                case 'E' -> location = Coordinate.add(location, Coordinate.scalarMult(Directions.East.getDelta(), val));
                case 'W' -> location = Coordinate.add(location, Coordinate.scalarMult(Directions.West.getDelta(), val));
                case 'F' -> location = Coordinate.add(location, Coordinate.scalarMult(facing.getDelta(), val));
                case 'L' -> facing = Directions.rotate(facing, -1 * val);
                case 'R' -> facing = Directions.rotate(facing, val);
            }
        };
        return Coordinate.manhattenDistance(new Coordinate(0,0,0), location);
    }

    @Override
    public Integer runPart2(List<String> input) {

        var wayPoint = new Coordinate(10, 1,0);
        var location = new Coordinate(0, 0, 0);

        for (var a : input) {
            var inst = a.charAt(0);
            var val = Integer.parseInt(a.substring(1));
            switch (inst) {
                case 'N' -> wayPoint = new Coordinate(wayPoint.x(), wayPoint.y() + val, 0);
                case 'S' -> wayPoint = new Coordinate(wayPoint.x(), wayPoint.y() - val, 0);
                case 'E' -> wayPoint = new Coordinate(wayPoint.x() + val, wayPoint.y(), 0);
                case 'W' -> wayPoint = new Coordinate(wayPoint.x() - val, wayPoint.y(), 0);
                case 'F' -> location = Coordinate.add(location, Coordinate.scalarMult(wayPoint, val));
                case 'L' -> wayPoint = Coordinate.rotate(wayPoint, -1 * val);
                case 'R' -> wayPoint = Coordinate.rotate(wayPoint, val);
            }
        }
        return Coordinate.manhattenDistance(new Coordinate(0,0,0), location);
    }
}
