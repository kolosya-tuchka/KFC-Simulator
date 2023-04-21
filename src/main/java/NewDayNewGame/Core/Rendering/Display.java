package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Vector2;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Display implements Iterable<Renderer> {
    protected int width = 128, height = 128;
    private static Display main;

    protected static ArrayList<Renderer> renderers;
    protected static ArrayList<String> sortingLayers;

    public void onSceneLoad() {
        renderers.clear();
    }

    public void update() throws IOException {
        ArrayList<Renderer> toDestroy = new ArrayList<>();
        for (var renderer : renderers) {
            if (renderer.gameObject().destroyFlag) {
                toDestroy.add(renderer);
            }
        }
        for (var renderer : toDestroy) {
            renderers.remove(renderer);
        }
        renderers.sort(this::compareRenderers);
    }

    public abstract void awake();

    public abstract void clear();

    public Display(int width, int height) throws FileNotFoundException {
        if (main == null) {
            main = this;
        }

        if (sortingLayers == null) {
            sortingLayers = new ArrayList<>();
        }

        if (renderers == null) {
            renderers = new ArrayList<>();
        }

        setHeight(height);
        setWidth(width);

        loadSortingLayers(new File("src/main/java/NewDayNewGame/Core/Rendering/sortingLayers.txt"));
    }

    public void loadSortingLayers(File file) throws FileNotFoundException {
        sortingLayers = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            sortingLayers.add(scanner.next());
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void setVisible(boolean visible);

    public abstract Vector2 getPos();

    public void setHeight(int height) {
        if (height < 0) {
            return;
        }
        this.height = height;
    }

    public void setWidth(int width) {
        if (width < 0) {
            return;
        }
        this.width = width;
    }

    public static Display getMainDisplay() {
        return main;
    }

    public static void setMainDisplay(Display display) {
        main = display;
    }

    private int compareRenderers(@NotNull Renderer first, @NotNull Renderer second) {
        int index1 = sortingLayers.indexOf(first.getSortingLayer());
        int index2 = sortingLayers.indexOf(second.getSortingLayer());

        if (index1 > index2) {
            return 1;
        }

        if (index2 > index1) {
            return -1;
        }

        int order1 = first.orderInLayer;
        int order2 = second.orderInLayer;

        if (order1 > order2) {
            return 1;
        }

        if (order2 > order1) {
            return -1;
        }

        return 0;
    }

    protected void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }

    protected abstract void addSprite(Renderer renderer, Vector2 center, Vector2 size);

    protected void removeRenderer(Renderer renderer) {
        renderers.remove(renderer);
    }

    protected void setSortingLayers(@NotNull ArrayList<String> sortingLayers) {
        Display.sortingLayers = (ArrayList<String>) sortingLayers.clone();
    }

    public boolean hasSortingLayer(String sortingLayer) {
        return Display.sortingLayers.contains(sortingLayer);
    }

    public Iterator<Renderer> iterator() {
        return new DisplayIterator(this);
    }
}
