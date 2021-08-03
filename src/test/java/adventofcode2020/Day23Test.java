package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day23Test {

    @Test
    public void part1() {
        assertThat(new Day23().runPart1(List.of("389125467"))).isEqualTo("67384529");
    }

    @Test
    public void part2() {
        assertThat(new Day23().runPart2(List.of("389125467"))).isEqualTo(149245887792L);
    }
}
