package adventofcode.utils.coordinate;

import java.util.List;

public record Coordinate4D(Integer x, Integer y, Integer z, Integer w) implements Coordinate<Coordinate4D> {
    @Override
    public Coordinate4D add(Coordinate4D c2) {
        return new Coordinate4D(this.x() + c2.x(), this.y() + c2.y(),
                this.z() + c2.z(), this.w() + c2.w());
    }

    @Override
    public Integer manhattenDistance(Coordinate4D c2) {
        return Math.abs(this.x() - c2.x()) +
                Math.abs(this.y() - c2.y()) +
                Math.abs(this.z() - c2.z()) +
                Math.abs(this.w() - c2.w());
    }

    @Override
    public Coordinate4D scalarMult(int val) {
        return new Coordinate4D(
                this.x() * val,
                this.y() * val,
                this.z() * val,
                this.w() * val
        );
    }

    @Override
    public Coordinate4D rotate(int degree) {
        throw new RuntimeException("rotate is not implemented for Coordinate4D");
    }

    @Override
    public List<Coordinate4D> hexNeighbours() {
        throw new RuntimeException("hexNeighbours is not implemented for Coordinate4D");
    }
}
