package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Vector2;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.TreeSet;

public class FXSpritePanel {
    protected final TreeSet<FXImageContainer> sprites;
    protected TreeSet<FXImageContainer> actualSprites;
    protected Vector2 size;
    public boolean enabled = false;
    private boolean resizeFlag = false;

    public FXSpritePanel() {
        sprites = new TreeSet<>();
        actualSprites = new TreeSet<>();
    }

    public void paint(GraphicsContext g) {
        Platform.runLater(() -> {
            g.setFill(Color.BLACK);
            g.fillRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
            for (var sprite : actualSprites) {
                Image image = sprite.getGraphics();
                ImageView imageView = new ImageView(image);
                imageView.resize(sprite.rendSize.x, sprite.rendSize.y);
                g.drawImage(image, (int) sprite.rendCenter.x, (int) (sprite.rendCenter.y / 2), (int) sprite.rendSize.x, (int) sprite.rendSize.y);
            }
            g.save();
            g.restore();
            g.getCanvas().snapshot(null, null);
        });
    }
}
