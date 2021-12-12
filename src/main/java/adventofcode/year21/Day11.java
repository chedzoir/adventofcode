package adventofcode.year21;

import adventofcode.utils.DayRunner;
import adventofcode.utils.coordinate.Coordinate2D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day11 implements DayRunner<Integer, Integer> {

    @Override
    public Integer runPart1(List<String> input) {
        return runSteps(parse(input), 100);
    }

    @Override
    public Integer runPart2(List<String> input) {
        var grid = parse(input);
        var stepCount =0;
        do {
            runStep(grid);
            stepCount++;
        } while ( ! allZero(grid));

        return stepCount;
    }

    public Integer[][] parse(List<String> input) {
        return input.stream().map(this::parseRow).toArray(Integer[][]::new);
    }

    public Integer[] parseRow(String row) {
        return Arrays.stream(row.split("")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public Integer runStep(Integer[][] grid) {
        Set<Coordinate2D> flashes = new HashSet<>();

        for (int y=0;y<grid.length;y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] += 1;
            }
        }

        boolean done = true;
        do {
            done = true;
            for (int y=0;y<grid.length;y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    var coord = new Coordinate2D(x,y);
                    if (grid[y][x] > 9 && ! flashes.contains(coord)) {
                        flashes.add(coord);
                        done = false;
                        for (int ydelta = -1; ydelta <= 1; ydelta++) {
                            for (int xDelta = -1; xDelta <= 1; xDelta++) {
                                incr(grid, y + ydelta, x + xDelta);
                            }
                        }
                    }
                }
            }
        } while ( ! done );


        flashes.forEach(c -> {
            grid[c.y()][c.x()] = 0;
        });

        return flashes.size();
    }

    private void incr(Integer[][] grid, int y, int x) {
        if ( y < 0 || y >= grid.length) {
            return;
        }
        if ( x < 0 || x >= grid[y].length) {
            return;
        }
        grid[y][x] += 1;
    }

    public Integer runSteps(Integer[][] grid, int i) {
        var res = 0;
        for (int cnt =0;cnt < i;cnt++){
            res += runStep(grid);
        }
        return res;
    }

    public boolean allZero(Integer[][] grid) {
        for (int y=0;y<grid.length;y++){
            for (int  x=0;x<grid[y].length;x++){
                if (grid[y][x] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
