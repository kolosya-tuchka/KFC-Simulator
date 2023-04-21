package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Physics.PhysBody;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;
import java.util.ArrayList;

public class EnemyMove extends Component {
    private PhysBody body;
    private double speed;
    private double seeDistance;
    private ArrayList<GameObject> players;

    public EnemyMove(GameObject gameObject, double speed, double seeDistance) {
        super(gameObject);
        this.speed = speed;
        this.seeDistance = seeDistance;
        body = gameObject.getComponent(PhysBody.class);
    }

    @Override
    protected void start() {
        players = GameObject.findAllWithTag("player");
    }

    @Override
    protected void update() throws IOException {
        for (var player : players) {
            if (Vector2.distance(transform().position(), player.transform().position()) <= seeDistance) {
                Vector2 direction = player.transform().position().sub(transform().position()).normalized().multiply(speed);
                body.velocity.x = direction.x;
                body.velocity.y = direction.y;
                return;
            }
        }
        body.velocity.x = body.velocity.y = 0;
    }
}
