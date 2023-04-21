package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Physics.Collisions.RectCollider;
import NewDayNewGame.Core.Physics.PhysBody;
import NewDayNewGame.Core.Rect;
import NewDayNewGame.Core.Rendering.SpriteRenderer;
import NewDayNewGame.Core.Vector2;
import NewDayNewGame.Scripts.EnemyAttack;
import NewDayNewGame.Scripts.EnemyMove;
import java.io.IOException;

public class EnemyCreator extends GameObjectCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GameObject enemy = super.create();
        enemy.addTag("enemy");
        enemy.addComponent(new SpriteRenderer(enemy, "characters", (byte)2, "gopnik"));
        enemy.getComponent(SpriteRenderer.class).setSize(new Vector2(0.7, 0.7));
        enemy.addComponent(new RectCollider(enemy, new Rect(new Vector2(0, 0), new Vector2(0.7, 0.7))));
        enemy.addComponent(new PhysBody(enemy));
        enemy.addComponent(new EnemyMove(enemy, 3.5, 6));
        enemy.addComponent(new EnemyAttack(enemy, 1, 1));
        return enemy;
    }
}
