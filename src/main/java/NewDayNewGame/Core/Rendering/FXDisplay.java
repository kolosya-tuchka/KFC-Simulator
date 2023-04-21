package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.FXInputController;
import NewDayNewGame.Core.Game;
import NewDayNewGame.Core.UI.TextRenderer;
import NewDayNewGame.Core.Vector2;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class FXDisplay extends Display {
    private Stage stage;
    private Scene scene;
    private VBox pane;
    private FXSpritePanel panel;
    private Canvas canvas;
    private static TreeMap<String, Image> sprites;
    private static boolean initFlag = true;
    public static Stage primaryStage;

    public FXDisplay(int width, int height) throws IOException {
        super(width, height);
        if (initFlag) {
            initFlag = false;
            stage = primaryStage;
            FXInputController inputController = new FXInputController();
        } else {
            stage = new Stage();
            stage.initOwner(primaryStage);
        }
        canvas = new Canvas();
        panel = new FXSpritePanel();
        pane = new VBox();
        pane.getChildren().add(canvas);
        pane.setAlignment(Pos.CENTER);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
    }

    public static void loadSprites(File file) throws IOException {
        sprites = new TreeMap<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String id = scanner.next();
            InputStream imgStream = new FileInputStream(scanner.next());
            sprites.put(id, new Image(imgStream));
        }
    }

    @Override
    public void update() throws IOException {
        setWidth((int) stage.getWidth());
        setHeight((int) stage.getHeight());
        panel.size = new Vector2(getWidth(), getHeight());
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
        super.update();
        while (!Game.getInstance().canRender() || panel.sprites.isEmpty()) {
            Thread.yield();
        }
        synchronized (panel.sprites) {
            panel.actualSprites = (TreeSet<FXImageContainer>) panel.sprites.clone();
        }
        panel.paint(canvas.getGraphicsContext2D());
    }

    @Override
    public void awake() {
        scene.setOnKeyPressed(FXInputController.getInstance());
        scene.setOnKeyReleased(FXInputController.getInstance());
        scene.setOnKeyTyped(FXInputController.getInstance());
        panel.enabled = true;
        Platform.runLater(() -> stage.setTitle(Game.getInstance().getCurrentScene().getName()));
    }

    @Override
    public void clear() {
        synchronized (panel.sprites) {
            panel.sprites.clear();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        Platform.runLater(() -> {
            if (visible) {
                stage.show();
            } else {
                stage.hide();
            }
        });
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(stage.getX(), stage.getY());
    }

    @Override
    protected void addSprite(Renderer renderer, Vector2 center, Vector2 size) {
        if (renderer instanceof TextRenderer textRenderer) {
            if (textRenderer.getCanvas().getRenderingCamera().getDisplay() != this) {
                return;
            }
        }

        synchronized (panel.sprites) {
            panel.sprites.add(new FXImageContainer(renderer, center, size));
        }
    }

    public Stage getStage() {
        return stage;
    }

    public VBox getPane() {
        return pane;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void onSceneLoad() {
        super.onSceneLoad();
        Platform.runLater(() -> {
            pane.getChildren().clear();
            pane.getChildren().add(canvas);
        });
    }

    public static Image getSprite(String spriteID) {
        return sprites.get(spriteID);
    }
}
