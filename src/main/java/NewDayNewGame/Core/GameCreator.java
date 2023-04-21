package NewDayNewGame.Core;

import NewDayNewGame.Core.Physics.PhysicsCreator;
import NewDayNewGame.Core.Rendering.Display;

import java.io.IOException;
import java.util.ArrayList;

public abstract class GameCreator {
    public PhysicsCreator physicsCreator;
    public ArrayList<SceneCreator> sceneCreators;
    public ArrayList<Display> displays;
    public GameGUI gameGUI;

    public Game createGame() throws IOException {
        Game game = new Game(sceneCreators, displays, gameGUI);
        Time time = new Time();
        Input input = new Input();
        SwingInputController inputController = new SwingInputController();
        return game;
    }

    public GameCreator setGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        return this;
    }
}
