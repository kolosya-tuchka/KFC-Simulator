package NewDayNewGame.Scenes;

import NewDayNewGame.Core.*;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.FXDisplay;
import NewDayNewGame.Core.Rendering.SwingDisplay;
import NewDayNewGame.Scripts.FXMainMenu;
import NewDayNewGame.Scripts.SwingGameAbout;
import NewDayNewGame.Scripts.SwingHighScoresTable;
import NewDayNewGame.Scripts.SwingMainMenu;
import java.io.IOException;
import java.util.ArrayList;

public class MenuScene extends SceneCreator {
    @Override
    public Scene createScene() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (var display : Game.getInstance().getDisplays()) {
            display.setVisible(true);
        }
        GameObject mainMenu = new GameObjectCreator().create();
        mainMenu.setEnabled(false);
        if (Game.getInstance().gameGUI == GameGUI.swing) {
            mainMenu.addComponent(new SwingMainMenu(mainMenu, (SwingDisplay) Display.getMainDisplay()));
            var displays = Game.getInstance().getDisplays();
            mainMenu.addComponent(new SwingHighScoresTable(mainMenu, (SwingDisplay) displays.get(1), (SwingDisplay) displays.get(0)));
            mainMenu.addComponent(new SwingGameAbout(mainMenu, (SwingDisplay) displays.get(1), (SwingDisplay) displays.get(0)));
        } else {
            mainMenu.addComponent(new FXMainMenu(mainMenu, (FXDisplay) Display.getMainDisplay()));
        }
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(mainMenu);
        setGameObjects(gameObjects);
        setName("Menu");
        return super.createScene();
    }
}
