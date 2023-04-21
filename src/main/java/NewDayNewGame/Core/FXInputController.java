package NewDayNewGame.Core;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class FXInputController implements EventHandler<KeyEvent> {
    private static FXInputController instance;

    public FXInputController() {
        instance = this;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (KeyEvent.KEY_PRESSED.equals(keyEvent.getEventType())) {
            Input.getInstance().keyPressed[keyEvent.getCode().getCode()] = true;
        } else if (KeyEvent.KEY_RELEASED.equals(keyEvent.getEventType())) {
            Input.getInstance().keyPressed[keyEvent.getCode().getCode()] = false;
        } else if (KeyEvent.KEY_TYPED.equals(keyEvent.getEventType())) {
            Input.getInstance().keyTyped[keyEvent.getCode().getCode()] = true;
        }
    }

    public static FXInputController getInstance() {
        return instance;
    }
}
