package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day16Test {

    List<String> input2 = List.of(
            "class: 0-1 or 4-19",
            "row: 0-5 or 8-19",
            "seat: 0-13 or 16-19",
            "",
            "your ticket:",
            "11,12,13",
            "",
            "nearby tickets:",
            "3,9,18",
            "15,1,5",
            "5,14,9"
    );

    List<String> input = List.of(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
    );

    @Test
    public void part1() {
        assertThat(new Day16().runPart1(input)).isEqualTo(71);
    }


    @Test
    public void part2() {
        assertThat(new Day16().findProduct(input2, List.of("class", "seat"))).isEqualTo(156);
    }
}
