package sample.pixelDrawers;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface PixelDrawer {
    void drawPixel(int x, int y, Color c);
    //PixelDrawer getInstance(BufferedImage bi);
}
