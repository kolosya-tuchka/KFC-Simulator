package NewDayNewGame.Scenes;

import NewDayNewGame.Core.*;
import NewDayNewGame.Core.Rendering.Camera;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.FXDisplay;
import NewDayNewGame.Core.Rendering.SwingDisplay;
import NewDayNewGame.Core.UI.TextRenderer;
import NewDayNewGame.Core.UI.Canvas;
import NewDayNewGame.GameObjectPatterns.*;
import NewDayNewGame.Scripts.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameScene extends SceneCreator {
    @Override
    public Scene createScene() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Time.getInstance().timeScale = 1;
        for (var display : Game.getInstance().getDisplays()) {
            if (display instanceof SwingDisplay swingDisplay) {
                display.setVisible(true);
                swingDisplay.getPanel().enabled = true;
                swingDisplay.getFrame().setResizable(true);
                swingDisplay.getFrame().setSize(500, 500);
            } else if (display instanceof FXDisplay fxDisplay) {
                fxDisplay.setVisible(true);
                fxDisplay.getStage().setResizable(true);
                fxDisplay.getStage().setWidth(500);
                fxDisplay.getStage().setHeight(500);
                fxDisplay.getCanvas().setVisible(true);
            }
        }
        setName("Game");

        ArrayList<GameObject> gameObjects = new ArrayList<>();
        var player1 = new PlayerCreator().setSpeed(7).setName("player1").create(new Vector2(-27, 45));
        var player2 = new PlayerCreator().setSpeed(7).setWASD().setName("player2").create(new Vector2(-27, 48));
        var camera = new CameraCreator().setDisplay(Display.getMainDisplay()).setSize(20).setName("camera1").setParent(player1.transform()).create();

        var timer = new GameObjectCreator().create();
        timer.addComponent(new Timer(timer));

        GameObject highScores = new GameObjectCreator().create();
        highScores.addComponent(new HighScoresManager(highScores, timer.getComponent(Timer.class)));

        var canvas1 = new GameObjectCreator().create();
        canvas1.addComponent(new Canvas(canvas1, camera.getComponent(Camera.class)));
        if (Game.getInstance().gameGUI == GameGUI.swing) {
            canvas1.addComponent(new SwingGameOverPanel(canvas1, (SwingDisplay) Display.getMainDisplay(), timer.getComponent(Timer.class), highScores.getComponent(HighScoresManager.class)));
        } else {
            canvas1.addComponent(new FXGameOverPanel(canvas1, (FXDisplay) Display.getMainDisplay(), timer.getComponent(Timer.class)));
        }
        var burgerText1 = new GameObjectCreator().create(new Vector2(-0.6, -1));
        burgerText1.addComponent(new TextRenderer(burgerText1, "ui", (byte) 0, canvas1.getComponent(Canvas.class)));
        burgerText1.getComponent(TextRenderer.class).setSize(new Vector2(5, 1));
        var timerText1 = new GameObjectCreator().create(new Vector2(0.8, -1));
        timerText1.addComponent(new TextRenderer(timerText1, "ui", (byte) 0, canvas1.getComponent(Canvas.class)));
        timerText1.getComponent(TextRenderer.class).setSize(new Vector2(5, 1));
        timerText1.addComponent(new TimerText(timerText1, timer.getComponent(Timer.class), timerText1.getComponent(TextRenderer.class)));

        var manager = new BurgerManagerCreator().create();
        for (var burger : manager.transform().children) {
            gameObjects.add(burger.gameObject());
        }
        gameObjects.add(manager);

        var enemies = new EnemiesCreator().create();
        for (var enemy : enemies.transform().children) {
            gameObjects.add(enemy.gameObject());
        }
        gameObjects.add(enemies);

        burgerText1.addComponent(new BurgerText(burgerText1, burgerText1.getComponent(TextRenderer.class), manager.getComponent(BurgerManager.class)));

        gameObjects.add(highScores);
        gameObjects.add(timer);
        gameObjects.add(new GameJudgeCreator().create());
        gameObjects.add(player1);
        gameObjects.add(player2);
        gameObjects.add(canvas1);
        gameObjects.add(burgerText1);
        gameObjects.add(timerText1);
        gameObjects.add(camera);

        var tilemapParent = new TilemapCreator().setTileSize(new Vector2(1, 1)).create();
        gameObjects.add(tilemapParent);
        for (var tile : tilemapParent.transform().children) {
            gameObjects.add(tile.gameObject());
        }

        setGameObjects(gameObjects);
        Scene scene = super.createScene();
        GameObject camera2 = new CameraCreator().
                setDisplay(Game.getInstance().getDisplays().get(1)).
                setSize(20).
                setName("camera2").
                setParent(player2.transform()).
                create();
        camera2.setEnabled(true);
        var canvas2 = new GameObjectCreator().create();
        canvas2.addComponent(new Canvas(canvas2, camera2.getComponent(Camera.class)));
        var burgerText2 = new GameObjectCreator().create(new Vector2(-0.6, -1));
        burgerText2.addComponent(new TextRenderer(burgerText2, "ui", (byte) 0, canvas2.getComponent(Canvas.class)));
        burgerText2.getComponent(TextRenderer.class).setSize(new Vector2(5, 1));
        burgerText2.addComponent(new BurgerText(burgerText2, burgerText2.getComponent(TextRenderer.class), manager.getComponent(BurgerManager.class)));
        var timerText2 = new GameObjectCreator().create(new Vector2(0.8, -1));
        timerText2.addComponent(new TextRenderer(timerText2, "ui", (byte) 0, canvas2.getComponent(Canvas.class)));
        timerText2.getComponent(TextRenderer.class).setSize(new Vector2(5, 1));
        timerText2.addComponent(new TimerText(timerText2, timer.getComponent(Timer.class), timerText2.getComponent(TextRenderer.class)));

        scene.addGameObject(canvas2);
        scene.addGameObject(burgerText2);
        scene.addGameObject(timerText2);
        scene.addGameObject(camera2);
        return scene;
    }
}
