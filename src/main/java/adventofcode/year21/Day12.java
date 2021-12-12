package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day12 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {
        return calcuatePaths(input,
                (lst, neighbour) ->  ! lst.contains(neighbour) || isLargeCave(neighbour));
    }

    private boolean isLargeCave(String neighbour) {
        return neighbour.equals(neighbour.toUpperCase());
    }

    private int calcuatePaths(List<String> input, CanInclude inclusionCheck) {
        var system = parseInput(input);

        // create a set of paths
        List<LinkedList<String>> paths = new ArrayList<>();
        List<LinkedList<String>> finishedPaths = new ArrayList<>();

        var initialPath = new LinkedList<String>();
        initialPath.add("start");
        paths.add(initialPath);

        var done = false;

        do {
            List<LinkedList<String>> newPaths = new ArrayList<>();
            paths.forEach(lst -> {
                var lastEntry = lst.getLast();
                var cave = system.get(lastEntry);
                cave.neighbours.forEach(neighbour -> {
                    if (inclusionCheck.canInclude(lst, neighbour)) {
                        var newPath = new LinkedList<String>();
                        newPath.addAll(lst);
                        newPath.addLast(neighbour);

                        if ("end".equals(neighbour)) {
                            finishedPaths.add(newPath);
                        } else {
                            newPaths.add(newPath);
                        }
                    }
                });
            });
            paths = newPaths;
            done = paths.isEmpty();
        } while ( ! done);

        return finishedPaths.size();
    }

    @Override
    public Integer runPart2(List<String> input) {
        CanInclude canInclude = new CanInclude() {
            @Override
            public boolean canInclude(List<String> path, String newentry) {

                if (isLargeCave(newentry) ||  ! path.contains(newentry)) {
                    return true;
                }

                if ( "start".equals(newentry) && path.contains("start")) {
                    return false;
                }

                var countOfElements = path.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                return countOfElements.entrySet().stream().filter(
                        e -> ! isLargeCave( e.getKey())
                ).allMatch(e -> e.getValue() == 1 );

            }
        };
        return calcuatePaths(input, canInclude);
    }

    Map<String, Cave> parseInput(List<String> input) {
        Map<String,Cave> caveSystem = new HashMap<>();

        input.forEach(inp -> {
            var caves = inp.split("-");
            caveSystem.putIfAbsent(caves[0], new Cave(caves[0]));
            caveSystem.putIfAbsent(caves[1], new Cave(caves[1]));

            caveSystem.get(caves[0]).addNeighbour(caves[1]);
            caveSystem.get(caves[1]).addNeighbour(caves[0]);
        });

        return caveSystem;
    }
}

class Cave {
    String name;
    List<String> neighbours;

    public Cave(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }


    public void addNeighbour(String neighbour) {
        this.neighbours.add(neighbour);
    }


}

interface CanInclude {
    boolean canInclude(List<String> path, String newentry);
}

