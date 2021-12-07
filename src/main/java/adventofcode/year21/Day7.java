package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day7 implements DayRunner<Integer, Long> {
    @Override
    public Integer runPart1(List<String> input) {
        var inputs = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        var uniqueInputs = new HashSet<>(inputs);

        return uniqueInputs.stream().map(ui -> calculateFuel(inputs, ui)).reduce(Math::min).orElse(0);
    }

    @Override
    public Long runPart2(List<String> input) {
        var inputs = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        var min = inputs.stream().reduce(Math::min).orElse(0);
        var max = inputs.stream().reduce(Math::max).orElse(0) + 1;

        System.out.println("Checking values " + min + " to " + max);
        return LongStream.range(min, max).map(ui -> calculateVaryingFuel(inputs, ui)).reduce(Math::min).orElse(0L);
    }

    public Integer calculateFuel(List<Integer> input, int position) {
        return input.stream().map(inp -> Math.abs(inp - position)).reduce(Integer::sum).orElse(0);
    }

    public Long calculateVaryingFuel(List<Integer> input, long position) {
        return input.stream().map(inp -> {
                    var delta = Math.abs(inp - position);
                    return delta * ( delta + 1 ) / 2;
                })
                .reduce(Long::sum).orElse(0L);
    }
}
