package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day11Test {

    List<String> input = List.of(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL"
    );

    @Test
    public void testPart1() {
        assertThat(new Day11().runPart1(input)).isEqualTo(37);
    }

    @Test
    public void testPart2() {
        assertThat(new Day11().runPart2(input)).isEqualTo(26);
    }
}
