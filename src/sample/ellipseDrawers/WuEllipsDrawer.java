package sample.ellipseDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class WuEllipsDrawer implements EllipsDrawer {
    private PixelDrawer pixelDrawer;

    WuEllipsDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawEllips(int x, int y, int a, int b, Color color) {
        int x = 0;
        int y = r;
        int delta = 1 - 2 * r;
        int error = 0;
        while (y >= 0) {
            //       drawpixel(X1 + x, Y1 + y);
            //       drawpixel(X1 + x, Y1 - y);
            //       drawpixel(X1 - x, Y1 + y);
            //raster[w * (y + y0) + x + x0] = SHAPES_COLOR;

            error = 2 * (delta + y) - 1;
            if ((delta < 0) && (error <= 0)) {
                delta += 2 * ++x + 1;
                continue;
            }
            if ((delta > 0) && (error > 0)) {
                delta -= 2 * --y + 1;
                continue;
            }
            delta += 2 * (++x - y--);
        }
    }

    @Override
    public void drawPie(int x, int y, int r, int angle) {

    }
}
