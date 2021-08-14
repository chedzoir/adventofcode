package adventofcode.utils.coordinate;

import java.util.List;

public record Coordinate3D(Integer x, Integer y, Integer z) implements Coordinate<Coordinate3D> {
    @Override
    public Coordinate3D add(Coordinate3D c2) {
        return new Coordinate3D(this.x() + c2.x(), this.y() + c2.y(), this.z() + c2.z());
    }

    @Override
    public Integer manhattenDistance(Coordinate3D c2) {
        return Math.abs(this.x() - c2.x()) + Math.abs(this.y() - c2.y()) + Math.abs(this.z() - c2.z());
    }

    @Override
    public Coordinate3D scalarMult(int val) {
        return new Coordinate3D(this.x() * val, this.y() * val, this.z() * val);
    }

    @Override
    public Coordinate3D rotate(int degree) {
        throw new RuntimeException("rotate not implemented for Cordinate3D");
    }

    @Override
    public List<Coordinate3D> hexNeighbours() {
        throw new RuntimeException("hexNeighbours not implemented for Cordinate3D");
    }
}
