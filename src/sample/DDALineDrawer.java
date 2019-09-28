package sample;

public class DDALineDrawer implements LineDrawer {
    PixelDrawer pd;
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pd = pixelDrawer;
    }
}
