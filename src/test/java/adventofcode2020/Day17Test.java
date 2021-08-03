package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day17Test {

    List<String> input = List.of(
            ".#.",
            "..#",
            "###");

    @Test
    public void part1() {
        assertThat(new Day17().runPart1(input)).isEqualTo(112);
    }

    @Test
    public void part2() {
        assertThat(new Day17().runPart2(input)).isEqualTo(848);
    }
}
