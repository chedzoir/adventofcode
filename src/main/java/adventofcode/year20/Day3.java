package adventofcode.year20;

import java.util.List;

import adventofcode.utils.Coordinate;
import adventofcode.utils.DayRunner;

public class Day3 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {
        var delta = new Coordinate(3, 1, null);

        return run(input, delta);
    }

    public Long run(List<String> input, Coordinate delta) {
        var currentLocation = new Coordinate(0, 0, null);

        long res = 0;
        do {
            var row = input.get(currentLocation.y());
            var pos = row.charAt(currentLocation.x() % row.length());
            if (pos == '#') {
                res++;
            }
            currentLocation = Coordinate.add(currentLocation, delta);
        } while (currentLocation.y() < input.size());

        return res;

    }

    @Override
    public Long runPart2(List<String> input) {
        var deltas = List.of(
                new Coordinate(1, 1, null),
                new Coordinate(3, 1, null),
                new Coordinate(5, 1, null),
                new Coordinate(7, 1, null),
                new Coordinate(1, 2, null)
        );

       return deltas.stream().map(delta -> run(input, delta))
                .reduce(1L, (a,b) -> a * b);

    }
}
