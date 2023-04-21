package NewDayNewGame.Core;

public class Input {
    protected static Input instance;

    protected final boolean[] keyPressed;
    protected final boolean[] keyTyped;

    public Input() {
        instance = this;
        keyPressed = new boolean[256];
        keyTyped = new boolean[256];
    }

    protected void update() {
        for (int i = 0; i < 256; ++i) {
            keyTyped[i] = false;
        }
    }

    public static Input getInstance() {
        return instance;
    }

    public boolean getKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }
}
