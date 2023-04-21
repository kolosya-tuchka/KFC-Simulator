package NewDayNewGame.Core;

import org.jetbrains.annotations.NotNull;

public class Rect {
    private Vector2 center;
    private Vector2 size;

    public Rect(@NotNull Vector2 center, @NotNull Vector2 size) {
        setCenter(center);
        setSize(size);
    }

    public Rect(@NotNull Rect rect) {
        setSize(rect.getSize());
        setCenter(rect.getCenter());
    }


    public Vector2 getCenter() {
        return new Vector2(center);
    }

    public Vector2 getSize() {
        return new Vector2(size);
    }

    public void setSize(@NotNull Vector2 size) {
        this.size = size;
    }

    public void setCenter(@NotNull Vector2 center) {
        this.center = center;
    }
}
