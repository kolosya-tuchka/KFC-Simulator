package NewDayNewGame;

import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameGUI;
import NewDayNewGame.Core.Rendering.FXDisplay;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.Scanner;

public class GameApplication extends Application {
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[1];
            Scanner scanner = new Scanner(System.in);
            args[0] = scanner.next();
        }
        try {
            if (args[0].equals("fx")) {
                launch();
            } else {
                Game game = (new NewDayNewGame()).setGUI(GameGUI.swing).createGame();
                game.init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXDisplay.primaryStage = stage;
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        Game game = (new NewDayNewGame()).setGUI(GameGUI.fx).createGame();
        Thread gameThread = new Thread(() -> {
            try {
                game.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }
}