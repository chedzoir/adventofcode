package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day7Test {

    @Test
    public void testFuelCalc() {
        var runner = new Day7();

        var input = List.of(16,1,2,0,4,2,7,1,2,14);
        assertThat(runner.calculateFuel(input,1)).isEqualTo(41);
        assertThat(runner.calculateFuel(input,2)).isEqualTo(37);
        assertThat(runner.calculateFuel(input,3)).isEqualTo(39);
        assertThat(runner.calculateFuel(input,10)).isEqualTo(71);
    }

    @Test
    public void part1() {
        assertThat(new Day7().runPart1(List.of("16,1,2,0,4,2,7,1,2,14"))).isEqualTo(37);
    }

    @Test
    public void testVaryingFuelCalc() {
        var runner = new Day7();

        var input = List.of(16,1,2,0,4,2,7,1,2,14);
        assertThat(runner.calculateVaryingFuel(input,5)).isEqualTo(168);
        assertThat(runner.calculateVaryingFuel(input,2)).isEqualTo(206);
    }

    @Test
    public void part2() {
        assertThat(new Day7().runPart2(List.of("16,1,2,0,4,2,7,1,2,14"))).isEqualTo(168);
    }
}
