package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day22Test {

    List<String> input = List.of(
            "Player 1:", "9", "2", "6", "3", "1", "",
            "Player 2:", "5", "8", "4", "7", "10"
    );

    @Test
    public void part1() {
        assertThat(new Day22().runPart1(input)).isEqualTo(306);
    }

    @Test
    public void part2() {
        assertThat(new Day22().runPart2(input)).isEqualTo(291);
    }

}
