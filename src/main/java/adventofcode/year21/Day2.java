package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.List;

public class Day2 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {

        int[] horiz = {0};
        int[] depth = {0};

        input.forEach(dir -> {
            var parts = dir.split(" ");
            var delta = Integer.parseInt(parts[1]);
            if ("forward".equals(parts[0])) {
                horiz[0] += delta;
            } else if ("down".equals(parts[0])) {
                depth[0] += delta;
            } else if ("up".equals(parts[0])) {
                depth[0] -= delta;
            }
        });
        return horiz[0] * depth[0];
    }

    @Override
    public Integer runPart2(List<String> input) {
        int[] horiz = {0};
        int[] depth = {0};
        int[] aim  = {0};

        input.forEach(dir -> {
            var parts = dir.split(" ");
            var delta = Integer.parseInt(parts[1]);
            if ("forward".equals(parts[0])) {
                horiz[0] += delta;
                depth[0] += delta * aim[0];
            } else if ("down".equals(parts[0])) {
                aim[0] += delta;
            } else if ("up".equals(parts[0])) {
                aim[0] -= delta;
            }
        });
        return horiz[0] * depth[0];    }
}
