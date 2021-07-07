package adventofcode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements DayRunner<Integer, Long> {


    @Override
    public Integer runPart1(List<String> input) {


        Integer[] vals = parseInput(input);

        int[] joltCount = {0, 0, 0, 0};
        for (int i = 0; i < vals.length - 1; i++) {
            joltCount[vals[i + 1] - vals[i]]++;
        }

        return joltCount[3] * joltCount[1];
    }

    private Integer[] parseInput(List<String> input) {
        List<Integer> parsedValues = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        parsedValues.add(0);
        parsedValues.add(parsedValues.stream().max(Comparator.naturalOrder()).orElse(0) + 3);

        Integer[] vals = parsedValues.stream().sorted().toArray(Integer[]::new);
        return vals;
    }

    @Override
    public Long runPart2(List<String> input) {

        var vals = parseInput(input);

        var numberOfGroupsOfOnes = new ArrayList<Long>();

        var count = 0L;
        for (int i = 0; i < vals.length - 1; i++) {
            var jolt = vals[i + 1] - vals[i];

            if (jolt == 1) {
                count++;
            } else {
                if (count > 0) {
                    numberOfGroupsOfOnes.add(count);
                    count = 0L;
                }
            }
        }


        return numberOfGroupsOfOnes.stream().map(a -> {

            if (a.equals(1L)) {
                return 1L;
            }
            if (a.equals(2L)) {
                return 2L;
            }

            return a + combination(a-1, 2L);
        }).reduce(1L, (a, b) -> a * b);

    }


    Long combination(Long n, Long m) {
        if (m.equals(n)) {
            return 1L;
        }

        return factorial(n) / (factorial(m) * factorial(n - m));
    }

    Long factorial(Long n) {
        if (n <= 2) {
            return n;
        }
        return n * factorial(n - 1);
    }
}
