package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test {

    @Test
    public void runPart1() {

        var input = List.of(
                "1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc"
        );

        assertThat(new Day2().runPart1(input)).isEqualTo(2);
    }

    @Test
    public void runPart2() {

        var input = List.of(
                "1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc"
        );

        assertThat(new Day2().runPart2(input)).isEqualTo(1);
    }
}
