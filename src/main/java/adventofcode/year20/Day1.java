package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 implements DayRunner<Integer, Integer> {

    @Override
    public Integer runPart1(List<String> input) {

        var ints = input.stream().map(Integer::valueOf).collect(Collectors.toList());

        for (var t : ints) {
            if (ints.contains(2020 - t)) {
                return (t * (2020 - t));
            }
        }
        return -1;
    }

    @Override
    public Integer runPart2(List<String> input) {

        var ints = input.stream().map(Integer::valueOf).collect(Collectors.toList());

        for (var t : ints) {
            for (var s : ints) {
                if (t.equals(s)) {
                    continue;
                }
                if (ints.contains(2020 - t - s)) {

                    return t * s * (2020 - t - s);
                }
            }
        }

        return -1;
    }
}
