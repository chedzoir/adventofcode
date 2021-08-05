package adventofcode.year20;

import adventofcode.year20.Day13;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day13Test {

    List<String> input = List.of(
            "939",
            "7,13,x,x,59,x,31,19"
    );

    @Test
    public void testPart1() {
        assertThat(new Day13().runPart1(input)).isEqualTo(295);
    }

    @Test
    public void testPart2() {
        assertThat(new Day13().runPart2(List.of("","17,x,13,19"))).isEqualTo(3417);
        assertThat(new Day13().runPart2(input)).isEqualTo(1068781);
        assertThat(new Day13().runPart2(List.of("","67,7,59,61"))).isEqualTo(754018);
        assertThat(new Day13().runPart2(List.of("","67,x,7,59,61"))).isEqualTo(779210);
        assertThat(new Day13().runPart2(List.of("","67,7,x,59,61"))).isEqualTo(1261476);
        assertThat(new Day13().runPart2(List.of("","1789,37,47,1889"))).isEqualTo(1202161486);

    }
}
