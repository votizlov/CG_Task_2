package sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBufferPixelDrawer implements PixelDrawer {//todo switch code to this architecture
    private BufferedImage bi;

    public ImageBufferPixelDrawer(BufferedImage bi) {
        this.bi = bi;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        bi.setRGB(x,y,c.getRGB());
    }
}
