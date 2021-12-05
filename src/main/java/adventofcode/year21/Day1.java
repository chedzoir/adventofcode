package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 implements DayRunner<Integer, Integer> {
    @Override
    public Integer runPart1(List<String> input) {
        var inputVars = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        var res =0;
        for (int i=1; i<inputVars.size();i++){
            if (inputVars.get(i) > inputVars.get(i-1)) {
                res++;
            }
        }

        return res;
    }

    @Override
    public Integer runPart2(List<String> input) {
        var inputVars = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        var windowSum = new ArrayList<Integer>();

        for (int i=0; i<inputVars.size() -2;i++) {
            windowSum.add(inputVars.get(i) + inputVars.get(i+1) + inputVars.get(i+2));
        }
        var res =0;
        for (int i=1; i<windowSum.size();i++){
            if (windowSum.get(i) > windowSum.get(i-1)) {
                res++;
            }
        }

        return res;    }
}
