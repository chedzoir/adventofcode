package adventofcode.utils.coordinate;

import java.util.List;

public interface Coordinate<D extends Coordinate> {


    D add(D c2);

    D minus(D c2);

    Integer manhattenDistance(D c2);

    D scalarMult(int val);

    D rotate(int degree);

    List<D> hexNeighbours();

    boolean isMultipleOf(D c2);
}
