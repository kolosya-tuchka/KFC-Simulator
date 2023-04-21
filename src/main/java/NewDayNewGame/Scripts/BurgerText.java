package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.UI.TextRenderer;

import java.io.IOException;

public class BurgerText extends Component {
    private TextRenderer textRenderer;
    private BurgerManager manager;

    public BurgerText(GameObject gameObject, TextRenderer textRenderer, BurgerManager manager) {
        super(gameObject);
        this.textRenderer = textRenderer;
        this.manager = manager;
    }

    @Override
    protected void update() throws IOException {
        textRenderer.setText("Burgers: " + BurgerCounter.getBurgerCount() + "/" + manager.getBurgersCount());
    }
}
