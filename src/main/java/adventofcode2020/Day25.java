package adventofcode2020;

import java.util.List;

public class Day25 implements DayRunner<Long, Long>{
    @Override
    public Long runPart1(List<String> input) {
        var key1 = Long.parseLong(input.get(0));
        var key2 = Long.parseLong(input.get(1));
        return getEncyptionKey(key1, key2);
    }

    @Override
    public Long runPart2(List<String> input) {
        return null;
    }

    public long getLoopSize(long target, long subject) {

        long val =1;
        long loopCount = 0;

        do {
            val = (val * subject) % 20201227L;
            loopCount++;
        } while (val != target);

        return loopCount;
    }

    public long runLoop(long subject, long loops) {
        long val = 1;
        for (var i=0;i<loops;i++) {
            val = (val * subject) % 20201227L;
        }
        return val;
    }

    public long getEncyptionKey(long key1, long key2) {
        var loop = getLoopSize(key1, 7L);
        return runLoop(key2, loop);
    }
}
