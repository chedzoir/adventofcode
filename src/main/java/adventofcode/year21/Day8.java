package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 implements DayRunner<Long, Integer> {
    @Override
    public Long runPart1(List<String> input) {
        var segments = input.stream().map(Segment::new).collect(Collectors.toList());

        // ones + fours + sevens + eights
        return count(segments, 2) + count(segments, 4) + count(segments, 3) + count(segments, 7);
    }

    private long count(List<Segment> segments, int i) {
        return segments.stream().map(s -> s.output)
                .map(op -> Arrays.stream(op).filter(val -> val.length() == i).count())
                .reduce(Long::sum).orElse(0L);
    }

    @Override
    public Integer runPart2(List<String> input) {

        var segments = input.stream().map(Segment::new).collect(Collectors.toList());

        return segments.stream().map(e -> e.getOutputValue())
                .reduce(Integer::sum).orElse(-1);
    }
}

class Segment {

    String[] patterns;
    String[] output;
    private HashMap<Integer, String> segmentDetail;

    Segment(String input) {
        var pieces = input.split("\\|");

        patterns = pieces[0].trim().split(" ");
        output = pieces[1].trim().split(" ");
    }

    public void identifySegments() {
        segmentDetail = new HashMap<>();

        segmentDetail.put(1, Arrays.stream(patterns).filter(p -> p.length() == 2).findFirst().orElse(null));
        segmentDetail.put(4, Arrays.stream(patterns).filter(p -> p.length() == 4).findFirst().orElse(null));
        segmentDetail.put(7, Arrays.stream(patterns).filter(p -> p.length() == 3).findFirst().orElse(null));
        segmentDetail.put(8, Arrays.stream(patterns).filter(p -> p.length() == 7).findFirst().orElse(null));

        segmentDetail.put(6, Arrays.stream(patterns)
                .filter(p -> p.length() == 6)
                .filter(p -> !overlaps(p, segmentDetail.get(1)))
                .findFirst().orElse(null));

        segmentDetail.put(9, Arrays.stream(patterns)
                .filter(p -> p.length() == 6)
                .filter(p -> overlaps(p, segmentDetail.get(4)))
                .filter(p -> !segmentDetail.containsValue(p))
                .findFirst().orElse(null));

        segmentDetail.put(0, Arrays.stream(patterns)
                .filter(p -> p.length() == 6)
                .filter(p -> !segmentDetail.containsValue(p))
                .findFirst().orElse(null));

        segmentDetail.put(3, Arrays.stream(patterns)
                .filter(p -> p.length() == 5)
                .filter(p -> overlaps(p, segmentDetail.get(1)))
                .findFirst().orElse(null));

        segmentDetail.put(5, Arrays.stream(patterns)
                .filter(p -> p.length() == 5)
                .filter(p -> !segmentDetail.containsValue(p))
                .filter(p -> overlaps(segmentDetail.get(9), p))
                .findFirst().orElse(null));

        segmentDetail.put(2, Arrays.stream(patterns)
                .filter(p -> !segmentDetail.containsValue(p))
                .findFirst().orElse(null));


    }

    public Integer getOutputValue() {
        this.identifySegments();
        ;
        var res = 0;
        for (int i = 0; i < output.length; i++) {
            res = res * 10 + getValue(output[i]);
        }
        return res;
    }

    private Integer getValue(String output) {
        return segmentDetail.entrySet().stream()
                .filter(e -> overlaps(e.getValue(), output) && overlaps(output, e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElse(0);
    }

    private boolean overlaps(String pattern1, String pattern2) {
        return Arrays.stream(pattern2.split("")).allMatch(p -> pattern1.contains(p));
    }
}
