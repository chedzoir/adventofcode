package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day12Test {

    @Test
    public void pathsAsExpected_example1() {
        var input = List.of("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end");

        assertThat(new Day12().runPart1(input)).isEqualTo(10);
    }

    @Test
    public void pathsAsExpected_example2() {
        var input = List.of(
                "dc-end",
                "HN-start",
                "start-kj",
                "dc-start",
                "dc-HN",
                "LN-dc",
                "HN-end",
                "kj-sa",
                "kj-HN",
                "kj-dc"
        );

        assertThat(new Day12().runPart1(input)).isEqualTo(19);
    }

    @Test
    public void pathsAsExpected_example3() {
        var input = List.of(
                "fs-end",
                "he-DX",
                "fs-he",
                "start-DX",
                "pj-DX",
                "end-zg",
                "zg-sl",
                "zg-pj",
                "pj-he",
                "RW-he",
                "fs-DX",
                "pj-RW",
                "zg-RW",
                "start-pj",
                "he-WI",
                "zg-he",
                "pj-fs",
                "start-RW");

        assertThat(new Day12().runPart1(input)).isEqualTo(226);
    }

    @Test
    public void secondPartPathsAsExpected_example1() {
        var input = List.of("start-A", "start-b", "A-c", "A-b", "b-d", "A-end", "b-end");

        assertThat(new Day12().runPart2(input)).isEqualTo(36);
    }

    @Test
    public void secondPartPathsAsExpected_example2() {
        var input = List.of(
                "dc-end",
                "HN-start",
                "start-kj",
                "dc-start",
                "dc-HN",
                "LN-dc",
                "HN-end",
                "kj-sa",
                "kj-HN",
                "kj-dc"
        );

        assertThat(new Day12().runPart2(input)).isEqualTo(103);
    }

    @Test
    public void secondPartPathsAsExpected_example3() {
        var input = List.of(
                "fs-end",
                "he-DX",
                "fs-he",
                "start-DX",
                "pj-DX",
                "end-zg",
                "zg-sl",
                "zg-pj",
                "pj-he",
                "RW-he",
                "fs-DX",
                "pj-RW",
                "zg-RW",
                "start-pj",
                "he-WI",
                "zg-he",
                "pj-fs",
                "start-RW");

        assertThat(new Day12().runPart2(input)).isEqualTo(3509);
    }
}
