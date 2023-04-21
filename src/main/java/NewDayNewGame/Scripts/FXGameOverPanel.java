package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.FXDisplay;
import NewDayNewGame.Core.Time;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FXGameOverPanel extends Component implements ActionListener {
    private FXDisplay fxDisplay;
    private Timer timer;

    public FXGameOverPanel(GameObject gameObject, FXDisplay fxDisplay, Timer timer) {
        super(gameObject);
        this.fxDisplay = fxDisplay;
        this.timer = timer;
    }

    @Override
    protected void start() {
        Platform.runLater(() -> {
            if (!fxDisplay.getPane().getChildren().contains(fxDisplay.getCanvas())) {
                fxDisplay.getPane().getChildren().add(fxDisplay.getCanvas());
            }
        });
        GameJudge judge = Game.getInstance().getCurrentScene().findObjectOfType(GameJudge.class);
        judge.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Platform.runLater(() -> {
            if (e.getActionCommand().equals("lose")) {
                Time.getInstance().timeScale = 0;
                fxDisplay.getPane().getChildren().remove(fxDisplay.getCanvas());
                VBox pane = fxDisplay.getPane();
                pane.getChildren().clear();
                Label label = new Label("You lose");
                Button restart = new Button("Restart");
                restart.setOnAction(actionEvent -> Game.getInstance().setNextScene(1));
                Button menu = new Button("Menu");
                menu.setOnAction(actionEvent -> Game.getInstance().setNextScene(0));
                pane.getChildren().addAll(label, restart, menu);
            } else if (e.getActionCommand().equals("win")) {
                Time.getInstance().timeScale = 0;
                fxDisplay.getPane().getChildren().remove(fxDisplay.getCanvas());
                VBox pane = fxDisplay.getPane();
                pane.getChildren().clear();
                Label label = new Label("You won!");
                String seconds = timer.getSeconds() < 10 ? "0" + timer.getSeconds() : String.valueOf(timer.getSeconds());
                String minutes = timer.getMinutes() < 10 ? "0" + timer.getMinutes() : String.valueOf(timer.getMinutes());
                Label timeLabel = new Label("Time: " + minutes + ":" + seconds);
                Button restart = new Button("Restart");
                restart.setOnAction(actionEvent -> Game.getInstance().setNextScene(1));
                Button menu = new Button("Menu");
                menu.setOnAction(actionEvent -> Game.getInstance().setNextScene(0));
                pane.getChildren().addAll(label, timeLabel, restart, menu);
            }
        });
    }
}
