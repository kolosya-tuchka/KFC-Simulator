package NewDayNewGame.Core;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import NewDayNewGame.Core.Physics.Physics;
import org.jetbrains.annotations.NotNull;

public class GameObjectCreator {
    private ArrayList<Component> components;
    private TreeSet<String> tags;
    private String layer, name;
    private Transform parent;

    public GameObjectCreator() {
        components = new ArrayList<>();
        tags = new TreeSet<>();
        name = "gameObject";
        layer = "default";
    }

    public GameObjectCreator setParent(Transform parent) {
        this.parent = parent;
        return this;
    }

    public GameObjectCreator setTags(@NotNull TreeSet<String> tags) {
        this.tags = (TreeSet<String>) tags.clone();
        return this;
    }

    public GameObjectCreator setComponents(@NotNull ArrayList<Component> components) {
        this.components = (ArrayList<Component>) components.clone();
        return this;
    }

    public GameObjectCreator setLayer(@NotNull String layer) {
        if (Physics.getInstance().hasLayer(layer)) {
            this.layer = layer;
        }
        return this;
    }

    public GameObjectCreator setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public GameObjectCreator addTag(@NotNull String tag) {
        tags.add(tag);
        return this;
    }

    public GameObjectCreator removeTag(@NotNull String tag) {
        tags.remove(tag);
        return this;
    }

    public GameObjectCreator addComponent(@NotNull Component component) {
        components.add(component);
        return this;
    }

    public GameObjectCreator removeComponent(@NotNull Component component) {
        components.remove(component);
        return this;
    }

    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject gameObject = new GameObject(tags, name, layer);
        gameObject._enabled = false;
        for (var component : components) {
            gameObject.addComponent(component);
        }
        gameObject.transform().setParent(parent);
        return gameObject;
    }

    public GameObject create(Vector2 position) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject gameObject = create();
        gameObject.transform().localPosition = new Vector2(position);
        return gameObject;
    }
}
