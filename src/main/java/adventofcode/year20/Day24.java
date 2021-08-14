package adventofcode.year20;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 implements DayRunner<Integer, Integer> {

    Map<String, Coordinate2D> deltas = new HashMap<>();

    public Day24() {
        this.deltas.put("w", new Coordinate2D(-2, 0));
        this.deltas.put("e", new Coordinate2D(2, 0));
        this.deltas.put("nw", new Coordinate2D(-1, 1));
        this.deltas.put("sw", new Coordinate2D(-1, -1));
        this.deltas.put("ne", new Coordinate2D(1, 1));
        this.deltas.put("se", new Coordinate2D(1, -1));
    }

    @Override
    public Integer runPart1(List<String> input) {

        Set<Coordinate2D> flippedTiles = getInitialFlippedTiles(input);
        return flippedTiles.size();
    }

    public Set<Coordinate2D> getInitialFlippedTiles(List<String> input) {
        Set<Coordinate2D> flippedTiles = new HashSet<>();

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

    public Set<Coordinate2D> runDay(Set<Coordinate2D> flippedTiles) {

        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        for (Coordinate2D crd : flippedTiles) {
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

        Set<Coordinate2D> coordsToCheck = flippedTiles.stream()
                .flatMap(a -> a.hexNeighbours().stream())
                .collect(Collectors.toSet());

        Set<Coordinate2D> newFlipped = new HashSet<>();

        for (Coordinate2D c : coordsToCheck) {
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

    public Coordinate2D follow(String path) {
        Coordinate2D currentLocation = new Coordinate2D(0, 0);
        var parsedPath = parseInstruction(path);

        for (String dir : parsedPath) {
            currentLocation = currentLocation.add( deltas.get(dir));
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

    public Set<Coordinate2D> runDays(Set<Coordinate2D> tiles, int daysToRun) {
        var currentDay = tiles;
        for (var i=0; i<daysToRun;i++){
            currentDay = runDay(currentDay);
        }
        return currentDay;
    }
}
