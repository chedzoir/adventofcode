package adventofcode2020;

import adventofcode2020.day20.Picture;
import adventofcode2020.day20.Tile;

import java.util.*;


public class Day20 implements DayRunner<Long, Long> {
    @Override
    public Long runPart1(List<String> input) {

        var picture = parseInput(input);

        return picture.getCorners()
                .stream()
                .map(Tile::getId)
                .reduce(1L, (a, b) -> a * b);
    }

    @Override
    public Long runPart2(List<String> input) {
        var picture = parseInput(input);

        return picture.findMonsters();

    }

    Picture parseInput(List<String> input) {

        List<Tile> res = new ArrayList<>();
        long id = -1L;
        List<String> vals = new ArrayList<>();
        for (var s : input) {
            if (s.startsWith("Tile")) {
                id = Long.parseLong(s.split(" ")[1].replace(":", ""));
            } else if (s.isEmpty()) {
                res.add(new Tile(id, vals));
                vals = new ArrayList<>();
            } else {
                vals.add(s);
            }
        }
        res.add(new Tile(id, vals));

        return new Picture(res);
    }

}
