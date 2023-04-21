package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.*;
import NewDayNewGame.Core.Physics.Collisions.RectCollider;
import NewDayNewGame.Core.Physics.Physics;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.HashMap;

public class Camera extends Component {
    private Display display;
    private static Camera main;
    public final HashMap<String, Boolean> drawLayer;
    public double size = 5;
    private double width, height;

    public Camera(GameObject gameObject, @NotNull Display display, double size) {
        super(gameObject);

        if (main == null) {
            main = this;
        }

        this.display = display;
        this.size = size;
        drawLayer = new HashMap<>();
        for (var layer : Physics.getInstance().getLayers()) {
            drawLayer.put(layer, true);
        }
    }

    @Override
    public void onRender() throws IOException {
        display.clear();
        width = size;
        height = size / display.getWidth() * display.getHeight();
        Vector2 rectSize = new Vector2(width, height);
        Rect rect = new Rect(transform().position().sub(new Vector2(0, height)), rectSize.scale(new Vector2(2, 3)));
        RectCollider collider = new RectCollider(null, rect);
        for (var rend : display) {
            if (!rend.enabled()) {
                return;
            }
            RectCollider other = new RectCollider(null, new Rect(rend.getCenter(), rend.getSize()));
            if (!collider.isTouching(other)) {
                continue;
            }

            if (!drawLayer.get(rend.gameObject().layer())) {
                continue;
            }

            double ratio = display.getWidth() / size;
            Vector2 rendSize = rend.size.multiply(ratio);
            Vector2 rendCenter = rend.transform().position().sub(transform().position()).add(new Vector2(0, 1)).multiply(ratio).add(new Vector2(display.getWidth() / 2, display.getHeight() / 2));
            display.addSprite(rend, rendCenter, rendSize);
        }
    }

    public static Camera getMainCamera() {
        return main;
    }

    public Display getDisplay() {
        return display;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
