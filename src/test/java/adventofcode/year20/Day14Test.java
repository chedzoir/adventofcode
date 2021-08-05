package adventofcode.year20;

import adventofcode.year20.Day14;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day14Test {

    List<String> input= List.of(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
    );

    @Test
    public void testPart1() {
        assertThat(new Day14().runPart1(input)).isEqualTo(165);
    }

    List<String> input2 = List.of(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
    );

    @Test
    public void testPart2() {
        assertThat(new Day14().runPart2(input2)).isEqualTo(208);
    }
}
