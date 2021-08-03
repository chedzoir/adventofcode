package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day21Test {

    List<String> input = List.of(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
    );

    @Test
    public void part1() {
        assertThat(new Day21().runPart1(input)).isEqualTo(5);
    }

    @Test
    public void part2() {
        assertThat(new Day21().runPart2(input)).isEqualTo("mxmxvkd,sqjhc,fvjkl");
    }
}
