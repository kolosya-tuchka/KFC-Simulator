package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HighScoresManager extends Component {
    private Timer timer;
    private String playerName;

    @Override
    protected void start() {
    }

    public HighScoresManager(GameObject gameObject, Timer timer) {
        super(gameObject);
        this.timer = timer;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void saveScore() {
        if (playerName == null || playerName.length() == 0) {
            return;
        }

        FileInputStream inputStream;
        ObjectInputStream objectInputStream;
        HighScoresSave save;
        try {
            inputStream = new FileInputStream("src/main/java/NewDayNewGame/LevelConfigs/highScores.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            save = (HighScoresSave) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception ex) {
            save = new HighScoresSave(new ArrayList<>());
        }
        double seconds = timer.getTime();
        if (save.scores.size() < 10 || seconds < save.scores.get(save.scores.size() - 1).second) {
            save.scores.add(new Pair<>(playerName, seconds));
            save.scores.sort(Comparator.comparing(a -> a.second));
            if (save.scores.size() > 10) {
                save.scores.remove(10);
            }
            try {
                FileOutputStream outputStream = new FileOutputStream("src/main/java/NewDayNewGame/LevelConfigs/highScores.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(save);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        //System.out.println(Arrays.toString(save.scores.toArray()));
    }
}
