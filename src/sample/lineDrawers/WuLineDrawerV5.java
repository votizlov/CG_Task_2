package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.round;

public class WuLineDrawerV5 implements LineDrawer {

    private PixelDrawer pixelDrawer;

    public WuLineDrawerV5(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int rgb = color.getRGB();
        var steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);

        if (steep) {
            y0 = [x0, x0 = y0][0]; // swap x0/y0
            y1 = [x1, x1 = y1][0]; // swap x1, y1
        }

        if (x0 > x1) {
            x1 = [x0, x0 = x1][0]; // swap x0/x1
            y1 = [y0, y0 = y1][0]; // swap y0/y1
        }

        var dx = x1 - x0;
        var dy = y1 - y0;

        var gradient = dy / dx;

        // handle first endpoint

        var xend = round(x0);
        var yend = y0 + gradient * (xend - x0);
        var xgap = rfpart(x0 + 0.5);
        var xpxl1 = xend;
        var ypxl1 = ipart(yend);

        if (steep) {
            plot(ypxl1, xpxl1, rfpart(yend) * xgap, rgb);
            plot(ypxl1 + 1, xpxl1, fpart(yend) * xgap, rgb);
        } else {
            plot(xpxl1, ypxl1, rfpart(yend) * xgap, rgb);
            plot(xpxl1, ypxl1 + 1, fpart(yend) * xgap, rgb);
        }

        var intery = yend + gradient;

        // handle second endpoint

        xend = round(x1);
        yend = y1 + gradient * (xend - x1);
        xgap = fpart(x1 + 0.5);
        var xpxl2 = xend;
        var ypxl2 = ipart(yend);

        if (steep) {
            plot(ypxl2, xpxl2, rfpart(yend) * xgap, rgb);
            plot(ypxl2 + 1, xpxl2, fpart(yend) * xgap, rgb);
        } else {
            plot(xpxl2, ypxl2, rfpart(yend) * xgap, rgb);
            plot(xpxl2, ypxl2 + 1, fpart(yend) * xgap, rgb);
        }

        // main loop

        for (var x = xpxl1 + 1; x < xpxl2; x += 1) {

            if (steep) {
                plot(ipart(intery), x, rfpart(intery), rgb);
                plot(ipart(intery) + 1, x, fpart(intery), rgb);
            } else {
                plot(x, ipart(intery), rfpart(intery), rgb);
                plot(x, ipart(intery) + 1, fpart(intery), rgb);
            }

            intery = intery + gradient;
        }

        yield({
                step:_.sprintf("drawLine renders the anti-aliased line using optimal integer calculations."),
                variables:{
            x0:
            x0 >> 0,
                    y0:y0 >> 0,
                    x1:x1 >> 0,
                    y1:y1 >> 0
        },
        line:
        "drawLine"
        });

    }

    /**
     * fractional part of x
     * @param x
     */
     int fpart(double x) {

        // fractional part must be unsigned
        var n = Math.abs(x);
        return (int)( n - Math.floor(n));
    }

    /**
     * 1 - the fractional part of x
     * @param x
     */
     int rfpart(double x) {
        return 1 - fpart(x);
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }
}
