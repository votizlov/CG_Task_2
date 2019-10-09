package sample.arcDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.sin;

public class BrezArcDrawer implements ArcDrawer {
    private PixelDrawer pixelDrawer;

    public BrezArcDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawArc(int x0, int y0, int startAngle, int endAngle, int r) {
        int x = 0;
        int y = r;
        int delta = 1 - 2 * r;
        int error = 0;
        while (y > 0) {//>=
            if (x / y > 0 && y / x > 0 && x / y > sin(startAngle) && x / y < sin(endAngle))
                pixelDrawer.drawPixel(x0 + x, y0 + y, Color.CYAN);
            if (x / y < 0 && y / x < 0 && x / y > sin(startAngle) && x / y < sin(endAngle))
                pixelDrawer.drawPixel(x0 - x, y0 + y, Color.CYAN);
            if (x / y > 0 && y / x < 0 && x / y > sin(startAngle) && x / y < sin(endAngle))
                pixelDrawer.drawPixel(x0 + x, y0 - y, Color.CYAN);
            if (x / y < 0 && y / x > 0 && x / y > sin(startAngle) && x / y < sin(endAngle))
                pixelDrawer.drawPixel(x0 - x, y0 - y, Color.CYAN);

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
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
