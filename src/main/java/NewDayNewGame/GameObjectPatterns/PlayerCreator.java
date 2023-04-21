package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Physics.Collisions.RectCollider;
import NewDayNewGame.Core.Physics.PhysBody;
import NewDayNewGame.Core.Rect;
import NewDayNewGame.Core.Rendering.SpriteRenderer;
import NewDayNewGame.Core.Vector2;
import NewDayNewGame.Scripts.BurgerCounter;
import NewDayNewGame.Scripts.Move.TopDownMove;
import NewDayNewGame.Scripts.PlayerHealth;
import NewDayNewGame.Scripts.Test;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PlayerCreator extends GameObjectCreator {
    private byte keyUp = KeyEvent.VK_UP, keyDown = KeyEvent.VK_DOWN, keyRight = KeyEvent.VK_RIGHT, keyLeft = KeyEvent.VK_LEFT;
    private double speed = 5;

    public PlayerCreator setArrows() {
        keyUp = KeyEvent.VK_UP;
        keyDown = KeyEvent.VK_DOWN;
        keyRight = KeyEvent.VK_RIGHT;
        keyLeft = KeyEvent.VK_LEFT;
        return this;
    }

    public PlayerCreator setWASD() {
        keyUp = KeyEvent.VK_W;
        keyRight = KeyEvent.VK_D;
        keyDown = KeyEvent.VK_S;
        keyLeft = KeyEvent.VK_A;
        return this;
    }

    public PlayerCreator setKeys(byte keyUp, byte keyDown, byte keyRight, byte keyLeft) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyRight = keyRight;
        this.keyLeft = keyLeft;
        return this;
    }

    public PlayerCreator setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        addComponent(new Test());
        GameObject player = super.create();
        player.addComponent(new SpriteRenderer(player, "characters", (byte)1, "frog"));
        TopDownMove move = new TopDownMove(player);
        move.speed = speed;
        move.setKeys(keyUp, keyDown, keyRight, keyLeft);
        player.addComponent(move);
        player.getComponent(SpriteRenderer.class).setSize(new Vector2(0.7, 0.7));
        player.addTag("player");
        player.setLayer("player");
        player.addComponent(new RectCollider(player, new Rect(new Vector2(0, 0), new Vector2(0.7, 0.7))));
        player.addComponent(new PhysBody(player));
        player.addComponent(new BurgerCounter(player));
        player.addComponent(new PlayerHealth(player, 1));
        player.getComponent(RectCollider.class).isTrigger = false;
        return player;
    }
}