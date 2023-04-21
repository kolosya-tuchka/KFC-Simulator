package NewDayNewGame;

import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameCreator;
import NewDayNewGame.Core.Physics.PhysicsCreator;
import NewDayNewGame.Core.Debug;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.FXDisplay;
import NewDayNewGame.Core.Rendering.SwingDisplay;
import NewDayNewGame.Scenes.GameScene;
import NewDayNewGame.Scenes.MenuScene;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewDayNewGame extends GameCreator {
    public NewDayNewGame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        physicsCreator = new PhysicsCreator();
        physicsCreator.loadLayersMask(new File("src/main/java/NewDayNewGame/Core/Physics/layersMask.txt")).
                loadLayers(new File("src/main/java/NewDayNewGame/Core/Physics/layers.txt")).
                createPhysics();
        sceneCreators = new ArrayList<>();
        sceneCreators.add(new MenuScene());
        sceneCreators.add(new GameScene());
        Debug debug = new Debug(System.out);
    }

    @Override
    public Game createGame() throws IOException {
        switch (gameGUI) {
            case fx -> loadFXGUI();
            case swing -> loadSwingGUI();
        }
        Game game = super.createGame();
        game.targetFPS = 40;
        return game;
    }

    private void loadSwingGUI() throws IOException {
        Display.setMainDisplay(new SwingDisplay(500, 500));
        SwingDisplay.loadSprites(new File("src/main/java/NewDayNewGame/Sprites/sprites.txt"));
        displays = new ArrayList<>();
        displays.add(Display.getMainDisplay());
        displays.add(new SwingDisplay(500, 500));
    }

    private void loadFXGUI() throws IOException {
        Display.setMainDisplay(new FXDisplay(500, 500));
        FXDisplay.loadSprites(new File("src/main/java/NewDayNewGame/Sprites/altSprites.txt"));
        displays = new ArrayList<>();
        displays.add(Display.getMainDisplay());
        displays.add(new FXDisplay(500, 500));
    }
}