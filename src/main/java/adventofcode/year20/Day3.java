package adventofcode.year20;

import java.util.List;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;

public class Day3 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {
        var delta = new Coordinate2D(3, 1);

        return run(input, delta);
    }

    public Long run(List<String> input, Coordinate2D delta) {
        var currentLocation = new Coordinate2D(0, 0);

        long res = 0;
        do {
            var row = input.get(currentLocation.y());
            var pos = row.charAt(currentLocation.x() % row.length());
            if (pos == '#') {
                res++;
            }
            currentLocation = currentLocation.add( delta);
        } while (currentLocation.y() < input.size());

        return res;

    }

    @Override
    public Long runPart2(List<String> input) {
        var deltas = List.of(
                new Coordinate2D(1, 1),
                new Coordinate2D(3, 1),
                new Coordinate2D(5, 1),
                new Coordinate2D(7, 1),
                new Coordinate2D(1, 2)
        );

       return deltas.stream().map(delta -> run(input, delta))
                .reduce(1L, (a,b) -> a * b);

    }
}
