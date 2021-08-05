package adventofcode.year20;

import adventofcode.utils.Coordinate;
import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 implements DayRunner<Integer, Integer> {

    Map<String, Coordinate> deltas = new HashMap<>();

    public Day24() {
        this.deltas.put("w", new Coordinate(-2, 0, 0));
        this.deltas.put("e", new Coordinate(2, 0, 0));
        this.deltas.put("nw", new Coordinate(-1, 1, 0));
        this.deltas.put("sw", new Coordinate(-1, -1, 0));
        this.deltas.put("ne", new Coordinate(1, 1, 0));
        this.deltas.put("se", new Coordinate(1, -1, 0));
    }

    @Override
    public Integer runPart1(List<String> input) {

        Set<Coordinate> flippedTiles = getInitialFlippedTiles(input);
        return flippedTiles.size();
    }

    public Set<Coordinate> getInitialFlippedTiles(List<String> input) {
        Set<Coordinate> flippedTiles = new HashSet<>();

        for (String path : input) {
            var coord = follow(path);
            if (flippedTiles.contains(coord)) {
                flippedTiles.remove(coord);
            } else {
                flippedTiles.add(coord);
            }
        }
        return flippedTiles;
    }


    @Override
    public Integer runPart2(List<String> input) {
        var flipped = getInitialFlippedTiles(input);
        return runDays(flipped, 100).size();

    }

    public Set<Coordinate> runDay(Set<Coordinate> flippedTiles) {

        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        for (Coordinate crd : flippedTiles) {
            if (minX > crd.x()) {
                minX = crd.x();
            }
            if (maxX < crd.x()) {
                maxX = crd.x();
            }
            if (minY > crd.y()) {
                minY = crd.y();
            }
            if (maxY < crd.y()) {
                maxY = crd.y();
            }
        }

        Set<Coordinate> coordsToCheck = flippedTiles.stream()
                .flatMap(a -> a.hexNeighbours().stream())
                .collect(Collectors.toSet());

        Set<Coordinate> newFlipped = new HashSet<>();

        for (Coordinate c : coordsToCheck) {
            var neighbours = c.hexNeighbours();
            var blackNeighbours = neighbours.stream().filter(flippedTiles::contains).count();
            if (flippedTiles.contains(c)) {
                // c is black
                if (blackNeighbours == 1 || blackNeighbours == 2) {
                    newFlipped.add(c);
                }
            } else {
                // c is white
                if (blackNeighbours == 2L) {
                    newFlipped.add(c);
                }
            }
        }

        return newFlipped;
    }

    public Coordinate follow(String path) {
        Coordinate currentLocation = new Coordinate(0, 0, 0);
        var parsedPath = parseInstruction(path);

        for (String dir : parsedPath) {
            currentLocation = Coordinate.add(currentLocation, deltas.get(dir));
        }
        return currentLocation;
    }

    public List<String> parseInstruction(String path) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'w' || path.charAt(i) == 'e') {
                res.add("" + path.charAt(i));
            } else {
                res.add(path.charAt(i) + "" + path.charAt(i + 1));
                i++;
            }
        }

        return res;
    }

    public Set<Coordinate> runDays(Set<Coordinate> tiles, int daysToRun) {
        var currentDay = tiles;
        for (var i=0; i<daysToRun;i++){
            currentDay = runDay(currentDay);
        }
        return currentDay;
    }
}
