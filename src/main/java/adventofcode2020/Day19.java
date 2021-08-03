package adventofcode2020;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

record Rule(Integer id, String val, Integer[][] subRules) {
}

public class Day19 implements DayRunner<Long, Long> {

    Map<Integer, Rule> rules = new HashMap<>();
    List<String> messages = new ArrayList<>();

    @Override
    public Long runPart1(List<String> input) {

        parseInput(input);

        var regEx = createRegex(0);

        var patt = Pattern.compile(regEx);

        System.out.println(regEx);
        return messages.stream().filter(a -> patt.matcher(a).matches()).count();
    }

    public String createRegex(int ruleNo) {
        var rule = rules.get(ruleNo);

        if (rule.val() != null) {
            return rule.val();
        }

        var regexs = Arrays.stream(rule.subRules()).map(
                r -> Arrays.stream(r).map(this::createRegex).reduce("", (a, b) -> a + b)
        ).collect(Collectors.toList());

        return "(" + String.join("|", regexs) + ")";
    }

    @Override
    public Long runPart2(List<String> input) {

        // 8 is a number of 42s
        // 11 is 42 42 42 ... 31 31 31
        parseInput(input);

        Set<String> matching = new HashSet<>();
        for (var eightCount = 1; eightCount < 30; eightCount++) {
            for (var elevenCount = 1; elevenCount < 30; elevenCount++) {

                var eights = IntStream.range(0, eightCount).map(a -> 42).boxed().toArray(Integer[]::new);

                var elvens42s = IntStream.range(0, elevenCount).map(a -> 42).boxed();
                var elvens31s = IntStream.range(0, elevenCount).map(a -> 31).boxed();

                var elevens = Stream.concat(elvens42s, elvens31s).toArray(Integer[]::new);

                var rule8 = new Rule(8, null, new Integer[][]{eights});
                var rule11 = new Rule(11, null, new Integer[][]{elevens});

                rules.put(8, rule8);
                rules.put(11, rule11);

                var regEx = createRegex(0);

                var patt = Pattern.compile(regEx);

                matching.addAll(messages.stream()
                        .filter(a -> patt.matcher(a).matches())
                        .collect(Collectors.toSet()));
            }
        }
        return (long) matching.size();
    }

    public void parseInput(List<String> input) {
        for (var row : input) {
            if (row.contains(":")) {
                var id = Integer.parseInt(row.substring(0, row.indexOf(":")));
                var details = row.split(":")[1].trim();
                if (details.contains("\"")) {
                    rules.put(id, new Rule(id, details.substring(1, 2), null));
                } else {
                    var nextRules = Arrays.stream(details.split("\\|")).map(
                            v -> Arrays.stream(v.trim().split(" ")).map(Integer::parseInt).toArray(Integer[]::new)
                    ).toArray(Integer[][]::new);
                    rules.put(id, new Rule(id, null, nextRules));
                }
            } else if (!row.isEmpty()) {
                messages.add(row);
            }
        }
    }


}
