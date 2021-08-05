package adventofcode.year20.day20;

import adventofcode.utils.MapGrids;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Picture {

    public List<Tile> tiles;
    public int size;
    public int tileSize;

    public Picture(List<Tile> tiles) {
        this.tiles = tiles;
        size = (int) Math.sqrt(tiles.size());
        tileSize = tiles.get(0).getSize();
        arrangeTiles();
    }

    private Optional<Tile> findTile(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return Optional.empty();
        }
        return tiles.stream().filter(t -> t.getX() == x && t.getY() == y).findFirst();
    }

    public List<Tile> getCorners() {
        return List.of(findTile(0, 0).get(),
                findTile(0, size - 1).get(),
                findTile(size - 1, 0).get(),
                findTile(size - 1, size - 1).get());
    }

    private void arrangeTiles() {
        // find a corners...
        var corner = tiles.stream().filter(t -> t.neighbourCount(tiles) == 2L).findAny();

        if (corner.isPresent()) {
            var firstTile = corner.get();
            firstTile.setX(0);
            firstTile.setY(0);

            var currentTile = firstTile;
            // work along the top edge
            for (var i = 1; i < size; i++) {
                Tile finalCurrentTile = currentTile;
                var neighbour = tiles.stream()
                        .filter(tile -> tile.isNeighbour(finalCurrentTile) && tile.neighbourCount(tiles) < 4 && tile.getX() == -1)
                        .findFirst();

                if (neighbour.isPresent()) {
                    var nTile = neighbour.get();
                    nTile.setX(i);
                    nTile.setY(0);
                    currentTile = nTile;
                }
            }

            for (var y = 1; y < size; y++) {
                for (var x = 0; x < size; x++) {
                    var leftNeighbour = this.findTile(x - 1, y);
                    var topNeighbour = this.findTile(x, y - 1);

                    if (topNeighbour.isEmpty()) {
                        throw new RuntimeException("This shouldn't happen");
                    }

                    Optional<Tile> thisTile;

                    // find what this tile should be...
                    if (leftNeighbour.isEmpty()) {
                        thisTile = this.tiles.stream()
                                .filter(t -> t.isNeighbour(topNeighbour.get())
                                        && t.neighbourCount(tiles) < 4
                                        && t.getX() == -1)
                                .findFirst();
                    } else {
                        thisTile = this.tiles.stream()
                                .filter(t -> t.getX() == -1 && t.isNeighbour(topNeighbour.get()) && t.isNeighbour(leftNeighbour.get()))
                                .findAny();
                    }

                    if (thisTile.isPresent()) {
                        thisTile.get().setY(y);
                        thisTile.get().setX(x);
                    }
                }
            }

            // Now align tiles
            var cornerTile = corner.get();
            var rightTile = findTile(1, 0).get();
            var bottomTile = findTile(0, 1).get();

            alignCornerRight(cornerTile, rightTile);
            alignCornerRightBottom(cornerTile, rightTile, bottomTile);
            // align the top row
            for (var x = 2; x < size; x++) {
                var tile = findTile(x, 0);
                var leftNeighbour = findTile(x - 1, 0);
                alignLeft(leftNeighbour.get(), tile.get());
            }

            // Now align the rest
            for (var y = 1; y < size; y++) {
                for (var x = 0; x < size; x++) {
                    var tile = findTile(x, y);
                    var topNeighbour = findTile(x, y - 1);

                    alignTop(topNeighbour.get(), tile.get());
                }
            }
        }
    }

    private void alignCornerRight(Tile corner, Tile right) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (corner.getRightEdge().equals(right.getLeftEdge())) {
                    return;
                }
                right.rotateLeft();
            }
            corner.rotateLeft();
        }
        corner.flip();
        alignCornerRight(corner, right);
    }

    private void alignCornerRightBottom(Tile corner, Tile right, Tile bottom) {
        for (int i = 0; i < 4; i++) {
            if (corner.getBottomEdge().equals(bottom.getTopEdge())) {
                return;
            }
            if (corner.getTopEdge().equals(bottom.getTopEdge())) {
                corner.flipVertical();
                right.flipVertical();
                return;
            }
            bottom.rotateLeft();
        }
        bottom.flipVertical();
        alignCornerRightBottom(corner, right, bottom);
    }

    private void alignLeft(Tile left, Tile right) {
        for (int i = 0; i < 4; i++) {
            if (left.getRightEdge().equals(right.getLeftEdge())) {
                return;
            }
            right.rotateLeft();
        }
        right.flipVertical();
        alignLeft(left, right);
    }

    private void alignTop(Tile top, Tile bottom) {
        for (int i = 0; i < 4; i++) {
            if (top.getBottomEdge().equals(bottom.getTopEdge())) {
                return;
            }
            bottom.rotateLeft();
        }
        bottom.flipVertical();
        alignTop(top, bottom);
    }

    public String createString(boolean dividers, boolean skipBorder) {
        StringBuilder stb = new StringBuilder();

        int lowerBound = skipBorder ? 1 : 0;
        int upperBound = skipBorder ? tileSize - 1 : tileSize;

        for (int y = 0; y < size; y++) {
            for (int row = lowerBound; row < upperBound; row++) {
                for (int x = 0; x < size; x++) {
                    var tile = findTile(x, y).get();
                    stb.append(tile.getRow(row).substring(lowerBound, upperBound));
                    if (dividers) {
                        stb.append("|");
                    }
                }
                stb.append("\n");
            }
            if (dividers) {
                for (int col = 0; col < (tileSize + 1) * size - 1; col++) {
                    stb.append("-");
                }
                stb.append("\n");
            }
        }

        return stb.toString();
    }

    @Override
    public String toString() {
        return createString(true, false);
    }

    public long findMonsters() {
        String[] regex = {
                "..................#.",
                "#....##....##....###",
                ".#..#..#..#..#..#..."
        };

        var map = List.of(createString(false, true).split("\n"));

        var pattern1 = Pattern.compile(regex[0]);
        var pattern2 = Pattern.compile(regex[1]);
        var pattern3 = Pattern.compile(regex[2]);

        // arrange the map first...
        map = arrangeMap(map, pattern1, pattern2, pattern3);

        // Now count the monsters....
        var totalMonsters = countMonsters(map, pattern1, pattern2, pattern3);

        long hashes = map.stream().map(t -> Arrays.stream(t.split("")).filter(a -> "#".equals(a)).count())
                .reduce(Long::sum).orElse(0L);

        return hashes - totalMonsters * 15;

    }

    public long countMonsters(List<String> map, Pattern pattern1, Pattern pattern2, Pattern pattern3) {
        long count = 0;
        int regexLength = "#....##....##....###".length();

        for (int y = 1; y < map.size() - 1; y++) {
            var row1 = map.get(y - 1);
            var row2 = map.get(y);
            var row3 = map.get(y + 1);
            for (int x = 0; x < row1.length() - regexLength; x++) {

                var matches1 = matches(row1.substring(x, x + regexLength), pattern1);
                var matches2 = matches(row2.substring(x, x + regexLength), pattern2);
                var matches3 = matches(row3.substring(x, x + regexLength), pattern3);
                if (matches1 > 0 && matches2 > 0 && matches3 > 0) {
                    count++;
                }
            }

        }
        return count;
    }

    public List<String> arrangeMap(List<String> mapX, Pattern pattern1, Pattern pattern2, Pattern pattern3) {
        var map = mapX;
        for (int i = 0; i < 4; i++) {

            for (int y = 1; y < map.size() - 1; y++) {

                var row1 = map.get(y - 1);
                var row2 = map.get(y);
                var row3 = map.get(y + 1);


                int regexLength = "#....##....##....###".length();
                for (int x = 0; x < row1.length() - regexLength; x++) {

                    var matches1 = matches(row1.substring(x, x + regexLength), pattern1);
                    var matches2 = matches(row2.substring(x, x + regexLength), pattern2);
                    var matches3 = matches(row3.substring(x, x + regexLength), pattern3);

                    if (matches1 > 0 && matches2 > 0 && matches3 > 0) {
                        return map;
                    }
                }
            }
            map = MapGrids.rotateLeft(map);
        }

        map = MapGrids.flipVertical(map);
        return

                arrangeMap(map, pattern1, pattern2, pattern3);

    }

    int matches(String row, Pattern pattern) {
        Matcher matcher = pattern.matcher(row);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
