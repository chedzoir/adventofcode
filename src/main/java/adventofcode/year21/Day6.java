package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 implements DayRunner<Integer, Long> {
    @Override
    public Integer runPart1(List<String> input) {
        var fishes = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return runSteps(fishes, 80).size();
    }

    @Override
    public Long runPart2(List<String> input) {
        var fishes = Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return runCalc(fishes, 256);
    }

    public List<Integer> runStep(List<Integer> fish) {
        final List<Integer> newFish = new ArrayList<>();

        var res = fish.stream().map(f -> {
            if (Objects.equals(0, f)) {
                newFish.add(8);
                return 6;
            } else {
                return f - 1;
            }
        }).collect(Collectors.toList());

        res.addAll(newFish);

        return res;
    }

    public List<Integer> runSteps(List<Integer> fish, int days) {
        var currentFish = fish;
        for (int i = 0; i < days; i++) {
            currentFish = runStep(currentFish);
        }
        return currentFish;
    }

    public Long runCalc(List<Integer> fish, int days) {
        final var cnt = new int[]{0};
        final var calcs = new HashMap<Integer, Long>();
        return fish.size() + fish.stream().map(f -> {
            System.out.println(f + " " + (++cnt[0]) + " " + fish.size());
            if (! calcs.containsKey(f)) {
                var numberOfFish = fishCreatedBy(f, days);
                calcs.put(f, numberOfFish);
            }
            return  calcs.get(f);
        }).reduce(Long::sum).orElse(0L);
    }


    private Long fishCreatedBy(Integer fishValue, int days) {

        var firstAddedAt = fishValue + 1; // if 0 added at day 1

        if (firstAddedAt > days) {
            return 0L;
        }

        var fishAddedByThis = 1L + Math.floorDiv(days - firstAddedAt, 7);

        var totalFish = fishAddedByThis;

        for (var i = 0; i < fishAddedByThis; i++) {
            totalFish += fishCreatedBy(8, days - (firstAddedAt + 7 * i));
        }
        return totalFish;
    }
}
