package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Core.Vector2;
import NewDayNewGame.Scripts.BurgerManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EnemiesCreator extends GameObjectCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        var enemiesParent = super.create();
        Scanner scanner = new Scanner(new File("src/main/java/NewDayNewGame/LevelConfigs/enemyPlaces.txt"));
        EnemyCreator enemyCreator = new EnemyCreator();
        while (scanner.hasNext()) {
            String[] coords = scanner.nextLine().split(" ");
            if (coords.length != 2) {
                continue;
            }
            var enemy = enemyCreator.create(new Vector2(Double.parseDouble(coords[0]) - 30, Double.parseDouble(coords[1]) * 2 - 20));
            enemy.transform().setParent(enemiesParent.transform());
        }
        enemiesParent.addComponent(new BurgerManager(enemiesParent));
        return enemiesParent;
    }
}
