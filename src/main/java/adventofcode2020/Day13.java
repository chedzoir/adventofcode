package adventofcode2020;

import utils.Maths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 implements DayRunner<Integer, Long>{
    @Override
    public Integer runPart1(List<String> input) {

        Integer starttime = Integer.parseInt(input.get(0));
        String[] timetable = input.get(1).split(",");

        var buses = Arrays.stream(timetable)
                .filter(b -> ! "x".equals(b))
                .map(b -> Integer.parseInt(b))
        .collect(Collectors.toList());

        var times = buses.stream().map(b -> (starttime / b +1) * b).collect(Collectors.toList());

        var departureTime = times.stream().min(Comparator.naturalOrder()).orElse(0);
        var index = times.indexOf(departureTime);
        var bus = buses.get(index);

        return bus * (departureTime - starttime);
    }

    @Override
    public Long runPart2(List<String> input) {
        String[] timetable = input.get(1).split(",");

        List<Long> buses = new ArrayList<>();
        List<Long> remainders = new ArrayList<>();

        for (int i=0;i<timetable.length;i++) {
            if ( "x".equals(timetable[i])) {
                continue;
            }
            var bus = Long.parseLong(timetable[i]);
            buses.add(bus);

            var remainder = i == 0? 0 : bus - i;
            while (remainder < 0) {
                remainder += bus;
            }
            remainders.add(remainder);
        }

        return Maths.chineseRemainderTheorem(buses, remainders);
    }
}
