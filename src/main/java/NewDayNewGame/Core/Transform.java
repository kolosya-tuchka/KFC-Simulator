package NewDayNewGame.Core;

import java.util.HashSet;

public class Transform extends Component {
    public Vector2 localPosition;
    public Vector2 localScale;
    private Transform parent;
    public final HashSet<Transform> children;

    private Vector2 _position, _scale;

    protected Transform(GameObject gameObject) {
        super(gameObject);
        localPosition = new Vector2(0, 0);
        localScale = new Vector2(1, 1);
        _transform = this;
        children = new HashSet<>();
    }

    @Override
    public void start() {
        calculatePositionAndScale();
    }

    @Override
    public void destroy() {
        gameObject().destroy();
    }

    @Override
    protected void update() {
        calculatePositionAndScale();
    }

    public Transform getParent() {
        return parent;
    }

    private void calculatePositionAndScale() {
        Transform cur = parent;
        _position = new Vector2(localPosition);
        _scale = new Vector2(localScale);
        while (cur != null) {
            _position = _position.add(cur.localPosition);
            _scale = _position.add(cur.localScale);
            cur = cur.parent;
        }
        for (var child : children) {
            child.calculatePositionAndScale();
        }
    }

    public Vector2 position() {
        return new Vector2(_position);
    }

    public Vector2 scale() {
        return new Vector2(_scale);
    }

    public void translate(Vector2 vector) {
        localPosition = localPosition.add(vector);
        _position = _position.add(vector);
        for (var child : children) {
            child.calculatePositionAndScale();
        }
    }

    public void translate(Vector2 direction, double magnitude) {
        Vector2 dir = direction.normalized();
        localPosition = localPosition.add(dir.multiply(magnitude));
        _position = _position.add(dir.multiply(magnitude));
        for (var child : children) {
            child.calculatePositionAndScale();
        }
    }

    public void setParent(Transform parent) {
        if (parent == null) {
            return;
        }
        this.parent = parent;
        parent.children.add(this);
    }
}
