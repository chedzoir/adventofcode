package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day9Test {

    List<String> input = List.of(
            "35",
            "20",
            "15",
            "25",
            "47",
            "40",
            "62",
            "55",
            "65",
            "95",
            "102",
            "117",
            "150",
            "182",
            "127",
            "219",
            "299",
            "277",
            "309",
            "576"
    );

    @Test
    public void testPart1() {
        Day9 day9 = new Day9();
        day9.setPreamble(5);
        assertThat(day9.runPart1(input)).isEqualTo(127);
    }

    @Test
    public void testPart2() {
        Day9 day9 = new Day9();
        day9.setPreamble(5);
        assertThat(day9.runPart2(input)).isEqualTo(62);
    }
}
