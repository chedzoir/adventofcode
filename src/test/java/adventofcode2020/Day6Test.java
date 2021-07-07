package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Test {

    List<String> input = List.of(
            "abc",
            "",
            "a",
            "b",
            "c",
            "",
            "ab",
            "ac",
            "",
            "a",
            "a",
            "a",
            "a",
            "",
            "b"
    );

    @Test
    public void testDay6() {
        assertThat(new Day6().runPart1(input)).isEqualTo(11);
    }

    @Test
    public void testDay6Part2() {
        assertThat(new Day6().runPart2(input)).isEqualTo(6);
    }
}
