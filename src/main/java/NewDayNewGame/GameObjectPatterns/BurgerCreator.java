package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Physics.Collisions.RectCollider;
import NewDayNewGame.Core.Rect;
import NewDayNewGame.Core.Rendering.SpriteRenderer;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public class BurgerCreator extends GameObjectCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject burger = super.create();
        burger.addTag("burger");
        burger.addComponent(new SpriteRenderer(burger, "default", (byte)2, "burger"));
        burger.getComponent(SpriteRenderer.class).setSize(new Vector2(0.6, 0.6));
        burger.addComponent(new RectCollider(burger, new Rect(new Vector2(0, 0), new Vector2(0.6, 0.6))));
        burger.getComponent(RectCollider.class).isTrigger = true;
        return burger;
    }
}
