package sample.pixelDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

@Deprecated
public class GraphixPixelDrawer implements PixelDrawer {
    @Override
    public void drawPixel(int x, int y, Color c) {

    }

    //@Override
    public PixelDrawer getInstance(BufferedImage bi) {
        return null;
    }
}
