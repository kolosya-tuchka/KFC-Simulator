package NewDayNewGame.GameObjectPatterns.Tiles;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Rendering.SpriteRenderer;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public abstract class TileCreator extends GameObjectCreator {
    protected String image;
    protected Vector2 size;
    private String sortingLayer = "default";
    private int orderInLayer = 0;

    public TileCreator setRenderingOrder(String sortingLayer, int orderInLayer) {
        this.sortingLayer = sortingLayer;
        this.orderInLayer = orderInLayer;
        return this;
    }

    public TileCreator setImage(String image) {
        this.image = image;
        return this;
    }

    public TileCreator setSize(Vector2 size) {
        this.size = new Vector2(size);
        return this;
    }

    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (image == null) {
            return null;
        }
        if (size == null) {
            size = new Vector2(1, 1);
        }

        GameObject tile = super.create();
        tile.addComponent(new SpriteRenderer(null, sortingLayer, (byte)orderInLayer, image));
        tile.getComponent(SpriteRenderer.class).setSize(size);
        return tile;
    }
}
