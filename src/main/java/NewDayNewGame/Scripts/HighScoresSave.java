package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class HighScoresSave implements Serializable {
    public ArrayList<Pair<String, Double>> scores;

    public HighScoresSave(ArrayList<Pair<String, Double>> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "HighScoresSave{" +
                "scores=" + Arrays.toString(scores.toArray()) + "}";
    }
}
