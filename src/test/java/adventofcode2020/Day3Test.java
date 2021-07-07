package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {

    List<String> input = List.of(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
    );

    @Test
    public void runPart1() {
        assertThat(new Day3().runPart1(input)).isEqualTo(7);
    }

    @Test
    public void runPart2() {
        assertThat(new Day3().runPart2(input)).isEqualTo(336);
    }
}
