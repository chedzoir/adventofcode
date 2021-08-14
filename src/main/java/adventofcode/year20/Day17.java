package adventofcode.year20;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;
import adventofcode.utils.coordinate.Coordinate3D;
import adventofcode.utils.coordinate.Coordinate4D;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {

        Map<Coordinate3D, Character> res = new HashMap<>();

        int zInd = 0;
        for (int x = 0; x < input.size(); x++) {
            var row = input.get(x);
            for (int y = 0; y < row.length(); y++) {
                res.put(new Coordinate3D(x, y, zInd), row.charAt(y));
            }
        }

        for (int i = 0; i < 6; i++) {

            var xVals = getBounds(res.keySet().stream().map(Coordinate3D::x).collect(Collectors.toList()));
            var yVals = getBounds(res.keySet().stream().map(Coordinate3D::y).collect(Collectors.toList()));
            var zVals = getBounds(res.keySet().stream().map(Coordinate3D::z).collect(Collectors.toList()));

            Map<Coordinate3D, Character> next = new HashMap<>();
            for (int x = xVals[0] - 1; x <= xVals[1] + 1; x++) {
                for (int y = yVals[0] - 1; y <= yVals[1] + 1; y++) {
                    for (int z = zVals[0] - 1; z <= zVals[1] + 1; z++) {
                        var neighbours = getNeighbours(x, y, z);
                        var activeNeighbours = neighbours.stream().filter(t -> Character.valueOf('#').equals(res.get(t))).count();
                        if (Character.valueOf('#').equals(res.get(new Coordinate3D(x, y, z)))) {
                            if (activeNeighbours == 2 || activeNeighbours == 3) {
                                next.put(new Coordinate3D(x, y, z), '#');
                            }
                        } else {
                            if (activeNeighbours == 3) {
                                next.put(new Coordinate3D(x, y, z), '#');
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

    private List<Coordinate3D> getNeighbours(int x, int y, int z) {
        List<Coordinate3D> res = new ArrayList<>();
        for (int xd = -1; xd <= 1; xd++) {
            for (int yd = -1; yd <= 1; yd++) {
                for (int zd = -1; zd <= 1; zd++) {
                    if (xd != 0 || yd != 0 || zd != 0) {
                        res.add(new Coordinate3D(x + xd, y + yd, z + zd));
                    }
                }
            }
        }
        return res;
    }

    private List<Coordinate4D> getNeighbours(int x, int y, int z, int w) {
        List<Coordinate4D> res = new ArrayList<>();
        for (int xd = -1; xd <= 1; xd++) {
            for (int yd = -1; yd <= 1; yd++) {
                for (int zd = -1; zd <= 1; zd++) {
                    for (int wd = -1; wd <= 1; wd++) {
                        if (xd != 0 || yd != 0 || zd != 0 || wd !=0) {
                            res.add(new Coordinate4D(x + xd, y + yd, z + zd, w + wd));
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
        Map<Coordinate4D, Character> res = new HashMap<>();

        int zInd = 0;
        int wInd = 0;
        for (int x = 0; x < input.size(); x++) {
            var row = input.get(x);
            for (int y = 0; y < row.length(); y++) {
                res.put(new Coordinate4D(x, y, zInd, wInd), row.charAt(y));
            }
        }

        for (int i = 0; i < 6; i++) {

            var xVals = getBounds(res.keySet().stream().map(Coordinate4D::x).collect(Collectors.toList()));
            var yVals = getBounds(res.keySet().stream().map(Coordinate4D::y).collect(Collectors.toList()));
            var zVals = getBounds(res.keySet().stream().map(Coordinate4D::z).collect(Collectors.toList()));
            var wVals = getBounds(res.keySet().stream().map(Coordinate4D::w).collect(Collectors.toList()));

            Map<Coordinate4D, Character> next = new HashMap<>();
            for (int x = xVals[0] - 1; x <= xVals[1] + 1; x++) {
                for (int y = yVals[0] - 1; y <= yVals[1] + 1; y++) {
                    for (int z = zVals[0] - 1; z <= zVals[1] + 1; z++) {
                        for (int w = wVals[0] - 1; w <= wVals[1] + 1; w++) {
                            var neighbours = getNeighbours(x, y, z, w);
                            var activeNeighbours = neighbours.stream()
                                    .filter(t -> Character.valueOf('#').equals(res.get(t)))
                                    .count();
                            if (Character.valueOf('#').equals(res.get(new Coordinate4D(x, y, z, w)))) {
                                if (activeNeighbours == 2 || activeNeighbours == 3) {
                                    next.put(new Coordinate4D(x, y, z, w), '#');
                                }
                            } else {
                                if (activeNeighbours == 3) {
                                    next.put(new Coordinate4D(x, y, z, w), '#');
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
