package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.UI.TextRenderer;

import java.io.IOException;

public class TimerText extends Component {
    private Timer timer;
    private TextRenderer textRenderer;

    public TimerText(GameObject gameObject, Timer timer, TextRenderer textRenderer) {
        super(gameObject);
        this.timer = timer;
        this.textRenderer = textRenderer;
    }

    @Override
    protected void update() throws IOException {
        String seconds = timer.getSeconds() < 10 ? "0" + timer.getSeconds() : String.valueOf(timer.getSeconds());
        String minutes = timer.getMinutes() < 10 ? "0" + timer.getMinutes() : String.valueOf(timer.getMinutes());
        textRenderer.setText(minutes + ":" + seconds);
    }
}
