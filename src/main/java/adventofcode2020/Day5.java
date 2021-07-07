package adventofcode2020;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {

        return input.stream().map(this::getId)
                .max(Comparator.naturalOrder()).get();
    }

    @Override
    public Long runPart2(List<String> input) {

        var seats = input.stream().map(this::getId).collect(Collectors.toSet());

        var min = seats.stream().min(Comparator.naturalOrder()).orElse(0L);
        var max = seats.stream().max(Comparator.naturalOrder()).orElse(10000L);

        for (long cnt = min; cnt < max; cnt++) {
            if ( ! seats.contains(cnt) && seats.contains(cnt - 1) && seats.contains(cnt +1)) {
                return cnt;
            }
        }
        return null;

    }

    public long getId(String passValue) {

        var rowId = passValue.substring(0, 7);
        var column = passValue.substring(7);


        var rowBinary = rowId.replaceAll("F", "0").replaceAll("B", "1");
        var colBinary = column.replaceAll("L", "0").replaceAll("R", "1");

        return Long.parseLong(rowBinary, 2) * 8 + Long.parseLong(colBinary, 2);
    }
}
