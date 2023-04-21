package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Vector2;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class ImageContainer implements Comparable<ImageContainer> {
    protected Renderer renderer;
    protected Vector2 rendCenter, rendSize;

    public ImageContainer(Renderer renderer, Vector2 rendCenter, Vector2 rendSize) {
        this.renderer = renderer;
        this.rendCenter = new Vector2(rendCenter);
        this.rendSize = new Vector2(rendSize);
    }

    @Override
    public int compareTo(@NotNull ImageContainer other) {
        ArrayList<String> sortingLayers = Display.sortingLayers;
        int index1 = sortingLayers.indexOf(this.renderer.getSortingLayer());
        int index2 = sortingLayers.indexOf(other.renderer.getSortingLayer());

        if (index1 > index2) {
            return 1;
        }

        if (index2 > index1) {
            return -1;
        }

        int order1 = this.renderer.orderInLayer;
        int order2 = other.renderer.orderInLayer;

        if (order1 > order2) {
            return 1;
        }

        if (order2 > order1) {
            return -1;
        }

        return 1;
    }
}
