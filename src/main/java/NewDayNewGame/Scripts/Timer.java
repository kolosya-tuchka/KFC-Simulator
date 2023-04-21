package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Time;

import java.io.IOException;

public class Timer extends Component {
    private double seconds;

    public Timer(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void start() {
        seconds = 0;
    }

    @Override
    protected void update() throws IOException {
        if (enabled()) {
            seconds += Time.getInstance().deltaTime();
        }
    }

    public double getTime() {
        return seconds;
    }

    public int getSeconds() {
        return (int)(seconds % 60);
    }

    public int getMinutes() {
        return (int)(seconds / 60);
    }
}
