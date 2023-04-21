package NewDayNewGame.Core.Physics;

import NewDayNewGame.Core.*;
import NewDayNewGame.Core.Physics.Collisions.Collider;
import NewDayNewGame.Core.Physics.Collisions.PointCollider;

import java.io.IOException;
import java.util.HashSet;

public class PhysBody extends Component {
    public final Vector2 velocity;
    private Vector2 impulseVelocity;
    private Vector2 resultVelocity;
    private HashSet<Collider> collisions;

    public PhysBody(GameObject gameObject) {
        this._gameObject = gameObject;
        velocity = new Vector2(0, 0);
        impulseVelocity = new Vector2(0, 0);
        resultVelocity = new Vector2(0, 0);
        collisions = new HashSet<>();
    }

    @Override
    protected void lateUpdate() {
        resultVelocity = velocity;
        transform().translate(resultVelocity.multiply(Time.getInstance().deltaTime()));
        gameObject().getComponent(Collider.class).updateShape();
        for (var col : collisions) {
            updateCollisions(col);
        }
    }

    private void updateCollisions(Collider other) {
        impulseVelocity = resultVelocity.normalized().multiply(-0.001);
        Vector2 otherImpulseVelocity = new Vector2(0, 0);
        PhysBody otherBody = other.gameObject().getComponent(PhysBody.class);
        if (otherBody != null) {
            otherImpulseVelocity = otherBody.resultVelocity.normalized().multiply(-0.001);
        }
        Collider collider = gameObject().getComponent(Collider.class);
        if (resultVelocity.magnitude() == 0 && otherBody == null) {
            impulseVelocity = other.transform().position().sub(transform().position()).normalized().multiply(-0.001);
        }
        while (other.isTouching(collider)) {
            transform().translate(impulseVelocity);
            if (otherImpulseVelocity.magnitude() != 0) {
                other.transform().translate(otherImpulseVelocity);
                other.updateShape();
            }
            collider.updateShape();
        }
    }

    @Override
    protected void onCollisionEnter(Collider other) {
        collisions.add(other);
        updateCollisions(other);
        /*Collider collider = gameObject().getComponent(Collider.class);
        Vector2 thisPoint = new Vector2(0, 0), otherPoint = new Vector2(0, 0);
        for (var point : collider.getShape()) {
            var col = new PointCollider(null, point);
            if (col.isTouching(other)) {
                thisPoint = new Vector2(point);
                break;
            }
        }
        otherPoint = new Vector2(thisPoint);
        for (var point : other.getShape()) {
            var col = new PointCollider(null, point);
            if (col.isTouching(collider)) {
                otherPoint = new Vector2(point);
                break;
            }
        }

        Vector2 temp = otherPoint.sub(thisPoint);
        Vector2 farthestPoint = other.farthestPointInDirection(new Vector2(-temp.y, temp.x));
        Vector2 direction = otherPoint.sub(farthestPoint);
        direction = direction.normalized().multiply(otherPoint.sub(thisPoint).scale(direction).magnitude());
        transform().translate(direction);
        collider.updateShape();*/


        /*impulseVelocity = resultVelocity.normalized().multiply(-0.001);
        Vector2 otherImpulseVelocity = new Vector2(0, 0);
        PhysBody otherBody = other.gameObject().getComponent(PhysBody.class);
        if (otherBody != null) {
            otherImpulseVelocity = otherBody.resultVelocity.normalized().multiply(-0.001);
        }
        Collider collider = gameObject().getComponent(Collider.class);
        if (resultVelocity.magnitude() == 0 && otherBody == null) {
            impulseVelocity = other.transform().position().sub(transform().position()).normalized().multiply(-0.001);
        }
        while (other.isTouching(collider)) {
            transform().translate(impulseVelocity);
            if (otherImpulseVelocity.magnitude() != 0) {
                other.transform().translate(otherImpulseVelocity);
                other.updateShape();
            }
            collider.updateShape();
        }*/
    }

    @Override
    protected void onCollisionExit(Collider other) {
        collisions.remove(other);
        if (collisions.isEmpty()) {
            impulseVelocity = new Vector2(0, 0);
        }
    }
}
