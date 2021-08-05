package adventofcode.year20;

import adventofcode.year20.Day8;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day8Test {

    List<String> input = List.of(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
    );

    @Test
    public void testPart1() {
        assertThat(new Day8().runPart1(input)).isEqualTo(5);
    }

    @Test
    public void testPart2() {
        assertThat(new Day8().runPart2(input)).isEqualTo(8);
    }
}
