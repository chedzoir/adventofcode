package adventofcode.year20;

import adventofcode.year20.Day25;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day25Test {

    List<String> input = List.of("5764801","17807724");

    @Test
    public void loopSizeIsCorrectlyWorkedOut() {
        Day25 day = new Day25();

        assertThat(day.getLoopSize(5764801L, 7L)).isEqualTo(8);
        assertThat(day.getLoopSize(17807724L, 7L)).isEqualTo(11);

    }

    @Test
    public void encryptionKeysAreCorrect() {
        Day25 day = new Day25();

        assertThat(day.getEncyptionKey(5764801L,17807724L)).isEqualTo(14897079L);
        assertThat(day.getEncyptionKey(17807724L,5764801L)).isEqualTo(14897079L);
    }

    @Test
    public void part1() {
        assertThat(new Day25().runPart1(input)).isEqualTo(14897079L);
    }
}
