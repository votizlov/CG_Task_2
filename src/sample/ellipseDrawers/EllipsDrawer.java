package sample.ellipseDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public interface EllipsDrawer {
    void drawEllips(int x, int y, int a, int b, Color color);
    void drawPie(int x, int y, int r, int angle);
    void setPixelDrawer(PixelDrawer pixelDrawer);
}
