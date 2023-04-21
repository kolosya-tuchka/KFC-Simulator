package NewDayNewGame.Core.Rendering;

import NewDayNewGame.Core.Vector2;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TreeSet;

public class SwingSpritePanel extends JPanel {
    protected final TreeSet<SwingImageContainer> sprites;
    protected TreeSet<SwingImageContainer> actualSprites;
    protected Vector2 size;
    public boolean enabled = false;

    public SwingSpritePanel() throws IOException {
        super.addNotify();
        sprites = new TreeSet<>();
        actualSprites = new TreeSet<>();
        setBackground(null);
        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)size.x, (int)size.y);
    }

    public void repaint(Graphics g) {
        if (!enabled) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        /*BufferedImage buffer = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bufferGr = buffer.createGraphics();
        bufferGr.setColor(Color.BLACK);
        bufferGr.fillRect(0, 0, (int)size.x, (int)size.y);

        for (var sprite : actualSprites) {
            if (sprite.renderer.getSize() == null) {
                return;
            }
            Image rendImage = sprite.getGraphics().getScaledInstance((int)sprite.rendSize.x, (int)sprite.rendSize.y, Image.SCALE_REPLICATE);

            Vector2 renderingPos = new Vector2(sprite.rendCenter.x - sprite.rendSize.x / 2, sprite.rendCenter.y - sprite.rendCenter.y / 2);
            bufferGr.drawImage(rendImage, (int)renderingPos.x, (int)renderingPos.y, this);
        }*/
        Image buffer = getRendImage();
        if (buffer == null) {
            return;
        }
        g2d.drawImage(buffer, 0, 0, this);
        g2d.dispose();
    }

    public Image getRendImage() {
        BufferedImage buffer = new BufferedImage((int)size.x, (int)size.y, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bufferGr = buffer.createGraphics();
        bufferGr.setColor(Color.BLACK);
        bufferGr.fillRect(0, 0, (int)size.x, (int)size.y);

        for (var sprite : actualSprites) {
            if (sprite.renderer.getSize() == null) {
                return null;
            }
            Image rendImage = sprite.getGraphics().getScaledInstance((int)sprite.rendSize.x, (int)sprite.rendSize.y, Image.SCALE_REPLICATE);

            Vector2 renderingPos = new Vector2(sprite.rendCenter.x - sprite.rendSize.x / 2, sprite.rendCenter.y - sprite.rendCenter.y / 2);
            bufferGr.drawImage(rendImage, (int)renderingPos.x, (int)renderingPos.y, this);
        }
        return buffer;
    }
}
