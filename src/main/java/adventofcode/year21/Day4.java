package adventofcode.year21;

import adventofcode.utils.DayRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 implements DayRunner<Integer, Integer> {

    List<Board> parseBoards(List<String> input) {
        List<String> currentBoard = new ArrayList<>();
        List<Board> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                currentBoard.add(input.get(i));
            }

            if (input.get(i).isEmpty() || i == input.size() - 1) {
                var board = currentBoard.stream().map(a ->
                                Arrays.stream(a.split(" "))
                                        .filter(val -> !val.isEmpty())
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList())
                                        .toArray(new Integer[0]))
                        .toArray(Integer[][]::new);
                int rows = board.length;
                int cols = board[0].length;

                var marked = new boolean[rows][cols];
                for (int row = 0; row < rows - 1; row++) {
                    for (int col = 0; col < cols - 1; col++) {
                        marked[row][col] = false;
                    }
                }
                boards.add(new Board().setNumbers(board).setMarked(marked));
                currentBoard.clear();
            }
        }
        return boards;
    }

    public Board runGame(List<Integer> numbers, List<Board> boards) {
        for (int i=0;i<numbers.size();i++){
             final var index = i;
            boards.forEach(board -> board.mark(numbers.get(index)));

            if (boards.stream().anyMatch(board -> board.winner())) {
                return boards.stream().filter(board -> board.winner())
                        .findFirst().orElse(null);
            }
        }
        return null;
    }

    public Board lastWinningBoard(List<Integer> numbers, List<Board> boards) {
        var currentBoards = boards;

        for (int i=0;i<numbers.size();i++){
            final var index = i;
            currentBoards.forEach(board -> board.mark(numbers.get(index)));

            if (currentBoards.size() == 1) {
                if (currentBoards.get(0).winner()) {
                    return currentBoards.get(0);
                }
            } else {
                currentBoards = currentBoards.stream().filter(board -> ! board.winner())
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
    @Override
    public Integer runPart1(List<String> input) {

        var numbers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var boards = parseBoards(input);

        var winningBoard = runGame(numbers, boards);

        return winningBoard.score();
    }

    @Override
    public Integer runPart2(List<String> input) {
        var numbers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var boards = parseBoards(input);

        var winningBoard = lastWinningBoard(numbers, boards);

        return winningBoard.score();    }
}

class Board {
    Integer[][] numbers;
    boolean[][] marked;
    Integer lastNumber;

    public Integer[][] getNumbers() {
        return numbers;
    }

    public Board setNumbers(Integer[][] numbers) {
        this.numbers = numbers;
        return this;
    }

    public boolean[][] getMarked() {
        return marked;
    }

    public Board setMarked(boolean[][] marked) {
        this.marked = marked;
        return this;
    }

    public void mark(Integer value) {
        for (int row = 0; row < numbers.length;row++){
            for(int col = 0 ; col < numbers[row].length;col++){
                if (value.equals(numbers[row][col])) {
                    marked[row][col] = true;
                }
            }
        }
        lastNumber = value;
    }

    public boolean winner() {
        // check rows
        for (int row = 0; row < numbers.length;row++){
            var winningRow = true;
            for (int col = 0; col < numbers[row].length;col++){
                winningRow = winningRow && marked[row][col];
            }
            if (winningRow) {
                return true;
            }
        }

        // check columns

        for (int col = 0; col < numbers[0].length;col++){
            var winningCol = true;
            for (int row = 0; row < numbers.length;row++){
                winningCol = winningCol && marked[row][col];
            }
            if (winningCol) {
                return true;
            }
        }

        return false;
    }

    Integer score() {
        var res = 0;
        for (int row = 0; row < numbers.length;row++){
            for (int col = 0; col < numbers[row].length;col++){
               if ( ! marked[row][col]) {
                   res += numbers[row][col];
               }
            }
        }

        return res * lastNumber;
    }
}
