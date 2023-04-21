package NewDayNewGame.Core.Physics.Collisions;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rect;
import NewDayNewGame.Core.Vector2;
import org.jetbrains.annotations.NotNull;

public class RectCollider extends Collider {
    protected Rect rect;
    private Shape localShape;

    public RectCollider(GameObject gameObject, @NotNull Vector2 center, @NotNull Vector2 size) {
        super(gameObject);
        shape.points = new Vector2[4];
        localShape = new Shape();
        localShape.points = new Vector2[4];
        this.rect = new Rect(center, size);
    }

    public RectCollider(GameObject gameObject, @NotNull Rect rect) {
        super(gameObject);
        shape.points = new Vector2[4];
        localShape = new Shape();
        localShape.points = new Vector2[4];
        this.rect = new Rect(rect);
        changeShape();
        updateShape();
    }

    @Override
    public void start() {
        changeShape();
        updateShape();
    }

    public void setCenter(@NotNull Vector2 center) {
        this.rect.setCenter(center);
        changeShape();
        updateShape();
    }

    public void setSize(@NotNull Vector2 size) {
        this.rect.setSize(size);
        changeShape();
        updateShape();
    }

    private void changeShape() {
        localShape.points[0] = new Vector2(rect.getCenter().x - rect.getSize().x / 2, rect.getCenter().y);
        localShape.points[1] = new Vector2(rect.getCenter().x + rect.getSize().x / 2, rect.getCenter().y);
        localShape.points[2] = new Vector2(rect.getCenter().x + rect.getSize().x / 2, rect.getCenter().y + rect.getSize().y * 2);
        localShape.points[3] = new Vector2(rect.getCenter().x - rect.getSize().x / 2, rect.getCenter().y + rect.getSize().y * 2);
    }

    @Override
    public void updateShape() {
        for (int i = 0; i < 4; ++i) {
            if (transform() == null) {
                shape.points[i] = localShape.points[i].add(rect.getCenter());
            } else {
                shape.points[i] = localShape.points[i].add(transform().position());
            }
        }
    }
}
