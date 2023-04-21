package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameJudge extends Component {
    private ActionEvent onLose, onWin;
    private ArrayList<ActionListener> listeners;

    public GameJudge(GameObject gameObject) {
        super(gameObject);
        onLose = new ActionEvent(this, 0, "lose");
        onWin = new ActionEvent(this, 1, "win");
        listeners = new ArrayList<>();
    }

    public void lose() {
        for (var listener : listeners) {
            listener.actionPerformed(onLose);
        }
    }

    public void win() {
        for (var listener : listeners) {
            listener.actionPerformed(onWin);
        }
    }

    public void addListener(ActionListener listener) {
        listeners.add(listener);
    }
}
