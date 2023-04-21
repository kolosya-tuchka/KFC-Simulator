package NewDayNewGame.Core.Physics.Collisions;

import NewDayNewGame.Core.Vector2;
import java.util.Iterator;

public class Shape implements Iterable<Vector2> {
    protected Vector2[] points;

    @Override
    public Iterator<Vector2> iterator() {
        return new ShapeIterator(this);
    }
}
