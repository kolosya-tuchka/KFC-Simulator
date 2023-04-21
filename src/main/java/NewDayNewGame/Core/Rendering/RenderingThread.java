package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Game;
import java.io.IOException;

public class RenderingThread extends Thread {
    public boolean run = true;

    @Override
    public void run() {
        Game game = Game.getInstance();
        while (run) {
            while (!game.canRender()) {
                Thread.yield();
            }
            for (var display : game.getDisplays()) {
                try {
                    display.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
