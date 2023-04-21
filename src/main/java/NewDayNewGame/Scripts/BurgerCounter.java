package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Physics.Collisions.Collider;
import java.io.IOException;

public class BurgerCounter extends Component {
    private static int burgerCount = 0;

    public BurgerCounter(GameObject player) {
        super(player);
        burgerCount = 0;
    }

    @Override
    protected void onTriggerEnter(Collider other) throws IOException {
        if (other.gameObject().hasTag("burger")) {
            ++burgerCount;
            other.gameObject().destroy();
        }
    }

    public static int getBurgerCount() {
        return burgerCount;
    }
}
