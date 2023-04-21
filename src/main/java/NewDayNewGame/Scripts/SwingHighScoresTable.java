package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.SwingDisplay;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SwingHighScoresTable extends Component {
    private SwingDisplay display, mainDisplay;
    public SwingHighScoresTable(GameObject gameObject, SwingDisplay display, SwingDisplay mainDisplay) {
        super(gameObject);
        this.display = display;
        this.mainDisplay = mainDisplay;
    }

    @Override
    protected void start() {
        JButton button = new JButton("High scores");
        button.addActionListener(e -> show());
        mainDisplay.getPanel().add(button);
        display.getPanel().getGraphics().clearRect(0, 0, display.getWidth(), display.getHeight());
        display.getPanel().enabled = false;
        display.getPanel().updateUI();
    }

    private void show() {
        display.getFrame().setTitle("High scores");
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
        display.getPanel().removeAll();
        display.getPanel().setLayout(new GridLayout(save.scores.size(), 2));
        for (var record : save.scores) {
            JLabel timeLabel = new JLabel();
            timeLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            JLabel nameLabel = new JLabel();
            nameLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
            nameLabel.setText(record.first);
            Double time = record.second;
            int minutes = (int) (time / 60);
            int seconds = (int) (time % 60);
            String strMin = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
            String strSec = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
            timeLabel.setText(strMin + ":" + strSec);
            display.getPanel().add(nameLabel);
            display.getPanel().add(timeLabel);
        }
        display.getPanel().updateUI();
    }
}