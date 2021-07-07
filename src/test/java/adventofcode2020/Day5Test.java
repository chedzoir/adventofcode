package adventofcode2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day5Test {

    @Test
    public void testId() {
        assertThat(new Day5().getId("FBFBBFFRLR")).isEqualTo(357);
        assertThat(new Day5().getId("BFFFBBFRRR")).isEqualTo(567);
        assertThat(new Day5().getId("FFFBBBFRRR")).isEqualTo(119);
        assertThat(new Day5().getId("BBFFBBFRLL")).isEqualTo(820);
    }

    @Test
    public void testPart1() {

        var input = List.of("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL");
        assertThat(new Day5().runPart1(input)).isEqualTo(820);
    }
}
