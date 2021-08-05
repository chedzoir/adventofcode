package adventofcode.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MathsTests {

    @Test
    public void chineseRemainderTheorem() {
        assertThat(Maths.chineseRemainderTheorem(List.of(3L,4L,5L), List.of(0L,3L,4L)))
                .isEqualTo(39L);
        assertThat(Maths.chineseRemainderTheorem(List.of(17L,13L,19L), List.of(0L,11L,16L)))
                .isEqualTo(3417L);
    }


}
