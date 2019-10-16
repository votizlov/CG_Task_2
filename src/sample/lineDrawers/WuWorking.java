package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.*;

public class WuWorking implements LineDrawer {
    private PixelDrawer pd;
    WuWorking (PixelDrawer pd){
        this.pd = pd;
    }
    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color c) {

        float dx = x1 - x0;
        float dy = y1 - y0;
        float gradient = 0;
        float y = 0;
        float x = 0;
        if (abs(dx) >= abs(dy)) {
            gradient = dy / abs(dx);
            y = y0 + gradient;
            x = x0 + signum(dx);
            float incr = signum(dy);
            if (dx > 0 && dy > 0 || dx < 0 && dy > 0 && abs(dx) > abs(dy)) {
                incr = -incr;
            }

            for (int i = 0; i < abs(dx) - 1; i++) {

                pd.drawPixel((int) x, (int) y,
                        new Color((c.getRed() * (1 - (y - (int) floor(y)))),
                                (int) (c.getGreen() * (1 - (y - (int) floor(y)))),
                                (int) (c.getBlue() * (1 - (y - (int) floor(y))))));
                pd.drawPixel((int) x, (int) (y + incr), new Color((c.getRed() * (y - (int) floor(y))),
                        (int) (c.getGreen() * (y - (int) floor(y))),
                        (int) (c.getBlue() * (y - (int) floor(y)))));
// plot(pd, (int) x, (int)y, c, 0);
//plot(pd, (int) x, (int) (y + signum(dy)), Color.BLUE, 0);
                x += signum(dx);
                y += gradient;
            }
        } else {
            gradient = dx / abs(dy);
            y = y0 + signum(dy);
            x = x0 + gradient;
            float incr = signum(dx);
            if (dx > 0 && dy > 0 || dx > 0 && dy < 0 && abs(dy) > abs(dx)) {
                incr = -signum(dx);
            }
            for (int i = 0; i < abs(dy) - 1; i++) {
                pd.drawPixel((int) x, (int) y, new Color(c.getRed() * (1 - (x - (int) x)),
                        c.getGreen() * (1 - (x - (int) x)),
                        c.getBlue() * (1 - (x - (int) x))));
                pd.drawPixel((int) (x + incr), (int) y, new Color(c.getRed() * (x - (int) x),
                        c.getGreen() * (x - (int) x),
                        c.getBlue() * (x - (int) x)));
//plot(pd, (int) x, (int)y, c, 0);
//plot(pd, (int) (x + signum(dx)), (int) y, Color.BLUE, 0);
                x += gradient;
                y += signum(dy);
            }

        }
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
