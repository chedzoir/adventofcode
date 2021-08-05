package adventofcode.year20;

import adventofcode.year20.Day12;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day12Test {


    List<String> input = List.of(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11"
    );

    @Test
    public void testPart1() {
        assertThat(new Day12().runPart1(input)).isEqualTo(25);
    }

    @Test
    public void testPart2() {
        assertThat(new Day12().runPart2(input)).isEqualTo(286);
    }}
