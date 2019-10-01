package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    PixelDrawer pd;
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pd = pixelDrawer;
    }
}
