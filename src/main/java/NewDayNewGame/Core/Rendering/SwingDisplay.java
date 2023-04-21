package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.SwingInputController;
import NewDayNewGame.Core.UI.TextRenderer;
import NewDayNewGame.Core.Vector2;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class SwingDisplay extends Display {
    private final JFrame frame;
    private final SwingSpritePanel panel;
    private static TreeMap<String, BufferedImage> sprites;

    @Override
    public void update() throws IOException {
        setWidth(frame.getWidth());
        setHeight(frame.getHeight());
        panel.size = new Vector2(width, height);
        super.update();
        while (!Game.getInstance().canRender() || panel.sprites.isEmpty()) {
            Thread.yield();
        }
        synchronized (panel.sprites) {
            panel.actualSprites = (TreeSet<SwingImageContainer>) panel.sprites.clone();
        }
        panel.repaint(panel.getGraphics());
        panel.actualSprites.clear();
    }

    public SwingDisplay(int width, int height) throws IOException {
        super(width, height);

        panel = new SwingSpritePanel();
        panel.size = new Vector2(width, height);
        frame = new JFrame("Window");
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void loadSprites(File file) throws IOException {
        sprites = new TreeMap<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            sprites.put(scanner.next(), ImageIO.read(new File(scanner.next())));
        }
    }

    @Override
    public void awake() {
        panel.removeAll();
        frame.addKeyListener(SwingInputController.getInstance());
        panel.enabled = true;
        frame.setTitle(Game.getInstance().getCurrentScene().getName());
    }

    @Override
    public void clear() {
        synchronized (panel.sprites) {
            panel.sprites.clear();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(frame.getLocation().x, frame.getLocation().y);
    }

    public JFrame getFrame() {
        return frame;
    }

    public SwingSpritePanel getPanel() {
        return panel;
    }

    @Override
    public void onSceneLoad() {
        super.onSceneLoad();
        //panel.enabled = true;
        panel.removeAll();
    }

    protected void addSprite(Renderer renderer, Vector2 center, Vector2 size) {
        if (renderer instanceof TextRenderer textRenderer) {
            if (textRenderer.getCanvas().getRenderingCamera().getDisplay() != this) {
                return;
            }
        }

        synchronized (panel.sprites) {
            panel.sprites.add(new SwingImageContainer(renderer, center, size));
        }
    }

    protected static BufferedImage getSprite(String id) {
        return sprites.get(id);
    }
}
