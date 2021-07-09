package utils;

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

        for (var index = 1; index < sortedMods.size();index++) {
            while (result % sortedMods.get(index) != sortedRemainders.get(index)) {
                result += delta;
            }
            delta *= sortedMods.get(index);
        }

        return result;
    }
}
