package adventofcode.utils.coordinate;


import java.util.List;
import java.util.stream.Collectors;

public record Coordinate2D(Integer x, Integer y) implements Coordinate<Coordinate2D> {

    public static final Coordinate2D Origin = new Coordinate2D(0,0);

    public static Coordinate2D from(String coor) {
        var parts = coor.split(",");
        var xStr = parts[0].trim();
        var yStr =  parts[1].trim();
        return new Coordinate2D(Integer.parseInt(xStr), Integer.parseInt(yStr));
    }


    @Override
    public Coordinate2D add(Coordinate2D c2) {
        return new Coordinate2D(this.x() + c2.x(),
                this.y() + c2.y());
    }
    public static final Coordinate2D Up = new Coordinate2D(0,1);

    @Override
    public Coordinate2D minus(Coordinate2D c2) {
        return new Coordinate2D(this.x() - c2.x(),
                this.y() - c2.y());
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

    @Override
    public boolean isMultipleOf(Coordinate2D c2) {
        if (x == 0) {
            return c2.x == 0;
        }

        if ( y == 0) {
            return c2.y == 0;
        }

        return Math.abs((c2.x / (double)c2.y) - (x / (double)y)) < 1e-9;
    }

    public int magnitude() {
        return x * x + y * y;
    }

    public boolean isPositiveMultipleOf(Coordinate2D c2) {
        if (! isMultipleOf(c2)) {
            return false;
        }

        return Math.signum(c2.x) == Math.signum(x) && Math.signum(c2.y) == Math.signum(y);
    }
}
