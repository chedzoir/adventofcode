package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test {

    List<String> input = List.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
    );
    @Test
    public void part1() {
        assertThat(new Day2().runPart1(input)).isEqualTo(150);
    }


    @Test
    public void part2() {
        assertThat(new Day2().runPart2(input)).isEqualTo(900);
    }
}
