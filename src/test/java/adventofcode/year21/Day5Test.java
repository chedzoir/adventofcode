package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {

    List<String> input = List.of(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
    );

    @Test
    public void part1() {
        assertThat(new Day5().runPart1(input)).isEqualTo(5);
    }


    @Test
    public void part2() {
        assertThat(new Day5().runPart2(input)).isEqualTo(12);
    }
}
