package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Vector2;
import NewDayNewGame.Scripts.BurgerManager;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BurgerManagerCreator extends GameObjectCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        var manager = super.create();
        Scanner scanner = new Scanner(new File("src/main/java/NewDayNewGame/LevelConfigs/burgerPlaces.txt"));
        BurgerCreator burgerCreator = new BurgerCreator();
        while (scanner.hasNext()) {
            String[] coords = scanner.nextLine().split(" ");
            if (coords.length != 2) {
                continue;
            }
            var burger = burgerCreator.create(new Vector2(Double.parseDouble(coords[0]) - 31, Double.parseDouble(coords[1]) * 2 - 21));
            burger.transform().setParent(manager.transform());
        }
        manager.addComponent(new BurgerManager(manager));
        return manager;
    }
}
