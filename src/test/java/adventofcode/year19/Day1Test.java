package adventofcode.year19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day1Test {

    List<String> input = List.of("12", "14", "1969", "100756");

    @Test
    public void simpleFuelCalc() {
        assertThat(new Day1().simpleFuelCalc(12L)).isEqualTo(2);
        assertThat(new Day1().simpleFuelCalc(14L)).isEqualTo(2);
        assertThat(new Day1().simpleFuelCalc(1969L)).isEqualTo(654);
        assertThat(new Day1().simpleFuelCalc(100756L)).isEqualTo(33583);
    }

    @Test
    public void recursiveFuelCalc() {
        assertThat(new Day1().recursiveFuelCalc(12L)).isEqualTo(2);
        assertThat(new Day1().recursiveFuelCalc(14L)).isEqualTo(2);
        assertThat(new Day1().recursiveFuelCalc(1969L)).isEqualTo(966);
        assertThat(new Day1().recursiveFuelCalc(100756L)).isEqualTo(50346);
    }

    @Test
    public void part1() {
        assertThat(new Day1().runPart1(input)).isEqualTo(2 + 2 + 654 + 33583);
    }

    @Test
    public void part2() {
        assertThat(new Day1().runPart2(input)).isEqualTo(2 + 2 + 966 + 50346);
    }
}
