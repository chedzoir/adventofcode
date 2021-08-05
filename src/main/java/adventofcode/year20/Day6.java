package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 implements DayRunner<Long, Long> {

    List<Set<String>> parseDataPart1(List<String> input) {

        List<Set<String>> res = new ArrayList<>();

        Set<String> chars = new HashSet<>();

        for (var row : input) {
            if (row.isEmpty()) {
                res.add(chars);
                chars = new HashSet<>();
            } else {
                chars.addAll(Arrays.stream(row.split("")).collect(Collectors.toSet()));
            }
        }

        res.add(chars);

        return res;
    }


    @Override
    public Long runPart1(List<String> input) {
        return (long) parseDataPart1(input).stream().map(a -> a.size()).reduce(0, (a, b) -> a + b);
    }


    List<List<String>> parseDataPart2(List<String> input) {

        List<List<String>> res = new ArrayList<>();

        List<String> answers = new ArrayList<>();

        for (var row : input) {
            if (row.isEmpty()) {
                res.add(answers);
                answers = new ArrayList<>();
            } else {
                answers.add(row);
            }
        }

        res.add(answers);

        return res;
    }

    @Override
    public Long runPart2(List<String> input) {

        var groupAnswers = parseDataPart2(input);

        List<Character> answers = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            answers.add((char) (i + 'a'));
        }

        return groupAnswers.stream().map(a ->
                answers.stream().filter(ans -> a.stream().allMatch(p -> p.contains("" + ans))).count()
        ).reduce(0L, Long::sum);
    }
}
