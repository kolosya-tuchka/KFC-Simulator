package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.SwingDisplay;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SwingGameAbout extends Component {
    private SwingDisplay display, mainDisplay;
    public SwingGameAbout(GameObject gameObject, SwingDisplay display, SwingDisplay mainDisplay) {
        super(gameObject);
        this.display = display;
        this.mainDisplay = mainDisplay;
    }

    @Override
    protected void start() {
        JButton button = new JButton("About");
        button.addActionListener(e -> show());
        mainDisplay.getPanel().add(button);
        display.getPanel().getGraphics().clearRect(0, 0, display.getWidth(), display.getHeight());
        display.getPanel().enabled = false;
        display.getPanel().updateUI();
    }

    private void show() {
        display.getFrame().setTitle("About");
        display.getPanel().removeAll();
        try {
            display.getPanel().setLayout(new BoxLayout(display.getPanel(), BoxLayout.Y_AXIS));
            String text = Files.readString(Paths.get("src/main/java/NewDayNewGame/LevelConfigs/about.txt"));
            JTextArea textArea = new JTextArea(text);
            textArea.setEditable(false);
            display.getPanel().add(textArea);
        } catch (Exception ignored) {}
        display.getPanel().updateUI();
    }
}
