package adventofcode.year19;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntCodeIntepreterTest {

    @Test()
    public void programmesRunAsExpected() {
        var prog1 = "1,9,10,3,2,3,11,0,99,30,40,50";
        var int1 = IntCodeInterpreter.of(prog1);
        int1.runProgram();
        assertThat(int1.getMemoryValue(0)).isEqualTo(3500);
    }
}
