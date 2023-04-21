package NewDayNewGame.Core.Physics.Collisions;

import NewDayNewGame.Core.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ShapeIterator implements Iterator<Vector2> {
    private Shape shape;
    int index;

    protected ShapeIterator(@NotNull Shape shape) {
        index = 0;
        this.shape = shape;
    }

    @Override
    public boolean hasNext() {
        return shape.points.length > index;
    }

    @Override
    public Vector2 next() {
        return shape.points[index++];
    }
}
