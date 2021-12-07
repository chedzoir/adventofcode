package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day6Test {

    @Test
    public void lanterfishSingleTimeStep() {
        assertThat(new Day6().runStep(List.of(3, 4, 3, 1, 2)))
                .isEqualTo(List.of(2, 3, 2, 0, 1));

        assertThat(new Day6().runStep((List.of(2, 3, 2, 0, 1))))
                .isEqualTo(List.of(1, 2, 1, 6, 0, 8));

        assertThat(new Day6().runStep((List.of(1, 2, 1, 6, 0, 8))))
                .isEqualTo(List.of(0, 1, 0, 5, 6, 7, 8));

    }

    @Test
    public void lanterfishMultipleTimeSteps() {
        assertThat(new Day6().runSteps(List.of(3, 4, 3, 1, 2), 18))
                .isEqualTo(List.of(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8));

    }

    @Test
    public void runPart1() {
        assertThat(new Day6().runPart1(List.of("3,4,3,1,2"))).isEqualTo(5934);
    }

    @Test
    public void runPart2() {
        assertThat(new Day6().runPart2(List.of("3,4,3,1,2"))).isEqualTo(26984457539L);
    }

    @Test
    public void runCalc() {

        assertThat(new Day6().runSteps(List.of(0), 20)).isEqualTo(List.of(1, 3, 3, 5, 3, 5, 5, 7));
        assertThat(new Day6().runSteps(List.of(1), 20)).isEqualTo(List.of(2, 4, 4, 6, 4, 6, 6, 8));
        assertThat(new Day6().runSteps(List.of(2), 20)).isEqualTo(List.of(3, 5, 5, 0, 5, 7, 7));
        assertThat(new Day6().runSteps(List.of(3), 20)).isEqualTo(List.of(4, 6, 6, 1, 6, 8, 8));
        assertThat(new Day6().runSteps(List.of(4), 20)).isEqualTo(List.of(5, 0, 0, 2, 7));
        assertThat(new Day6().runSteps(List.of(5), 20)).isEqualTo(List.of(6, 1, 1, 3, 8));
        assertThat(new Day6().runSteps(List.of(6), 20)).isEqualTo(List.of(0, 2, 2, 4));
        assertThat(new Day6().runSteps(List.of(7), 20)).isEqualTo(List.of(1, 3, 3, 5));
        assertThat(new Day6().runSteps(List.of(8), 20)).isEqualTo(List.of(2, 4, 4, 6));
        assertThat(new Day6().runCalc(List.of(0), 20)).isEqualTo(List.of(1, 3, 3, 5, 3, 5, 5, 7).size());
        assertThat(new Day6().runCalc(List.of(1), 20)).isEqualTo(List.of(2, 4, 4, 6, 4, 6, 6, 8).size());
        assertThat(new Day6().runCalc(List.of(2), 20)).isEqualTo(List.of(3, 5, 5, 0, 5, 7, 7).size());
        assertThat(new Day6().runCalc(List.of(3), 20)).isEqualTo(List.of(4, 6, 6, 1, 6, 8, 8).size());
        assertThat(new Day6().runCalc(List.of(4), 20)).isEqualTo(List.of(5, 0, 0, 2, 7).size());
        assertThat(new Day6().runCalc(List.of(5), 20)).isEqualTo(List.of(6, 1, 1, 3, 8).size());
        assertThat(new Day6().runCalc(List.of(6), 20)).isEqualTo(List.of(0, 2, 2, 4).size());
        assertThat(new Day6().runCalc(List.of(7), 20)).isEqualTo(List.of(1, 3, 3, 5).size());
        assertThat(new Day6().runCalc(List.of(8), 20)).isEqualTo(List.of(2, 4, 4, 6).size());

        assertThat(new Day6().runCalc(List.of(3,4,3,1,2), 18)).isEqualTo(26);
    }
}
