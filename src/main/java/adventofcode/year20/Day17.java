package adventofcode.year20;

import adventofcode.utils.Coordinate;
import adventofcode.utils.DayRunner;
import adventofcode.utils.FourDCoordinate;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {

        Map<Coordinate, Character> res = new HashMap<>();

        int zInd = 0;
        for (int x = 0; x < input.size(); x++) {
            var row = input.get(x);
            for (int y = 0; y < row.length(); y++) {
                res.put(new Coordinate(x, y, zInd), row.charAt(y));
            }
        }

        for (int i = 0; i < 6; i++) {

            var xVals = getBounds(res.keySet().stream().map(Coordinate::x).collect(Collectors.toList()));
            var yVals = getBounds(res.keySet().stream().map(Coordinate::y).collect(Collectors.toList()));
            var zVals = getBounds(res.keySet().stream().map(Coordinate::z).collect(Collectors.toList()));

            Map<Coordinate, Character> next = new HashMap<>();
            for (int x = xVals[0] - 1; x <= xVals[1] + 1; x++) {
                for (int y = yVals[0] - 1; y <= yVals[1] + 1; y++) {
                    for (int z = zVals[0] - 1; z <= zVals[1] + 1; z++) {
                        var neighbours = getNeighbours(x, y, z);
                        var activeNeighbours = neighbours.stream().filter(t -> Character.valueOf('#').equals(res.get(t))).count();
                        if (Character.valueOf('#').equals(res.get(new Coordinate(x, y, z)))) {
                            if (activeNeighbours == 2 || activeNeighbours == 3) {
                                next.put(new Coordinate(x, y, z), '#');
                            }
                        } else {
                            if (activeNeighbours == 3) {
                                next.put(new Coordinate(x, y, z), '#');
                            }
                        }
                    }
                }
            }

            res.clear();
            res.putAll(next);
        }
        return res.size();
    }

    private List<Coordinate> getNeighbours(int x, int y, int z) {
        List<Coordinate> res = new ArrayList<>();
        for (int xd = -1; xd <= 1; xd++) {
            for (int yd = -1; yd <= 1; yd++) {
                for (int zd = -1; zd <= 1; zd++) {
                    if (xd != 0 || yd != 0 || zd != 0) {
                        res.add(new Coordinate(x + xd, y + yd, z + zd));
                    }
                }
            }
        }
        return res;
    }

    private List<FourDCoordinate> getNeighbours(int x, int y, int z, int w) {
        List<FourDCoordinate> res = new ArrayList<>();
        for (int xd = -1; xd <= 1; xd++) {
            for (int yd = -1; yd <= 1; yd++) {
                for (int zd = -1; zd <= 1; zd++) {
                    for (int wd = -1; wd <= 1; wd++) {
                        if (xd != 0 || yd != 0 || zd != 0 || wd !=0) {
                            res.add(new FourDCoordinate(x + xd, y + yd, z + zd, w + wd));
                        }
                    }
                }
            }
        }
        return res;
    }

    private int[] getBounds(List<Integer> vals) {
        return new int[]{vals.stream().min(Comparator.naturalOrder()).get(), vals.stream().max(Comparator.naturalOrder()).get()};
    }


    @Override
    public Integer runPart2(List<String> input) {
        Map<FourDCoordinate, Character> res = new HashMap<>();

        int zInd = 0;
        int wInd = 0;
        for (int x = 0; x < input.size(); x++) {
            var row = input.get(x);
            for (int y = 0; y < row.length(); y++) {
                res.put(new FourDCoordinate(x, y, zInd, wInd), row.charAt(y));
            }
        }

        for (int i = 0; i < 6; i++) {

            var xVals = getBounds(res.keySet().stream().map(FourDCoordinate::x).collect(Collectors.toList()));
            var yVals = getBounds(res.keySet().stream().map(FourDCoordinate::y).collect(Collectors.toList()));
            var zVals = getBounds(res.keySet().stream().map(FourDCoordinate::z).collect(Collectors.toList()));
            var wVals = getBounds(res.keySet().stream().map(FourDCoordinate::w).collect(Collectors.toList()));

            Map<FourDCoordinate, Character> next = new HashMap<>();
            for (int x = xVals[0] - 1; x <= xVals[1] + 1; x++) {
                for (int y = yVals[0] - 1; y <= yVals[1] + 1; y++) {
                    for (int z = zVals[0] - 1; z <= zVals[1] + 1; z++) {
                        for (int w = wVals[0] - 1; w <= wVals[1] + 1; w++) {
                            var neighbours = getNeighbours(x, y, z, w);
                            var activeNeighbours = neighbours.stream()
                                    .filter(t -> Character.valueOf('#').equals(res.get(t)))
                                    .count();
                            if (Character.valueOf('#').equals(res.get(new FourDCoordinate(x, y, z, w)))) {
                                if (activeNeighbours == 2 || activeNeighbours == 3) {
                                    next.put(new FourDCoordinate(x, y, z, w), '#');
                                }
                            } else {
                                if (activeNeighbours == 3) {
                                    next.put(new FourDCoordinate(x, y, z, w), '#');
                                }
                            }
                        }
                    }
                }
            }

            res.clear();
            res.putAll(next);
        }
        return res.size();
    }
}
