package sample.arcDrawers;

import sample.pixelDrawers.PixelDrawer;

public interface ArcDrawer {
    void drawArc(int x,int y,int startAngle,int endAngle,int radius);
    void setPixelDrawer(PixelDrawer pixelDrawer);
}
