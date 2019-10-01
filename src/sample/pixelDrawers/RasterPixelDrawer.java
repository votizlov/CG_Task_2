package sample.pixelDrawers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RasterPixelDrawer implements PixelDrawer {
    //private BufferedImage bi;
    private int[] pixels;
    private int w;

    public RasterPixelDrawer(BufferedImage bi) {
        //this.bi = bi;
        pixels = ((DataBufferInt) bi.getRaster().getDataBuffer()).getData();
        w = bi.getWidth();
    }

    public void drawPixel(int x, int y, Color c) {
        pixels[w*y+x]=c.getRGB();
    }
}
