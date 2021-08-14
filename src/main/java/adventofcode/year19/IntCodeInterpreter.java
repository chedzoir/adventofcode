package adventofcode.year19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntCodeInterpreter {

    final List<Integer> opcode;
    final Map<Integer, Integer> memory;

    private int position = 0;

    public static IntCodeInterpreter of(String programme) {
        var opcode = Arrays.stream(programme.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new IntCodeInterpreter(opcode);
    }

    public IntCodeInterpreter(List<Integer> opcode) {
        this.opcode = opcode;
        memory = new HashMap<>();
        for (var i=0; i<opcode.size();i++) {
            memory.put(i, opcode.get(i));
        }
    }

    public void setOpcode(Integer position, Integer value) {
        opcode.set(position, value);
        this.resetMemory();
    }

    public void resetMemory() {
        memory.clear();
        for (var i=0; i<opcode.size();i++) {
            memory.put(i, opcode.get(i));
        }
    }

    public Integer getMemoryValue(Integer position) {
        return memory.get(position);
    }

    public void runProgram() {

        position = 0;
        while (getMemoryValue(position) != 99) {
            var opcode = memory.get(position);

            switch (opcode) {
                case 1:
                    this.addition();
                    break;
                case 2:
                    this.multiplication();
                    break;
            }
        }
    }

    private void addition() {
        var pos1 = memory.get(position+1);
        var pos2 = memory.get(position+2);
        var saveTo = memory.get(position+3);

        memory.put(saveTo, memory.get(pos1) + memory.get(pos2));
        position += 4;
    }

    private void multiplication() {
        var pos1 = memory.get(position+1);
        var pos2 = memory.get(position+2);
        var saveTo = memory.get(position+3);

        memory.put(saveTo, memory.get(pos1) * memory.get(pos2));
        position += 4;
    }
}
