package adventofcode.year21;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day4Test {

    List<String> input = List.of(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
            "",
            "22 13 17 11  0",
            "8  2 23  4 24",
            "21  9 14 16  7",
            "6 10  3 18  5",
            "1 12 20 15 19",
            "",
            "3 15  0  2 22",
            "9 18 13 17  5",
            "19  8  7 25 23",
            "20 11 10 24  4",
            "14 21 16 12  6",
            "",
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            "2  0 12  3  7"
    );

    @Test
    public void boardsAreParsed() {
        var boards = new Day4().parseBoards(input);

        assertThat(boards).hasSize(3);

        assertThat(boards.get(0).getNumbers()).isDeepEqualTo(
                new Integer[][]{
                        {22, 13, 17, 11, 0},
                        {8, 2, 23, 4, 24},
                        {21, 9, 14, 16, 7},
                        {6, 10, 3, 18, 5},
                        {1, 12, 20, 15, 19}
                }
        );
    }

    @Test
    void runningBoardFinishes() {
        var day4 = new Day4();

        var boards = day4.parseBoards(input);
        var numbers = List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1);

        var winningBoard = day4.runGame(numbers, boards);

        assertThat(winningBoard).isNotNull();

        assertThat(winningBoard.getNumbers())
                .isDeepEqualTo(
                        new Integer[][]{
                                {14, 21, 17, 24, 4},
                                {10, 16, 15, 9, 19},
                                {18, 8, 23, 26, 20},
                                {22, 11, 13, 6, 5},
                                {2, 0, 12, 3, 7}
                        }
                );

        assertThat(winningBoard.score()).isEqualTo(4512);
    }
    @Test
    public void runPart1() {
        assertThat(new Day4().runPart1(input)).isEqualTo(4512);
    }

    @Test
    public void runPart2() {
        assertThat(new Day4().runPart2(input)).isEqualTo(1924);
    }
}
