package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {
        return runGame(input, 2020L);
    }

    private Long runGame(List<String> input, long limit) {
        Map<Long, Long[]> recall = new HashMap<>();

        var initVals = Arrays.stream(input.get(0).split(","))
                .map(a -> Long.parseLong(a)).collect(Collectors.toList());

        for (int i = 0; i < initVals.size(); i++) {
            recall.put(initVals.get(i), new Long[]{null, (long) (i + 1)});
        }

        Long lastValue = initVals.get(initVals.size() - 1);

        for (long i = initVals.size() + 1; i <= limit; i++) {

            var whenLastCalled = recall.get(lastValue);
            var nextNumber = 0L;
            if (whenLastCalled[0] != null) {
                nextNumber = whenLastCalled[1] - whenLastCalled[0];
            }

            recall.putIfAbsent(nextNumber, new Long[]{null, null});
            var nextLastCalled = recall.get(nextNumber);
            nextLastCalled[0] = nextLastCalled[1];
            nextLastCalled[1] = i;
            lastValue = nextNumber;
        }
        return lastValue;
    }

    @Override
    public Long runPart2(List<String> input) {
        return runGame(input, 30000000L);
    }
}
