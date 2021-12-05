package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {

    @Test
    public void part1() {
        var input = List.of(
                "00100", "11110", "10110", "10111", "10101", "01111",
                "00111", "11100", "10000", "11001", "00010", "01010"
        );

        assertThat(new Day3().runPart1(input)).isEqualTo(198);
    }

    @Test
    public void part2() {
        var input = List.of(
                "00100", "11110", "10110", "10111", "10101", "01111",
                "00111", "11100", "10000", "11001", "00010", "01010"
        );

        assertThat(new Day3().runPart2(input)).isEqualTo(230);
    }
}
