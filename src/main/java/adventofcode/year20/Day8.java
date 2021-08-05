package adventofcode.year20;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


record Instruction(String instruction, int val) {
}

record ProgrammeResult(boolean completes, long accumulator) {
}

public class Day8 implements DayRunner<Long, Long> {

    ProgrammeResult runProgramme(List<Instruction> insts) {
        int pointer = 0;
        long accumulator = 0;
        int[] runCount = new int[insts.size()];
        boolean complete = false;

        do {

            if (pointer >= insts.size()) {
                complete = true;
                break;
            }
            var currentInst = insts.get(pointer);

            runCount[pointer]++;
            if (runCount[pointer] > 1) {
                break;
            }
            if ("nop".equals(currentInst.instruction())) {
                pointer++;
            } else if ("jmp".equals(currentInst.instruction())) {
                pointer += currentInst.val();
            } else {
                accumulator += currentInst.val();
                pointer++;
            }
        } while (true);
        return new ProgrammeResult(complete, accumulator);
    }

    List<Instruction> parseInput(List<String> input) {
        return input.stream().map(i -> {
            var pieces = i.split(" ");
            return new Instruction(pieces[0], Integer.parseInt(pieces[1]));
        }).collect(Collectors.toList());
    }

    @Override
    public Long runPart1(List<String> input) {

        var insts = parseInput(input);

        return runProgramme(insts).accumulator();
    }

    @Override
    public Long runPart2(List<String> input) {

        var insts = parseInput(input);

        for (var i=0; i < insts.size(); i++) {
            var current = insts.get(i);
            if ("nop".equals(current.instruction()) || "jmp".equals(current.instruction())) {
                var modifiedList = new ArrayList<>(insts);
                var newInst = "nop".equals(current.instruction()) ? "jmp":"nop";
                modifiedList.set(i,  new Instruction(newInst, current.val()));
                var res = runProgramme(modifiedList);
                if (res.completes()) {
                    return res.accumulator();
                }
            }
        }
        return null;
    }
}
