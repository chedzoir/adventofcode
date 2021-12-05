package adventofcode.year21;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day5 implements DayRunner<Long, Long> {


    @Override
    public Long runPart1(List<String> input) {

        List<Coordinate2D[]> coords = parseInput(input);

        Map<Coordinate2D, Integer> grid = new HashMap<>();

        coords.stream().filter(c ->
                        Objects.equals(c[0].x(), c[1].x()) ||
                                Objects.equals(c[0].y(), c[1].y()))
                .forEach(c -> {
                    // vertical?
                    if (Objects.equals(c[0].y(), c[1].y())) {
                        var start = Math.min(c[0].x(), c[1].x());
                        var end = Math.max(c[0].x(), c[1].x());
                        var y = c[0].y();

                        for (int x = start; x <= end; x++) {
                            var coord = new Coordinate2D(x, y);
                            grid.put(coord, grid.getOrDefault(coord, 0) + 1);
                        }
                    } else {
                        var start = Math.min(c[0].y(), c[1].y());
                        var end = Math.max(c[0].y(), c[1].y());
                        var x = c[0].x();
                        for (int y = start; y <= end; y++) {
                            var coord = new Coordinate2D(x, y);
                            grid.put(coord, grid.getOrDefault(coord, 0) + 1);
                        }
                    }
                });

        return grid.values().stream().filter(c -> c > 1).count();
    }

    private List<Coordinate2D[]> parseInput(List<String> input) {
        return input.stream().map(coor -> {
            var coors = coor.split(" ");
            var start = Coordinate2D.from(coors[0]);
            var end = Coordinate2D.from(coors[2]);

            return new Coordinate2D[]{start, end};
        }).collect(Collectors.toList());
    }

    @Override
    public Long runPart2(List<String> input) {

        List<Coordinate2D[]> coords = parseInput(input);

        Map<Coordinate2D, Integer> grid = new HashMap<>();

        coords.forEach(c -> {
            // vertical?

            if (Objects.equals(c[0].y(), c[1].y())) {
                var start = Math.min(c[0].x(), c[1].x());
                var end = Math.max(c[0].x(), c[1].x());
                var y = c[0].y();

                for (int x = start; x <= end; x++) {
                    var coord = new Coordinate2D(x, y);
                    grid.put(coord, grid.getOrDefault(coord, 0) + 1);
                }
            } else if (Objects.equals(c[0].x(), c[1].x())) {
                var start = Math.min(c[0].y(), c[1].y());
                var end = Math.max(c[0].y(), c[1].y());
                var x = c[0].x();
                for (int y = start; y <= end; y++) {
                    var coord = new Coordinate2D(x, y);
                    grid.put(coord, grid.getOrDefault(coord, 0) + 1);
                }
            } else {
                var start = c[0].x() < c[1].x() ? c[0] : c[1];
                var end = c[0].x() < c[1].x() ? c[1] : c[0];
                var yDelta = start.y()  > end.y() ? -1 : 1;
                var y = start.y();

                for (int x = start.x(); x <= end.x(); x++) {
                    var coord = new Coordinate2D(x, y);
                    grid.put(coord, grid.getOrDefault(coord, 0) + 1);
                    y += yDelta;
                }
            }
        });

        return grid.values().stream().filter(c -> c > 1).count();
    }
}
