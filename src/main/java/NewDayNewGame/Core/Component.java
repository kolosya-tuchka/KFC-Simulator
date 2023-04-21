package NewDayNewGame.Core;

import NewDayNewGame.Core.Physics.Collisions.Collider;
import java.io.IOException;

public abstract class Component {
    protected boolean _enabled = true;

    protected GameObject _gameObject;
    protected Transform _transform;

    public Component(GameObject gameObject) {
        if (gameObject == null) {
            return;
        }

        _gameObject = gameObject;
        _transform = _gameObject.transform();
    }

    protected Component() {}

    public GameObject gameObject() {
        return _gameObject;
    }

    public Transform transform() {
        return _transform;
    }

    public boolean enabled() {
        return _enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled != _enabled) {
            if (enabled) {
                _enabled = true;
                onEnable();
            } else {
                _enabled = false;
                onDisable();
            }
        }
    }

    public void destroy() {
        gameObject().removeComponent(this);
        onDestroy();
    }

    protected void update() throws IOException {}
    protected void lateUpdate() {}
    protected void start() {}
    protected void awake() {}
    protected void onCollisionEnter(Collider other) throws IOException {}
    protected void onCollisionStay(Collider other) {}
    protected void onCollisionExit(Collider other) {}
    protected void onTriggerEnter(Collider other) throws IOException {}
    protected void onTriggerStay(Collider other) throws IOException {}
    protected void onTriggerExit(Collider other) throws IOException {}
    protected void onEnable() {}
    protected void onDisable() {}
    protected void onDestroy() {}
    protected void onRender() throws IOException {}
    protected void onSceneLoad() {}
}
