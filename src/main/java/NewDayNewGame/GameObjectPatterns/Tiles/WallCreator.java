package NewDayNewGame.GameObjectPatterns.Tiles;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Physics.Collisions.RectCollider;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public class WallCreator extends TileCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject tile = super.create();
        tile.addComponent(new RectCollider(tile, new Vector2(0, 0), size));
        return tile;
    }
}
