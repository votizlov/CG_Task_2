package sample.ellipseDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class BrezFilledEllipsDrawer implements EllipsDrawer {

    private final PixelDrawer pixelDrawer;

    public BrezFilledEllipsDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawEllips(int xc, int yc, int a, int b, Color color) {
        int height = a;
        int width = b;
        int hh = height * height;
        int ww = width * width;
        int hhww = hh * ww;
        int x0 = width;
        int dx = 0;

// do the horizontal diameter
        for (int x = -width; x <= width; x++)
            pixelDrawer.drawPixel(xc + x, yc,color);

// now do both halves at the same time, away from the diameter
        for (int y = 1; y <= height; y++)
        {
            int x1 = x0 - (dx - 1);  // try slopes of dx - 1 or more
            for ( ; x1 > 0; x1--)
                if (x1*x1*hh + y*y*ww <= hhww)
                    break;
            dx = x0 - x1;  // current approximation of the slope
            x0 = x1;

            for (int x = -x0; x <= x0; x++)
            {
                pixelDrawer.drawPixel(xc + x, yc - y,color);
                pixelDrawer.drawPixel(xc + x, yc + y,color);
            }
        }
    }

    @Override
    public void drawPie(int x, int y, int r, int angle) {

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
