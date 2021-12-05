package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {

    @Test
    public void part1ReturnsCorrectAnswer() {
        List<String> input = List.of(
                "199", "200", "208", "210", "200", "207", "240", "269", "260", "263"
        );

        assertThat(new Day1().runPart1(input)).isEqualTo(7);
    }

    @Test
    public void part2ReturnsCorrectAnswer() {
        List<String> input = List.of(
                "199", "200", "208", "210", "200", "207", "240", "269", "260", "263"
        );

        assertThat(new Day1().runPart2(input)).isEqualTo(5);
    }
}
