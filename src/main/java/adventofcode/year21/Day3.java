package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.List;
import java.util.stream.Collectors;

public class Day3 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {

        int gamma = 0;
        int epsilon =0;

        var lgth = input.get(0).length();
        for (int i=0;i< lgth;i++){
            final var index = i;
            var ones = input.stream().map(a -> a.charAt(index))
                    .filter(t -> t == '1')
                    .count();

            var zeros = input.size() - ones;

            gamma = gamma << 1;
            if (ones > zeros) {
                gamma  += 1;
            }
        }

        epsilon = (int)Math.pow(2, lgth) - 1 - gamma;

        System.out.println(gamma);
        System.out.println(epsilon);

        return (long)gamma * epsilon;
    }

    @Override
    public Long runPart2(List<String> input) {

        var oxygen = getValue(input, true);
                var co2 = getValue(input, false);

        System.out.println(oxygen);
        System.out.println(co2);
        return oxygen * co2;
    }

    private long getValue(List<String> input, boolean most) {

        var currentList = input;
        var lgth = currentList.get(0).length();

        for (int i=0;i < lgth;i++) {
            // most common
            final var index = i;
            var ones = currentList.stream().map(a -> a.charAt(index))
                    .filter(t -> t == '1').count();
            var zeros = currentList.size() - ones;

            var filterChar = '1';
            if (ones >= zeros) {
                filterChar = most ? '1' : '0';
            } else  {
                filterChar = most ? '0' : '1';
            }

            final var filterBy = filterChar;
            currentList = currentList.stream()
                    .filter(c -> c.charAt(index) == filterBy)
                    .collect(Collectors.toList());

            if (currentList.size() == 1) {
                break;
            }
        }

        return Integer.parseInt(currentList.get(0),2);
    }
}
