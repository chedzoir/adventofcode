package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;

public class Day10 implements DayRunner<Long, Long> {

    private Map<String, String> commandLookup = Map.of("(", ")", "[", "]", "{", "}", "<", ">");
    private Map<String, Long> points = Map.of(")", 3L, "]", 57L, "}", 1197L, ">", 25137L);
    private Map<String, Long> completeScore = Map.of(")",1L,"]",2L,"}",3L,">",4L);

    @Override
    public Long runPart1(List<String> input) {
        return input.stream()
                .map(line -> validateLine(line)
                        .map(st -> points.get(st))
                        .orElse(0L))
                .reduce(Long::sum).orElse(0L);
    }

    @Override
    public Long runPart2(List<String> input) {
        var res = input.stream().filter(line -> validateLine(line).isEmpty())
                .map(line -> getCompletionScore(line))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return res.get((res.size() - 1) /2);
    }

    public Optional<String> validateLine(String line) {
        LinkedList<String> stack = new LinkedList<>();

        var commands = line.split("");

        for (String command : commands) {
            if (commandLookup.containsKey(command)) {
                stack.push(command);
            } else {
                var lastVal = stack.pop();
                if (!commandLookup.get(lastVal).equals(command)) {
                    return Optional.of(command);
                }
            }
        }

        return Optional.empty();
    }

    public List<String> completeLine(String line) {
        LinkedList<String> stack = new LinkedList<>();

        var commands = line.split("");

        for (String command : commands) {
            if (commandLookup.containsKey(command)) {
                stack.push(command);
            } else {
                stack.pop();
            }
        }

        List<String> res = new ArrayList<>();
        while (! stack.isEmpty()) {
            res.add(commandLookup.get(stack.pop()));
        }
        return res;
    }

    public Long getCompletionScore(String line) {
        return completeLine(line)
                .stream()
                .map(s -> completeScore.get(s))
                .reduce(0L, (a,b) -> a * 5 + b);

    }
}
