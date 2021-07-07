package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10Test {

    List<String> input1 = List.of(
            "16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4"
    );

    List<String> input2 = List.of(
            "28", "33", "18", "42", "31", "14", "46", "20", "48", "47",
            "24", "23", "49", "45", "19", "38", "39", "11", "1", "32",
            "25", "35", "8", "17", "7", "9", "4", "2", "34", "10", "3");

    @Test
    public void testPart1() {
        assertThat(new Day10().runPart1(input1)).isEqualTo(7 * 5);
        assertThat(new Day10().runPart1(input2)).isEqualTo(22 * 10);
    }

    @Test
    public void testPart2() {
        assertThat(new Day10().runPart2(input1)).isEqualTo(8);
        assertThat(new Day10().runPart2(input2)).isEqualTo(19208);
    }
}
