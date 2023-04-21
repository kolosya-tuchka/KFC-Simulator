package NewDayNewGame.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Scene {
    protected HashSet<GameObject> gameObjects;
    private String name;

    public Scene(String name, ArrayList<GameObject> gameObjects) {
        this.name = name;
        this.gameObjects = new HashSet<>();
        for (var gameObject : gameObjects) {
            this.gameObjects.add(gameObject);
        }
    }

    public String getName() {
        return name;
    }

    protected void awake() {
        for (var gameObject : gameObjects) {
            gameObject.awake();
        }

        for (var gameObject : gameObjects) {
            gameObject.onEnable();
        }
    }

    protected void start() {
        for (var gameObject : gameObjects) {
            if (gameObject.enabled()) {
                gameObject.start();
            }
        }
    }

    protected void update() throws IOException {
        ArrayList<GameObject> toDestroy = new ArrayList<>();
        for (var gameObject : gameObjects) {
            if (gameObject.destroyFlag) {
                toDestroy.add(gameObject);
            }
        }
        for (var gameObject : toDestroy) {
            gameObjects.remove(gameObject);
        }
        for (var gameObject : gameObjects) {
            if (gameObject.enabled()) {
                gameObject.update();
            }
        }
    }

    protected void lateUpdate() throws IOException {
        for (var gameObject : gameObjects) {
            if (gameObject.enabled()) {
                gameObject.lateUpdate();
            }
        }
    }

    protected void onRender() throws IOException {
        for (var gameObject : gameObjects) {
            if (gameObject.enabled()) {
                gameObject.onRender();
            }
        }
    }

    public <T extends Component> HashSet<T> findObjectsOfType(Class<T> tClass) {
        if (tClass.equals(GameObject.class)) {
            return (HashSet<T>) gameObjects;
        }
        HashSet<T> result = new HashSet<>();
        for (var gameObject : gameObjects) {
            for (var component : gameObject.getComponents(Component.class)) {
                if (tClass.isInstance(component)) {
                    result.add((T) component);
                }
            }
        }
        return result;
    }

    public <T extends Component> T findObjectOfType(Class<T> tClass) {
        var temp = findObjectsOfType(tClass);
        return temp.size() == 0 ? null : (T) temp.toArray()[0];
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
