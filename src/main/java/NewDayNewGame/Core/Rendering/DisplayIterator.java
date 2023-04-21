package NewDayNewGame.Core.Rendering;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class DisplayIterator implements Iterator<Renderer> {
    private Display display;
    private int i;

    public DisplayIterator(@NotNull Display display) {
        this.display = display;
        i = 0;
    }

    @Override
    public boolean hasNext() {
        return i < display.renderers.size();
    }

    @Override
    public Renderer next() {
        return display.renderers.get(i++);
    }
}
