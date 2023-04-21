package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public class SpriteRenderer extends Renderer {
    public String spriteID;

    public SpriteRenderer(GameObject gameObject, String sortingLayer, byte orderInLayer, String spriteID) throws IOException {
        super(gameObject, sortingLayer, orderInLayer, new Vector2(1, 1));
        this.spriteID = spriteID;
    }
}
