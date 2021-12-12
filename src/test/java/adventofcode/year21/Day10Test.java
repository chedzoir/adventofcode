package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class Day10Test {

    List<String> input = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
    );

    @Test
    public void linesAreParsedCorrectly() {
        var runner = new Day10();

        assertThat(runner.validateLine("[({(<(())[]>[[{[]{<()<>>")).isEmpty();
        assertThat(runner.validateLine("{([(<{}[<>[]}>{[]{[(<()>")).isEqualTo(Optional.of("}"));
        assertThat(runner.validateLine("[[<[([]))<([[{}[[()]]]")).isEqualTo(Optional.of(")"));
        assertThat(runner.validateLine("[{[{({}]{}}([{[{{{}}([]")).isEqualTo(Optional.of("]"));
    }

    @Test
    public void part1() {
        assertThat(new Day10().runPart1(input)).isEqualTo(26397L);
    }

    @Test
    public void linesAreCorrectlyCompleted() {
        var runner = new Day10();

        assertThat(runner.completeLine("[({(<(())[]>[[{[]{<()<>>")).isEqualTo(List.of("}", "}", "]", "]", ")", "}", ")", "]"));
    }

    @Test
    public void correctScore() {
        var runner = new Day10();

        assertThat(runner.getCompletionScore("[({(<(())[]>[[{[]{<()<>>")).isEqualTo(288957L);
        assertThat(runner.getCompletionScore("[(()[<>])]({[<{<<[]>>(")).isEqualTo(5566L);
        assertThat(runner.getCompletionScore("(((({<>}<{<{<>}{[]{[]{}")).isEqualTo(1480781L);
        assertThat(runner.getCompletionScore("{<[[]]>}<{[{[{[]{()[[[]")).isEqualTo(995444L);
        assertThat(runner.getCompletionScore("<{([{{}}[<[[[<>{}]]]>[]]")).isEqualTo(294L);
    }

    @Test
    public void part2() {
        assertThat(new Day10().runPart2(input)).isEqualTo(288957L);
    }
}
