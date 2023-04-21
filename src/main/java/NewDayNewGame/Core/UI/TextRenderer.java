package NewDayNewGame.Core.UI;

import NewDayNewGame.Core.GameObject;
import NewDayNewGame.Core.Rendering.Renderer;
import NewDayNewGame.Core.Vector2;
import java.io.IOException;

public class TextRenderer extends Renderer {
    protected String text;
    protected Canvas canvas;
    private Vector2 pos;

    public TextRenderer(GameObject gameObject, String sortingLayer, byte orderInLayer, Canvas canvas) throws IOException {
        super(gameObject, sortingLayer, orderInLayer, null);
        text = "text";
        this.canvas = canvas;
        transform().setParent(canvas.transform());
        pos = new Vector2(transform().localPosition);
        updateText();
    }

    @Override
    protected void update() {
        var camera = canvas.getRenderingCamera();
        transform().localPosition = pos.scale(new Vector2(camera.getWidth() / 2, camera.getHeight() / 2));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updateText();
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    private void updateText() {
        /*sprite = new BufferedImage(64 * text.length(), 80, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = sprite.createGraphics();
        g2d.setFont(new Font("Bebas Neue", Font.BOLD, 64));
        g2d.setColor(color);
        g2d.drawString(text, 0, g2d.getFontMetrics().getAscent());
        g2d.dispose();*/
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
