package NewDayNewGame.Core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public abstract class SceneCreator {
    private String name = "scene";
    private ArrayList<GameObject> gameObjects;

    public SceneCreator setName(String name) {
        this.name = name;
        return this;
    }

    public SceneCreator setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        return this;
    }

    public SceneCreator addGameObject(@NotNull GameObject gameObject) {
        gameObjects.add(gameObject);
        return this;
    }

    public SceneCreator removeGameObject(@NotNull GameObject gameObject) {
        gameObjects.remove(gameObject);
        return this;
    }

    public String getName() {
        return name;
    }

    public Scene createScene() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return new Scene(name, gameObjects);
    }
}
