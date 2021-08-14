package adventofcode.year20;

import adventofcode.utils.coordinate.Coordinate2D;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day24Test {

    List<String> input = List.of(
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew"
    );

    @Test
    public void parsingValue() {
        assertThat(new Day24().parseInstruction("esenee")).isEqualTo(List.of("e", "se", "ne", "e"));
        assertThat(new Day24().parseInstruction("esew")).isEqualTo(List.of("e", "se", "w"));
        assertThat(new Day24().parseInstruction("nwwswee")).isEqualTo(List.of("nw", "w", "sw", "e", "e"));
    }

    @Test
    public void followPath() {
        assertThat(new Day24().follow("esew")).isEqualTo(new Coordinate2D(1, -1));
        assertThat(new Day24().follow("nwwswee")).isEqualTo(new Coordinate2D(0, 0));
    }

    @Test
    public void part1() {
        assertThat(new Day24().runPart1(input)).isEqualTo(10);
    }

    @Test
    public void dayRunner() {
        Day24 day = new Day24();
        var flipped = day.getInitialFlippedTiles(input);

        var flipped2 = day.runDay(flipped);
        assertThat(flipped2.size()).isEqualTo(15);

        var flipped3 = day.runDay(flipped2);
        assertThat(flipped3.size()).isEqualTo(12);

        var flipped4 = day.runDay(flipped3);
        assertThat(flipped4.size()).isEqualTo(25);

        assertThat(day.runDays(flipped, 20).size()).isEqualTo(132);
        assertThat(day.runDays(flipped, 30).size()).isEqualTo(259);
        assertThat(day.runDays(flipped, 40).size()).isEqualTo(406);
        assertThat(day.runDays(flipped, 50).size()).isEqualTo(566);
        assertThat(day.runDays(flipped, 60).size()).isEqualTo(788);
        assertThat(day.runDays(flipped, 70).size()).isEqualTo(1106);
        assertThat(day.runDays(flipped, 80).size()).isEqualTo(1373);
        assertThat(day.runDays(flipped, 90).size()).isEqualTo(1844);
        assertThat(day.runDays(flipped, 100).size()).isEqualTo(2208);
    }

    @Test
    public void part2() {
        assertThat(new Day24().runPart2(input)).isEqualTo(2208);
    }
}
