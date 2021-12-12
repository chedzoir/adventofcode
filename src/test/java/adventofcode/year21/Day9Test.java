package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day9Test {

    List<String> input = List.of(
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
    );

    @Test
    public void part1() {
        assertThat(new Day9().runPart1(input)).isEqualTo(15);
    }

    @Test
    public void basins() {

        var basins = new Day9().getBasinSizes(Day9.parse(input));

        assertThat(basins).size().isEqualTo(4);
        assertThat(basins).containsExactly(14L,9L,9L,3L);
    }

    @Test
    public void part2() {
        assertThat(new Day9().runPart2(input)).isEqualTo(1134);
    }

}
