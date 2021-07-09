package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class Day15Test {

    @Test
    public void runPart1() {
        assertThat(new Day15().runPart1(Collections.singletonList("0,3,6"))).isEqualTo(436);

        assertThat(new Day15().runPart1(Collections.singletonList("1,3,2"))).isEqualTo(1);
        assertThat(new Day15().runPart1(Collections.singletonList("2,1,3"))).isEqualTo(10);
        assertThat(new Day15().runPart1(Collections.singletonList("1,2,3"))).isEqualTo(27);
        assertThat(new Day15().runPart1(Collections.singletonList("2,3,1"))).isEqualTo(78);
        assertThat(new Day15().runPart1(Collections.singletonList("3,2,1"))).isEqualTo(438);
        assertThat(new Day15().runPart1(Collections.singletonList("3,1,2"))).isEqualTo(1836);
    }

    @Test
    public void runPart2() {
        assertThat(new Day15().runPart2(Collections.singletonList("0,3,6"))).isEqualTo(175594);
        assertThat(new Day15().runPart2(Collections.singletonList("1,3,2"))).isEqualTo(2578);
        assertThat(new Day15().runPart2(Collections.singletonList("2,1,3"))).isEqualTo(3544142);
        assertThat(new Day15().runPart2(Collections.singletonList("1,2,3"))).isEqualTo(261214);
        assertThat(new Day15().runPart2(Collections.singletonList("2,3,1"))).isEqualTo(6895259);
        assertThat(new Day15().runPart2(Collections.singletonList("3,2,1"))).isEqualTo(18);
        assertThat(new Day15().runPart2(Collections.singletonList("3,1,2"))).isEqualTo(362);
    }
}
