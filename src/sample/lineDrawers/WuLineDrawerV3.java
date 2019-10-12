package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class WuLineDrawerV3 implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public WuLineDrawerV3(PixelDrawer pixelDrawer){
        this.pixelDrawer = pixelDrawer;
    }
    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        DDALineDrawer lineDrawer = new DDALineDrawer(pixelDrawer);
        lineDrawer.drawLine(x1,  y1, x2,  y2, color);
        lineDrawer.drawLine(x1-1,  y1, x2-1,  y2, color.darker());
        lineDrawer.drawLine(x1+1,  y1, x2+1,  y2, color.darker());
        lineDrawer.drawLine(x1, y1+1, x2, y2+1, color.darker());
        lineDrawer.drawLine(x1,  y1-1, x2,  y2-1, color.darker());
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
