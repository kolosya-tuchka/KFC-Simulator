package NewDayNewGame.Core;

import NewDayNewGame.Core.Physics.Physics;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.RenderingThread;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private static Game instance;
    private Scene currentScene;
    private boolean run = true;
    protected ArrayList<Display> displays;
    public double targetFPS = 60;
    protected ArrayList<SceneCreator> scenesInGame;
    public static Game getInstance() {
        return instance;
    }

    private boolean loadFlag = false;
    private int nextSceneIndex = 0;
    private boolean _canRender = false;
    public final GameGUI gameGUI;

    public Game(ArrayList<SceneCreator> scenesInGame, ArrayList<Display> displays, GameGUI gameGUI) {
        this.scenesInGame = scenesInGame;
        this.displays = (ArrayList<Display>) displays.clone();
        this.gameGUI = gameGUI;
        instance = this;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setNextScene(int index) {
        nextSceneIndex = index;
        loadFlag = true;
    }

    private void loadScene(int index) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        loadFlag = false;
        //ArrayList<GameObject> dontDestroyOnLoad = new ArrayList<>();
        if (currentScene != null) {
            /*for (var gameObject : currentScene.gameObjects) {
                if (gameObject.dontDestroyOnLoad) {
                    dontDestroyOnLoad.add(gameObject);
                }
            }*/
            currentScene.gameObjects.clear();
            Display.getMainDisplay().onSceneLoad();
            Physics.getInstance().onSceneLoad();
        }
        currentScene = scenesInGame.get(index).createScene();
        /*for (var gameObject : dontDestroyOnLoad) {
            gameObject.onSceneLoad();
        }*/
        //currentScene.gameObjects.addAll(dontDestroyOnLoad);
        currentScene.awake();
        for (var display : displays) {
            display.awake();
        }
        for (var gameObject : currentScene.gameObjects) {
            gameObject.setEnabled(true);
        }
        currentScene.start();
    }

    private void loadScene(@NotNull String name) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int i = 0;
        while (!Objects.equals(scenesInGame.get(i).getName(), name)) {
            ++i;
        }
        loadScene(i);
    }

    public void init() throws InterruptedException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        loadScene(0);
        RenderingThread renderingThread = new RenderingThread();
        renderingThread.setDaemon(true);
        renderingThread.start();
        gameCycle();
    }

    private void gameCycle() throws InterruptedException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        while (run) {
            if (loadFlag) {
                _canRender = false;
                loadScene(nextSceneIndex);
            }
            Time.getInstance().startFrame();
            Input.getInstance().update();
            currentScene.update();
            currentScene.lateUpdate();
            Physics.getInstance().update();
            _canRender = false;
            currentScene.onRender();
            _canRender = true;
            Time.getInstance().endFrame();
            Time.getInstance().nextFrame();
        }
    }

    public ArrayList<Display> getDisplays() {
        return displays;
    }

    public boolean canRender() {
        return _canRender;
    }

    public void quit() {
        run = false;
    }
}
