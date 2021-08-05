package adventofcode.year20;

import adventofcode.year20.Day19;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day19Test {

    List<String> input = List.of(
            "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
            "ababbb",
            "bababa",
            "abbbab",
            "aaabbb",
            "aaaabbb"
    );

    List<String> input2 = List.of("42: 9 14 | 10 1",
            "9: 14 27 | 1 26",
            "10: 23 14 | 28 1",
            "1: \"a\"",
            "11: 42 31",
            "5: 1 14 | 15 1",
            "19: 14 1 | 14 14",
            "12: 24 14 | 19 1",
            "16: 15 1 | 14 14",
            "31: 14 17 | 1 13",
            "6: 14 14 | 1 14",
            "2: 1 24 | 14 4",
            "0: 8 11",
            "13: 14 3 | 1 12",
            "15: 1 | 14",
            "17: 14 2 | 1 7",
            "23: 25 1 | 22 14",
            "28: 16 1",
            "4: 1 1",
            "20: 14 14 | 1 15",
            "3: 5 14 | 16 1",
            "27: 1 6 | 14 18",
            "14: \"b\"",
            "21: 14 1 | 1 14",
            "25: 1 1 | 1 14",
            "22: 14 14",
            "8: 42",
            "26: 14 22 | 1 20",
            "18: 15 15",
            "7: 14 5 | 1 21",
            "24: 14 1",
            "",
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba");


    /*
      "0: 4 1 5",
            "1: 2 3 | 3 2",
            "2: 4 4 | 5 5",
            "3: 4 5 | 5 4",
            "4: \"a\"",
            "5: \"b\"",
            "",
     */

    @Test
    public void regexesCorrectlyCreated() {
        Day19 day19 = new Day19();

        day19.parseInput(input);
        assertThat( day19.createRegex(4)).isEqualTo("a");
        assertThat( day19.createRegex(5)).isEqualTo("b");
        assertThat( day19.createRegex(3)).isEqualTo("(ab|ba)");
        assertThat( day19.createRegex(2)).isEqualTo("(aa|bb)");
        assertThat(day19.createRegex(1)).isEqualTo("((aa|bb)(ab|ba)|(ab|ba)(aa|bb))");
        assertThat(day19.createRegex(0)).isEqualTo("(a((aa|bb)(ab|ba)|(ab|ba)(aa|bb))b)");

    }

    @Test
    public void part1() {
        assertThat(new Day19().runPart1(input)).isEqualTo(2);
        assertThat(new Day19().runPart1(input2)).isEqualTo(3);
    }

    @Test
    public void part2() {
        assertThat(new Day19().runPart2(input2)).isEqualTo(12);
    }
}
