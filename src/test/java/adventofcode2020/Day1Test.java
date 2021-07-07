package adventofcode2020;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class Day1Test {

    @Test
    public void part1() {

        var inputs = List.of(
                "1721",
                "979",
                "366",
                "299",
                "675",
                "1456"
        );
        assertThat(new Day1().runPart1(inputs)).isEqualTo(514579);
    }

    @Test
    public void part2() {

        var inputs = List.of(
                "1721",
                "979",
                "366",
                "299",
                "675",
                "1456"
        );
        assertThat(new Day1().runPart2(inputs)).isEqualTo(241861950);
    }
}
