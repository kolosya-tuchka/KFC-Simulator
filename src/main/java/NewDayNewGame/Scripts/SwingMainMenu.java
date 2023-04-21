package NewDayNewGame.Scripts;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.Display;
import NewDayNewGame.Core.Rendering.SwingDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingMainMenu extends Component {
    SwingDisplay display;

    public SwingMainMenu(GameObject gameObject, SwingDisplay display) {
        super(gameObject);
        this.display = display;
    }

    @Override
    protected void start() {
        SwingDisplay swingDisplay = (SwingDisplay) Display.getMainDisplay();
        swingDisplay.getPanel().enabled = false;
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Game.getInstance().setNextScene(1);
                } catch (Exception ignored) {

                }
            }
        });
        JLabel text = new JLabel("KFC Simulator");
        display.getPanel().setLayout(new BoxLayout(display.getPanel(), BoxLayout.Y_AXIS));
        display.getPanel().setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
        display.getPanel().add(text);
        display.getPanel().add(Box.createRigidArea(new Dimension(0, 15)));
        display.getPanel().add(btnStart);
        display.getPanel().updateUI();
        display.getFrame().setSize(220, 220);
        display.getFrame().setResizable(false);
    }
}
