package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 implements DayRunner<Long, Long> {

    private Long runSeating(List<String> input, boolean part1, int noOfNeighbours) {
        var seating = parseInput(input);

        String[][] newSeating;

        while (true) {
            newSeating = new String[seating.length][seating[0].length];

            for (int i = 0; i < seating.length; i++) {
                for (int j = 0; j < seating[i].length; j++) {
                    var occupiedNeighbours = part1 ?  countNeighbours(seating, i, j) : countLongNeighbours(seating, i,j);
                    if ("L".equals(seating[i][j]) && occupiedNeighbours == 0) {
                        newSeating[i][j] = "#";
                    } else if ("#".equals((seating[i][j])) && occupiedNeighbours >= noOfNeighbours) {
                        newSeating[i][j] = "L";
                    } else {
                        newSeating[i][j] = seating[i][j];
                    }
                }
            }

            if (seatingToString(newSeating).equals(seatingToString(seating))) {
                break;
            }
            seating = newSeating;
        }

        return Arrays.stream(seating).flatMap(Arrays::stream)
                .filter("#"::equals).count();
    }

    @Override
    public Long runPart1(List<String> input) {
        return this.runSeating(input, true, 4);

    }

    String seatingToString(String[][] seating) {
        return String.join("\n", Arrays.stream(seating).map(a -> String.join("", a)).collect(Collectors.toList()));
    }

    int countNeighbours(String[][] seating, int i, int j) {
        int res = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (y == j && x == i) {
                    continue;
                }
                if (x >= 0 && x < seating.length) {
                    if (y >= 0 && y < seating[x].length) {
                        res += "#".equals(seating[x][y]) ? 1 : 0;
                    }
                }
            }
        }
        return res;
    }

    int countLongNeighbours(String[][] seating, int i, int j) {
        int res =0;
        for ( int x = -1; x <= 1; x++) {
            for (int y = -1; y<=1; y++) {
                res += "#".equals(getNeighbour(seating, i, j, x, y)) ? 1 : 0;
            }
        }
        return res;
    }

    String getNeighbour(String[][] seating, int i, int j, int x, int y) {

        if ( x == 0 && y == 0) {
            return null;
        }
        var xVal = i + x;
        var yVal = j + y;
        while(true) {
            if (xVal < 0 || xVal >= seating.length) {
                return null;
            }
            if (yVal < 0 || yVal >= seating[xVal].length) {
                return  null;
            }
            if (! ".".equals(seating[xVal][yVal])) {
                return seating[xVal][yVal];
            }
            xVal += x;
            yVal += y;
        }
    }
    private String[][] parseInput(List<String> input) {
        return input.stream().map(a -> a.split("")).collect(Collectors.toList()).toArray(new String[0][0]);
    }

    @Override
    public Long runPart2(List<String> input) {
        return this.runSeating(input, false, 5);
    }
}
