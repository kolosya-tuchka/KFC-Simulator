package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.FXDisplay;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMainMenu extends Component {
    private FXDisplay fxDisplay;

    public FXMainMenu(GameObject gameObject, FXDisplay fxDisplay) {
        super(gameObject);
        this.fxDisplay = fxDisplay;
    }

    @Override
    protected void start() {
        fxDisplay = (FXDisplay) Display.getMainDisplay();
        Label label = new Label("KFC Simulator");
        Button button = new Button("Start");
        button.setOnAction(e -> Game.getInstance().setNextScene(1));
        Platform.runLater(() -> {
            fxDisplay.getPane().getChildren().remove(fxDisplay.getCanvas());
            fxDisplay.getPane().getChildren().addAll(label, button);
        });
    }
}
