package NewDayNewGame.Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingInputController implements KeyListener {
    private static SwingInputController instance;

    public SwingInputController() {
        instance = this;
    }

    public static SwingInputController getInstance() {
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       Input.getInstance().keyTyped[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
       Input.getInstance().keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Input.getInstance().keyPressed[e.getKeyCode()] = false;
    }
}
