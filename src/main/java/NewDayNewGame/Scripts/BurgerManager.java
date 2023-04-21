package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import java.io.IOException;

public class BurgerManager extends Component {
    private int burgersCount = 0;

    public BurgerManager(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void start() {
        burgersCount = GameObject.findAllWithTag("burger").size();
        //burgersCount = 3;
    }

    public int getBurgersCount() {
        return burgersCount;
    }

    @Override
    protected void update() throws IOException {
        if (BurgerCounter.getBurgerCount() >= burgersCount) {
            GameJudge judge = Game.getInstance().getCurrentScene().findObjectOfType(GameJudge.class);
            if (judge != null) {
                judge.win();
            }
            setEnabled(false);
        }
    }
}
