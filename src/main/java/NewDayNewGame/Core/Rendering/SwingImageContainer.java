package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.UI.TextRenderer;
import NewDayNewGame.Core.Vector2;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SwingImageContainer implements Comparable<SwingImageContainer> {
    protected Renderer renderer;
    protected Vector2 rendCenter, rendSize;

    public SwingImageContainer(Renderer renderer, Vector2 rendCenter, Vector2 rendSize) {
        this.renderer = renderer;
        this.rendCenter = new Vector2(rendCenter);
        this.rendSize = new Vector2(rendSize);
    }

    @Override
    public int compareTo(@NotNull SwingImageContainer other) {
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

    protected BufferedImage getGraphics() {
        if (renderer instanceof SpriteRenderer sprite) {
            return SwingDisplay.getSprite(sprite.spriteID);
        } else if (renderer instanceof TextRenderer textRenderer) {
            BufferedImage textImage = new BufferedImage(textRenderer.getText().length() * 100, 80, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2d = textImage.createGraphics();
            g2d.setFont(new Font("Arial", Font.BOLD, 64));
            g2d.setColor(Color.YELLOW);
            g2d.drawString(textRenderer.getText(), 0, g2d.getFontMetrics().getAscent());
            g2d.dispose();
            return textImage;
        }
        return null;
    }
}
