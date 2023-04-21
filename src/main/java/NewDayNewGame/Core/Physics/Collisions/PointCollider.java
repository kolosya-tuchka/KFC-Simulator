package NewDayNewGame.Core.Physics.Collisions;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Vector2;

public class PointCollider extends Collider {
    private Vector2 position;

    public PointCollider(GameObject gameObject, Vector2 position) {
        super(gameObject);
        this.shape.points = new Vector2[1];
        this.shape.points[0] = new Vector2(position);
        this.position = new Vector2(position);
    }

    @Override
    public void updateShape() {
        shape.points[0] = gameObject() == null ? position : transform().position();
    }
}
