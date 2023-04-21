package NewDayNewGame.Core;

import NewDayNewGame.Core.Physics.Collisions.Collider;
import NewDayNewGame.Core.Physics.Physics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class GameObject extends Component {
    private String _name, _layer;
    public boolean destroyFlag = false;
    public boolean dontDestroyOnLoad = false;
    private List<Component> components;
    private TreeSet<String> tags;

    public GameObject(TreeSet<String> tags, String name, String layer) {
        super();
        _gameObject = this;
        _transform = new Transform(this);
        components = new ArrayList<>();
        this.tags = (TreeSet<String>) tags.clone();
        setName(name);
        setLayer(layer);
    }

    public void addComponent(Component component) {
        component._gameObject = this;
        component._transform = _transform;
        components.add(component);

        if (!enabled() || Game.getInstance().getCurrentScene() == null) {
            return;
        }

        component.awake();
        component.onEnable();
        component.start();
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public <T extends Component> T getComponent(Class<T> tClass) {
        for (var component : components) {
            if (tClass.isInstance(component)) {
                return (T)component;
            }
        }
        return null;
    }

    public <T extends Component> ArrayList<T> getComponents(Class<T> tClass) {
        ArrayList<T> result = new ArrayList<>();
        for (var component : components) {
            if (tClass.isInstance(component)) {
                result.add((T)component);
            }
        }
        return result;
    }

    @Override
    public void destroy() {
        _enabled = false;
        for (var child : transform().children) {
            child.gameObject().destroy();
        }
        transform().getParent().children.remove(transform());
        destroyFlag = true;
    }

    @Override
    public void awake() {
        for (var component : components) {
            component.awake();
        }
    }

    @Override
    public void start() {
        for (var component : components) {
            if (component.enabled()) {
                component.start();
            }
        }
        _transform.start();
    }

    @Override
    public void update() throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.update();
            }
        }
    }

    @Override
    public void lateUpdate()  {
        for (var component : components) {
            if (component.enabled()) {
                component.lateUpdate();
            }
        }
        _transform.update();
    }

    @Override
    public void onEnable() {
        for (var component : components) {
            if (component.enabled()) {
                component.onEnable();
            }
        }
    }

    @Override
    public void onDisable() {
        for (var component : components) {
            if (component.enabled()) {
                component.onDisable();
            }
        }
    }

    @Override
    public void onCollisionEnter(Collider other) throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.onCollisionEnter(other);
            }
        }
    }

    @Override
    public void onCollisionStay(Collider other) {
        for (var component : components) {
            if (component.enabled()) {
                component.onCollisionStay(other);
            }
        }
    }

    @Override
    public void onCollisionExit(Collider other) {
        for (var component : components) {
            if (component.enabled()) {
                component.onCollisionExit(other);
            }
        }
    }

    @Override
    public void onTriggerEnter(Collider other) throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.onTriggerEnter(other);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled != _enabled) {
            _enabled = enabled;
            for (var component : components) {
                component.setEnabled(enabled);
            }
            for (var child : transform().children) {
                child.gameObject().setEnabled(enabled);
            }
        }
    }

    @Override
    public void onTriggerStay(Collider other) throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.onTriggerStay(other);
            }
        }
    }

    @Override
    public void onTriggerExit(Collider other) throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.onTriggerExit(other);
            }
        }
    }

    @Override
    public void onRender() throws IOException {
        for (var component : components) {
            if (component.enabled()) {
                component.onRender();
            }
        }
    }

    public String name() {
        return _name;
    }

    public String layer() {
        return _layer;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setLayer(String layer) {
        if (Physics.getInstance().hasLayer(layer)) {
            _layer = layer;
        }
    }

    public static GameObject findWithTag(String tag) {
        for (var gameObject : Game.getInstance().getCurrentScene().gameObjects) {
            if (gameObject.tags.contains(tag)) {
                return gameObject;
            }
        }
        return null;
    }

    public static ArrayList<GameObject> findAllWithTag(String tag) {
        ArrayList<GameObject> out = new ArrayList<>();
        for (var gameObject : Game.getInstance().getCurrentScene().gameObjects) {
            if (gameObject.tags.contains(tag)) {
                out.add(gameObject);
            }
        }
        return out;
    }

    public static GameObject findWithName(String name) {
        for (var gameObject : Game.getInstance().getCurrentScene().gameObjects) {
            if (Objects.equals(gameObject.name(), name)) {
                return gameObject;
            }
        }
        return null;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    @Override
    protected void onSceneLoad() {
        for (var component : components) {
            component.onSceneLoad();
        }
    }
}
