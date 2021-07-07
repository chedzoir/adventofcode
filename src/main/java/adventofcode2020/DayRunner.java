package adventofcode2020;

import java.util.List;

public interface DayRunner<T,U> {

    T runPart1(List<String> input);

    U runPart2(List<String> input);
}
