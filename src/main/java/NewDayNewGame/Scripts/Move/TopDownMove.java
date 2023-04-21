package NewDayNewGame.Scripts.Move;

import NewDayNewGame.Core.*;
import NewDayNewGame.Core.Physics.PhysBody;
import NewDayNewGame.GameObjectPatterns.PlayerCreator;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class TopDownMove extends Component {
    public int keyUp = KeyEvent.VK_UP, keyRight = KeyEvent.VK_RIGHT, keyDown = KeyEvent.VK_DOWN, keyLeft = KeyEvent.VK_LEFT;
    public double speed;
    private PhysBody body;

    public TopDownMove(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void awake() {
        body = gameObject().getComponent(PhysBody.class);
    }

    public void setKeys(byte keyUp, byte keyDown, byte keyRight, byte keyLeft) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyRight = keyRight;
        this.keyLeft = keyLeft;
    }

    @Override
    protected void update() throws IOException {
        Input input = Input.getInstance();
        Vector2 velocity = new Vector2(0, 0);

        if (input.getKeyPressed(keyUp)) {
            velocity.y -= 1;
        }
        if (input.getKeyPressed(keyRight)) {
            velocity.x += 1;
        }
        if (input.getKeyPressed(keyDown)) {
            velocity.y += 1;
        }
        if (input.getKeyPressed(keyLeft)) {
            velocity.x -= 1;
        }

        velocity = velocity.normalized().multiply(speed);
        body.velocity.x = velocity.x;
        body.velocity.y = velocity.y;
    }
}
