package adventofcode2020.day20;

import utils.MapGrids;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tile {

    private final int size;
    private List<String> pixels;
    private final long id;
    private int x = -1;
    private int y = -1;

    public Tile(long id, List<String> pixelInput) {
        this.pixels = pixelInput;
        this.size = pixels.size();
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public long getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<String> getEdges() {
        return List.of(
                getTopEdge(),
                getRightEdge(),
                getBottomEdge(),
                getLeftEdge()
        );
    }

    public boolean isNeighbour(Tile otherTile) {
        var neighbourEdges = otherTile.getEdges();
        var thisEdges = this.getEdges();

        // do we contain without flipping?
        if (thisEdges.stream().anyMatch(neighbourEdges::contains)) {
            return true;
        }
        return thisEdges.stream()
                .map(e -> new StringBuilder(e).reverse().toString())
                .anyMatch(neighbourEdges::contains);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return size == tile.size && Objects.equals(pixels, tile.pixels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, pixels);
    }

    public long neighbourCount(List<Tile> tiles) {
        return tiles.stream()
                .filter(t -> !t.equals(this) && t.isNeighbour(this))
                .count();
    }

    public String getLeftEdge() {
        var left = new StringBuilder();

        for (var i = 0; i < size; i++) {
            left.append(pixels.get(i).charAt(0));
        }
        return left.toString();
    }

    public String getRightEdge() {
        var right = new StringBuilder();

        for (var i = 0; i < size; i++) {
            right.append(pixels.get(i).charAt(size - 1));
        }
        return right.toString();
    }

    public String getTopEdge() {
        return pixels.get(0);
    }

    public String getBottomEdge() {
        return pixels.get(size - 1);
    }

    public void rotateLeft() {
        this.pixels = MapGrids.rotateLeft(this.pixels);
    }

    public void flip() {

        List<StringBuilder> tmp = new ArrayList<>();
        for (var i=0;i<size; i++) {
            tmp.add(new StringBuilder(pixels.get(size - i - 1)));
        }
        this.pixels = tmp.stream().map(t -> t.reverse().toString()).collect(Collectors.toList());

    }

    public void flipVertical() {
        List<String> tmp = new ArrayList<>();
        for (var i=0;i<size; i++) {
            tmp.add(pixels.get(size - i - 1));
        }
        this.pixels = tmp;

    }


    public String getRow(int row) {
        return this.pixels.get(row);
    }
}
