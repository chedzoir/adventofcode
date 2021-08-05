package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {
        Map<Integer, Long> memory = new HashMap<>();

        String mask = "";

        for (var inst : input) {
            if (inst.startsWith("mask")) {
                mask = inst.split(" ")[2];
            } else {
                var tokens = inst.split(" ");
                var value = Long.parseLong(tokens[2]);
                var loc = Integer.parseInt(
                        tokens[0].replaceAll("mem\\[", "")
                                .replaceAll("\\]", ""));

                var orMask = Long.parseLong(mask.replaceAll("X", "0"), 2);
                var andMask = Long.parseLong(mask.replaceAll("X", "1"), 2);
                memory.put(loc, (value | orMask) & andMask);
            }
        }

        return memory.values().stream().reduce(Long::sum).orElse(0L);
    }

    @Override
    public Long runPart2(List<String> input) {
        Map<Long, Long> memory = new HashMap<>();

        String mask = "";

        for (var inst : input) {
            if (inst.startsWith("mask")) {
                mask = inst.split(" ")[2];
            } else {
                var tokens = inst.split(" ");
                var value = Long.parseLong(tokens[2]);
                var loc = Integer.parseInt(
                        tokens[0].replaceAll("mem\\[", "")
                                .replaceAll("\\]", ""));

                var addresses = getAddresses(loc, mask);

                addresses.forEach(a -> memory.put(Long.parseLong(a, 2), value));
            }
        }

        return memory.values().stream().reduce(Long::sum).orElse(0L);
    }

    private List<String> getAddresses(int loc, String mask) {

        List<String> res = List.of("");
        var tmp = "000000000000000000000000000000000000" + Integer.toString(loc, 2);

        var locString = tmp.substring(tmp.length() - 36);

        for (int i = 0; i < mask.length(); i++) {
            final var charToAdd = locString.charAt(i);
            if (mask.charAt(i) == '0') {
                res = addValue(res, charToAdd);
            } else if (mask.charAt(i) == '1') {
                res = addValue(res, '1');
            } else {
                var res0 = addValue(res, '0');
                var res1 = addValue(res, '1');
                res = new ArrayList<>(res0);
                res.addAll(res1);
            }
        }

        return res;
    }

    private List<String> addValue(List<String> res, char charToAdd) {
        return res.stream().map(r -> r + charToAdd).collect(Collectors.toList());
    }
}
