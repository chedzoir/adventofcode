package adventofcode.year20;

import adventofcode.year20.Day18;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day18Test {

    @Test
    public void sumsWork() {
        assertThat(new Day18().evaluate("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(71);
        assertThat(new Day18().evaluate("1 + (2 * 3) + (4 * (5 + 6))")).isEqualTo(51);
        assertThat(new Day18().evaluate("2 * 3 + (4 * 5)")).isEqualTo(26);
        assertThat(new Day18().evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(437);
        assertThat(new Day18().evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(12240);
        assertThat(new Day18().evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(13632);
    }

    @Test
    public void part1() {
        var input = List.of(
                "1 + 2 * 3 + 4 * 5 + 6",
                "1 + (2 * 3) + (4 * (5 + 6))",
                "2 * 3 + (4 * 5)",
                "5 + (8 * 3 + 9 + 3 * 4 * 3)",
                "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
                "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        );
        assertThat(new Day18().runPart1(input)).isEqualTo(71 + 51 + 26 + 437 + 12240 + 13632);
    }

    @Test
    public void additionPrecedenceWorks() {
        assertThat(new Day18().evaluateWithAdditionPrecedence("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(231);
        assertThat(new Day18().evaluateWithAdditionPrecedence("1 + (2 * 3) + (4 * (5 + 6))")).isEqualTo(51);
        assertThat(new Day18().evaluateWithAdditionPrecedence("2 * 3 + (4 * 5)")).isEqualTo(46);
        assertThat(new Day18().evaluateWithAdditionPrecedence("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(1445);
        assertThat(new Day18().evaluateWithAdditionPrecedence("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(669060);
        assertThat(new Day18().evaluateWithAdditionPrecedence("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(23340);
    }


    @Test
    public void part2() {
        var input = List.of(
                "1 + 2 * 3 + 4 * 5 + 6",
                "1 + (2 * 3) + (4 * (5 + 6))",
                "2 * 3 + (4 * 5)",
                "5 + (8 * 3 + 9 + 3 * 4 * 3)",
                "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
                "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        );
        assertThat(new Day18().runPart2(input)).isEqualTo(231 + 51 + 46 + 1445 + 669060 + 23340);
    }
}
