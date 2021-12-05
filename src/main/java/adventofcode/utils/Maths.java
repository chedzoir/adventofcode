package adventofcode.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Maths {

    /*

     */
    public static Long chineseRemainderTheorem(List<Long> mods, List<Long> remainders) {

        var sortedMods = mods.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        var sortedRemainders = sortedMods.stream().map(val -> {
            var pos = mods.indexOf(val);
            return remainders.get(pos);
        }).collect(Collectors.toList());

        var result = sortedRemainders.get(0);
        var delta = sortedMods.get(0);

        for (var index = 1; index < sortedMods.size(); index++) {
            while (result % sortedMods.get(index) != sortedRemainders.get(index)) {
                result += delta;
            }
            delta *= sortedMods.get(index);
        }

        return result;
    }

    public static Long gcd(Long val1, Long val2) {

        var a = Math.max(val1, val2);
        var b = Math.min(val1, val2);

        while (a % b != 0) {
            var nvl = a % b;
            a = b;
            b = nvl;
        }
        return b;
    }

    public static Long lcm(Long val1, Long val2 ) {
        return val1 * val2 / gcd(val1, val2);
    }
}
