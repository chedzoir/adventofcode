package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {

    List<String> input = List.of(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526"
    );

    @Test
    public void step1() {

        var runner = new Day11();

        var grid = runner.parse(input);
        assertThat(runner.runStep(grid)).isEqualTo(0);

        assertThat(grid[0]).isEqualTo(new Integer[]{6, 5, 9, 4, 2, 5, 4, 3, 3, 4});
    }

    @Test
    public void step2() {

        var runner = new Day11();

        var grid = runner.parse(input);
        assertThat(runner.runStep(grid)).isEqualTo(0);
        assertThat(runner.runStep(grid)).isEqualTo(35);

        assertThat(grid[0]).isEqualTo(new Integer[]{8, 8, 0, 7, 4, 7, 6, 5, 5, 5});
    }

    @Test
    public void flashCount() {
        var runner = new Day11();

        var grid = runner.parse(input);
        assertThat(runner.runSteps(grid, 10)).isEqualTo(204);
        assertThat(grid[0]).isEqualTo(new Integer[]{0, 4, 8, 1, 1, 1, 2, 9, 7, 6});
    }

    @Test
    public void part1() {
        assertThat(new Day11().runPart1(input)).isEqualTo(1656);
    }

    @Test
    public void part2() {
        assertThat(new Day11().runPart2(input)).isEqualTo(195);
    }
}
