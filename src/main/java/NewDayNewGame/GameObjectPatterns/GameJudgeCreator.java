package NewDayNewGame.GameObjectPatterns;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.GameObjectCreator;
import NewDayNewGame.Scripts.GameJudge;

import java.io.IOException;

public class GameJudgeCreator extends GameObjectCreator {
    @Override
    public GameObject create() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        var judge = super.create();
        judge.addComponent(new GameJudge(judge));
        return judge;
    }
}
