package adventofcode.year20;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Directions;
import adventofcode.utils.coordinate.Coordinate2D;

import java.util.List;

public class Day12 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {

        var facing = Directions.East;
        var location = new Coordinate2D(0, 0);

        for (var a : input) {
            var inst = a.charAt(0);
            var val = Integer.parseInt(a.substring(1));

            switch (inst) {
                case 'N' -> location = location.add(Directions.North.getDelta().scalarMult(val));
                case 'S' -> location = location.add(Directions.South.getDelta().scalarMult(val));
                case 'E' -> location = location.add(Directions.East.getDelta().scalarMult(val));
                case 'W' -> location = location.add(Directions.West.getDelta().scalarMult(val));
                case 'F' -> location = location.add(facing.getDelta().scalarMult(val));
                case 'L' -> facing = Directions.rotate(facing, -1 * val);
                case 'R' -> facing = Directions.rotate(facing, val);
            }
        }
        ;
        return location.manhattenDistance(new Coordinate2D(0, 0));
    }

    @Override
    public Integer runPart2(List<String> input) {

        var wayPoint = new Coordinate2D(10, 1);
        var location = new Coordinate2D(0, 0);

        for (var a : input) {
            var inst = a.charAt(0);
            var val = Integer.parseInt(a.substring(1));
            switch (inst) {
                case 'N' -> wayPoint = new Coordinate2D(wayPoint.x(), wayPoint.y() + val);
                case 'S' -> wayPoint = new Coordinate2D(wayPoint.x(), wayPoint.y() - val);
                case 'E' -> wayPoint = new Coordinate2D(wayPoint.x() + val, wayPoint.y());
                case 'W' -> wayPoint = new Coordinate2D(wayPoint.x() - val, wayPoint.y());
                case 'F' -> location = location.add(wayPoint.scalarMult(val));
                case 'L' -> wayPoint = wayPoint.rotate( -1 * val);
                case 'R' -> wayPoint = wayPoint.rotate( val);
            }
        }
        return location.manhattenDistance(new Coordinate2D(0, 0));
    }
}
