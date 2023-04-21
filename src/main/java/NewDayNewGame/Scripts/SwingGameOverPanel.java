package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.SwingDisplay;
import NewDayNewGame.Core.Time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGameOverPanel extends Component implements ActionListener {
    private SwingDisplay swingDisplay;
    private Timer timer;
    private HighScoresManager highScoresManager;

    public SwingGameOverPanel(GameObject gameObject, SwingDisplay display, Timer timer, HighScoresManager highScoresManager) {
        super(gameObject);
        this.swingDisplay = display;
        this.timer = timer;
        this.highScoresManager = highScoresManager;
    }

    @Override
    protected void start() {
        GameJudge judge = Game.getInstance().getCurrentScene().findObjectOfType(GameJudge.class);
        judge.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (var display : Game.getInstance().getDisplays()) {
            display.setVisible(false);
        }
        swingDisplay.setVisible(true);
        if (e.getActionCommand().equals("lose")) {
            Time.getInstance().timeScale = 0;
            JButton btnRestart = new JButton("Restart");
            btnRestart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Game.getInstance().setNextScene(1);
                    } catch (Exception ignored) {

                    }
                }
            });
            JButton btnMenu = new JButton("Menu");
            btnMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Game.getInstance().setNextScene(0);
                    } catch (Exception ignored) {

                    }
                }
            });
            JLabel text = new JLabel("You lose");
            swingDisplay.getPanel().enabled = false;
            swingDisplay.getPanel().setLayout(new BoxLayout(swingDisplay.getPanel(), BoxLayout.Y_AXIS));
            swingDisplay.getPanel().setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
            swingDisplay.getPanel().add(text);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(btnRestart);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(btnMenu);
            swingDisplay.getPanel().updateUI();
            swingDisplay.getFrame().setSize(200, 200);
            swingDisplay.getFrame().setResizable(false);
        } else if (e.getActionCommand().equals("win")) {
            Time.getInstance().timeScale = 0;
            JButton btnRestart = new JButton("Restart");
            btnRestart.addActionListener(e1 -> {
                try {
                    highScoresManager.saveScore();
                    Game.getInstance().setNextScene(1);
                } catch (Exception ignored) {}
            });
            JButton btnMenu = new JButton("Menu");
            btnMenu.addActionListener(e12 -> {
                try {
                    highScoresManager.saveScore();
                    Game.getInstance().setNextScene(0);
                } catch (Exception ignored) {}
            });
            btnMenu.setEnabled(false);
            btnRestart.setEnabled(false);
            JTextField editorPane = new JTextField();
            editorPane.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    onChanged();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    onChanged();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    onChanged();
                }

                private void onChanged() {
                    boolean enabled = editorPane.getDocument().getLength() > 3 && editorPane.getDocument().getLength() < 17;
                    highScoresManager.setPlayerName(editorPane.getText());
                    btnMenu.setEnabled(enabled);
                    btnRestart.setEnabled(enabled);
                }
            });
            JLabel text = new JLabel("You won!");
            String seconds = timer.getSeconds() < 10 ? "0" + timer.getSeconds() : String.valueOf(timer.getSeconds());
            String minutes = timer.getMinutes() < 10 ? "0" + timer.getMinutes() : String.valueOf(timer.getMinutes());
            JLabel timeText = new JLabel("Time: " + minutes + ":" + seconds);
            swingDisplay.getPanel().enabled = false;
            swingDisplay.getPanel().setLayout(new BoxLayout(swingDisplay.getPanel(), BoxLayout.Y_AXIS));
            swingDisplay.getPanel().setBorder(new EmptyBorder(new Insets(10, 50, 50, 50)));
            swingDisplay.getPanel().add(text);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(timeText);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(editorPane);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(btnRestart);
            swingDisplay.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
            swingDisplay.getPanel().add(btnMenu);
            swingDisplay.getFrame().setSize(200, 210);
            swingDisplay.getFrame().setResizable(false);
            swingDisplay.getPanel().updateUI();
        }
    }
}
