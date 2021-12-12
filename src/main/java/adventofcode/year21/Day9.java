package adventofcode.year21;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;
import adventofcode.utils.coordinate.Directions;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 implements DayRunner<Integer, Long> {

    static List<List<Integer>> parse(List<String> input) {
        return  input.stream()
                .map(line ->
                        Arrays.stream(line.split(""))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
    @Override
    public Integer runPart1(List<String> input) {

        var map = parse(input);

        System.out.println(map);

        var lowPoints = getLowPoints(map);

        return lowPoints.stream().map(i -> map.get(i.y()).get(i.x()) + 1).reduce(Integer::sum).orElse(-1);
    }

    private List<Coordinate2D> getLowPoints(List<List<Integer>> map) {
        var res = new ArrayList<Coordinate2D>();

        for (int y=0; y<map.size();y++) {
            for (int x =0; x<map.get(y).size();x++){
                var thisVal = map.get(y).get(x);
                var up = getNeighbours(map, x, y - 1);
                var down = getNeighbours(map, x, y + 1);
                var left = getNeighbours(map, x -1, y);
                var right = getNeighbours(map, x + 1, y);

                if ( thisVal < up && thisVal < down
                        && thisVal < left && thisVal < right) {
                    res.add(new Coordinate2D(x,y));
                }
            }
        }

        return res;
    }

    public List<Long> getBasinSizes(List<List<Integer>> map) {
        var lowPoints = getLowPoints(map);

        return lowPoints.stream().map(p -> (long)getBasin(map, p).size())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private Set<Coordinate2D> getBasin(List<List<Integer>> map, Coordinate2D lowPoint) {

        Set<Coordinate2D> res = new HashSet<>();

        res.add(lowPoint);

        var done = false;
        while ( ! done) {
            var newPoints = res.stream().flatMap(point -> getHigherNeighbours(map, point).stream())
                    .collect(Collectors.toList());

            if ( res.containsAll(newPoints)) {
                done = true;
            } else {
                res.addAll(newPoints);
            }
        }
        return res;
    }

    private List<Coordinate2D> getHigherNeighbours(List<List<Integer>> map, Coordinate2D lowPoint) {

        var coords = List.of(
                lowPoint.add(Directions.Up.getDelta()),
                lowPoint.add(Directions.Down.getDelta()),
                lowPoint.add(Directions.Right.getDelta()),
                lowPoint.add(Directions.Left.getDelta())
        );

        return coords.stream().filter(c -> getNeighbours(map, c.x(), c.y()) < 9)
                .collect(Collectors.toList());
    }

    private Integer getNeighbours(List<List<Integer>> map, int x, int y) {
        if (y < 0 || y >= map.size()) {
            return 100;
        }
        if ( x < 0 || x >= map.get(y).size() ) {
            return 100;
        }

        return map.get(y).get(x);
    }


    @Override
    public Long runPart2(List<String> input) {
        var basins = getBasinSizes(parse(input));

        return basins.get(0) * basins.get(1) * basins.get(2);
    }
}
