package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Component;
import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public abstract class Renderer extends Component {
    protected Vector2 size;
    private String sortingLayer = "default";
    public byte orderInLayer = 0;

    public Renderer(GameObject gameObject, String sortingLayer, byte orderInLayer, Vector2 size) {
        super(gameObject);
        this.orderInLayer = orderInLayer;
        setSortingLayer(sortingLayer);
        Display.getMainDisplay().addRenderer(this);
        this.size = new Vector2(size);
    }

    public String getSortingLayer() {
        return new String(sortingLayer);
    }

    public void setSortingLayer(String sortingLayer) {
        this.sortingLayer = sortingLayer;
    }

    public void setSize(Vector2 size) {
        if (size == null) {
            return;
        }

        this.size = new Vector2(size);
    }

    public Vector2 getSize() {
        return new Vector2(size);
    }

    public Vector2 getCenter() {
        return new Vector2(transform().position());
    }

    @Override
    protected void onSceneLoad() {
        Display.renderers.add(this);
    }
}
