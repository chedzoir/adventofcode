package adventofcode.year19;

import adventofcode.utils.DayRunner;

import java.util.List;

public class Day2 implements DayRunner<Integer, Integer> {


    @Override
    public Integer runPart1(List<String> input) {
        var interpreter = IntCodeInterpreter.of(input.get(0));
        return this.runProgramme(interpreter, 12, 2);
    }

    private Integer runProgramme(IntCodeInterpreter interpreter, Integer noun, Integer verb) {
        interpreter.setOpcode(1, noun);
        interpreter.setOpcode(2, verb);
        interpreter.runProgram();
        return interpreter.getMemoryValue(0);
    }

    @Override
    public Integer runPart2(List<String> input) {
        var interpreter = IntCodeInterpreter.of(input.get(0));
        int noun = 0;
        int verb = 0;
        while (true) {
            var res = this.runProgramme(interpreter, noun, verb);
            if (res == 19690720) {
                return 100 * noun + verb;
            }
            if (res > 19690720) {
                noun = 0;
                verb++;
            } else {
                noun++;
            }
        }

    }
}
