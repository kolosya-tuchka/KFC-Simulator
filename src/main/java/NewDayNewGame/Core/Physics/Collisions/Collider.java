package NewDayNewGame.Core.Physics.Collisions;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Physics.Physics;
import NewDayNewGame.Core.Vector2;

public abstract class Collider extends Component {
    public boolean isTrigger = false;
    protected Shape shape;

    public Collider(GameObject gameObject) {
        super(gameObject);

        if (gameObject != null) {
            Physics.getInstance().addCollider(this);
        }
        shape = new Shape();
    }

    public boolean isTouching(Collider other) {
        Simplex simplex = new Simplex();
        Vector2 direction = new Vector2(0, 1);
        simplex.add(supportPoint(other, direction));
        direction = direction.multiply(-1);

        while (direction != null) {
            Vector2 support = supportPoint(other, direction);

            if (support.dot(direction) <= 0) {
                return false;
            }

            simplex.add(support);
            direction = simplex.calculateDirection();
        }

        return true;
    }

    public Vector2 farthestPointInDirection(Vector2 direction) {
        double farthestDistance = Double.NEGATIVE_INFINITY;
        Vector2 farthestPoint = new Vector2(0, 0);

        for (var point : shape) {
            double distanceInDirection = point.dot(direction);
            if (distanceInDirection > farthestDistance) {
                farthestDistance = distanceInDirection;
                farthestPoint = point;
            }
        }
        return farthestPoint;
    }

    private Vector2 supportPoint(Collider other, Vector2 direction) {
        var first = this.farthestPointInDirection(direction);
        var second = other.farthestPointInDirection(direction.multiply(-1));
        return first.sub(second);
    }

    @Override
    protected void onSceneLoad() {
        Physics.getInstance().addCollider(this);
    }

    public abstract void updateShape();

    public Shape getShape() {
        return shape;
    }
}
