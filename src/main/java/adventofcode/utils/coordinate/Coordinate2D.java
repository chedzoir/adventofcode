package adventofcode.utils.coordinate;


import java.util.List;
import java.util.stream.Collectors;

public record Coordinate2D(Integer x, Integer y) implements Coordinate<Coordinate2D> {

    @Override
    public Coordinate2D add(Coordinate2D c2) {
        return new Coordinate2D(this.x() + c2.x(),
                this.y() + c2.y());
    }

    @Override
    public Integer manhattenDistance(Coordinate2D c2) {
        var xDiff = Math.abs(this.x() - c2.x());
        var yDiff = Math.abs(this.y() - c2.y());
        return xDiff + yDiff;
    }

    @Override
    public Coordinate2D scalarMult(int val) {
        return new Coordinate2D(val * this.x(), val * this.y());
    }

    @Override
    public Coordinate2D rotate(int degree) {
        if (degree == 0) {
            return this;
        }
        if (degree < 0) {
            return new Coordinate2D(-1 * this.y(), this.x()).rotate(degree + 90);
        } else {
            return new Coordinate2D(this.y(), -1 * this.x()).rotate(degree - 90);
        }
    }

    @Override
    public List<Coordinate2D> hexNeighbours() {

        List<Coordinate2D> deltas = List.of(
                new Coordinate2D(-2, 0),
                new Coordinate2D(2, 0),
                new Coordinate2D(-1, 1),
                new Coordinate2D(-1, -1),
                new Coordinate2D(1, 1),
                new Coordinate2D(1, -1)
        );

        return deltas.stream().map(this::add)
                .collect(Collectors.toList());
    }
}
