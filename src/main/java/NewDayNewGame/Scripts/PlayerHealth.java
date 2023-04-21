package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;

public class PlayerHealth extends Component {
    private double health;

    public PlayerHealth(GameObject gameObject, double health) {
        super(gameObject);
        this.health = health;
    }

    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        if (health == 0) {
            GameJudge judge = Game.getInstance().getCurrentScene().findObjectOfType(GameJudge.class);
            judge.lose();
        }
    }
}
