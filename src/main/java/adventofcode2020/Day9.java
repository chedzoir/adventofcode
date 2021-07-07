package adventofcode2020;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 implements DayRunner<Long, Long> {
    private int preamble = 25;

    @Override
    public Long runPart1(List<String> input) {

        var vals = input.stream().map(i -> Long.parseLong(i)).collect(Collectors.toList());

        int currentPos = preamble;

        do {
            int start = currentPos - preamble;
            int end = currentPos;
            var valuesToCheck = vals.subList(start, end);
            var valueToFind = vals.get(currentPos);

            var ok = false;
            for (var i = 0; i < valuesToCheck.size(); i++) {
                for (var j = i + 1; j < valuesToCheck.size(); j++) {
                    if (valuesToCheck.get(i) + valuesToCheck.get(j) == valueToFind) {
                        ok = true;
                    }
                }
            }
            if (!ok) {
                break;
            }
            currentPos++;
        } while (true);
        return vals.get(currentPos);
    }

    @Override
    public Long runPart2(List<String> input) {
        Long target = runPart1(input);

        var vals = input.stream().map(i -> Long.parseLong(i)).collect(Collectors.toList());

        for (var i = 0; i < vals.size(); i++) {
            if (vals.get(i) > target) {
                continue;
            }
            Long sum = 0L;
            int pos = i;
            for (var j = 0; j < vals.size() - i; j++) {
                sum += vals.get(i+j);
                if (sum >= target) {
                    pos = i + j;
                    break;
                }
            }
            if (sum.equals(target)) {
                var summingValues = vals.subList(i, pos + 1);
                var max = summingValues.stream().max(Comparator.naturalOrder()).orElse(0L);
                var min = summingValues.stream().min(Comparator.naturalOrder()).orElse(0L);
                return max + min;
            }
        }
        return null;
    }

    public void setPreamble(int preamble) {
        this.preamble = preamble;
    }

    public int getPreamble() {
        return preamble;
    }
}
