package adventofcode.utils;

import adventofcode.utils.coordinate.Coordinate2D;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinatesTest {

    @Test
    public void isScalarMultWorks() {

        assertThat(new Coordinate2D(0,2).isMultipleOf(new Coordinate2D(0,3))).isTrue();
        assertThat(new Coordinate2D(0,3).isMultipleOf(new Coordinate2D(0,2))).isTrue();

        assertThat(new Coordinate2D(3,0).isMultipleOf(new Coordinate2D(4,0))).isTrue();

        assertThat(new Coordinate2D(1,2).isMultipleOf(new Coordinate2D(2,4))).isTrue();
        assertThat(new Coordinate2D(2,4).isMultipleOf(new Coordinate2D(1,2))).isTrue();

        assertThat(new Coordinate2D(1,2).isMultipleOf(new Coordinate2D(1,3))).isFalse();
    }

    @Test
    public void isPositiveMultiplierWorks() {

        assertThat(new Coordinate2D(1,2).isPositiveMultipleOf(new Coordinate2D(2,4))).isTrue();

        assertThat(new Coordinate2D(1,2).isMultipleOf(new Coordinate2D(-2,-4))).isTrue();
        assertThat(new Coordinate2D(1,2).isPositiveMultipleOf(new Coordinate2D(-2,-4))).isFalse();
    }
}
