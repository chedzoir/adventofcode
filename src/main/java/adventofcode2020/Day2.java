package adventofcode2020;

import java.util.List;
import java.util.stream.Collectors;

record InputVals(int min, int max, char letter, String password) {
}

public class Day2 implements DayRunner<Long, Long> {

    private List<InputVals> parseInput(List<String> input) {
        return input.stream()
                .map(a -> {
                    var components = a.split(" ");
                    var minMax = components[0].split("-");
                    var min = Integer.parseInt(minMax[0]);
                    var max = Integer.parseInt(minMax[1]);
                    var letter = components[1].charAt(0);
                    var password = components[2];

                    return new InputVals(min, max, letter, password);
                }).collect(Collectors.toList());
    }

    @Override
    public Long runPart1(List<String> input) {
        var valsToCheck = parseInput(input);

        return valsToCheck.stream().filter(val -> {
            var noOfLetters = val.password().chars().filter(c -> c == val.letter()).count();
            return noOfLetters >= val.min() && noOfLetters <= val.max();
        }).count();

    }

    @Override
    public Long runPart2(List<String> input) {
        var valsToCheck = parseInput(input);

        return valsToCheck.stream().filter(val -> {

            var let1 = val.password().charAt(val.min() - 1);
            var let2 = val.password().charAt(val.max() - 1);

            return (let1 == val.letter() || let2 == val.letter()) && let1 != let2;
        }).count();
    }
}
