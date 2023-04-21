package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Rendering.Camera;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Vector2;

import java.io.IOException;

public class CameraCreator extends GameObjectCreator {
    private Display display;
    private double size;

    public CameraCreator() {
        super();
        setDisplay(display);
        setSize(size);
    }

    public CameraCreator setSize(double size) {
        this.size = size;
        return this;
    }

    public CameraCreator setDisplay(Display display) {
        this.display = display;
        return this;
    }

    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject camera = super.create();
        camera.addComponent(new Camera(camera, display, size));
        return camera;
    }
}
