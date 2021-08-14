package adventofcode.year19;

import adventofcode.utils.DayRunner;

import java.util.List;

public class Day1 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {
       return input.stream().map(Long::parseLong)
                .map(this::simpleFuelCalc)
                .reduce(Long::sum).orElse(-1L);
    }

    @Override
    public Long runPart2(List<String> input) {
        return input.stream().map(Long::parseLong)
                .map(this::recursiveFuelCalc)
                .reduce(Long::sum).orElse(-1L);    }

    public long simpleFuelCalc(long mass) {
        return mass / 3 - 2;
    }

    public long recursiveFuelCalc(long mass) {
        var fuel = simpleFuelCalc(mass);
        if (fuel < 0) {
            return 0;
        }
        return fuel + recursiveFuelCalc(fuel);
    }
}
