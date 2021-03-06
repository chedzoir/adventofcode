package adventofcode.year20;

import adventofcode.year20.Day7;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Day7Test {

    List<String> input = List.of(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
    );

    List<String> input2 = List.of(
            "shiny gold bags contain 2 dark red bags.",
                    "dark red bags contain 2 dark orange bags.",
                    "dark orange bags contain 2 dark yellow bags.",
                    "dark yellow bags contain 2 dark green bags.",
                    "dark green bags contain 2 dark blue bags.",
                    "dark blue bags contain 2 dark violet bags.",
                    "dark violet bags contain no other bags."
    );

    @Test
    public void testPart1() {
        assertThat(new Day7().runPart1(input)).isEqualTo(4);
    }

    @Test
    public void testPart2() {
        assertThat(new Day7().runPart2(input)).isEqualTo(32);
        assertThat(new Day7().runPart2(input2)).isEqualTo(126);
    }
}
