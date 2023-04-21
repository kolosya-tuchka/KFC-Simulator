package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.UI.TextRenderer;
import NewDayNewGame.Core.Vector2;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FXImageContainer extends ImageContainer {
    public FXImageContainer(Renderer renderer, Vector2 rendCenter, Vector2 rendSize) {
        super(renderer, rendCenter, rendSize);
    }

    protected Image getGraphics() {
        if (renderer instanceof SpriteRenderer sprite) {
            return FXDisplay.getSprite(sprite.spriteID);
        } else if (renderer instanceof TextRenderer textRenderer) {
            return null;
        }
        return null;
    }

    private Image textToImage(String text) {
        Text temp = new Text(text);
        Scene scene = new Scene(new StackPane(temp));
        rendCenter = rendCenter.sub(new Vector2(70, 0));
        return temp.snapshot(null, null);
    }
}
